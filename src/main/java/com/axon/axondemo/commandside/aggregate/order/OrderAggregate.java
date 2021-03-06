package com.axon.axondemo.commandside.aggregate.order;

import com.axon.axondemo.commandside.event.order.OrderCancelledEvent;
import com.axon.axondemo.commandside.event.order.OrderConfirmedEvent;
import com.axon.axondemo.commandside.event.order.OrderCreatedEvent;
import com.axon.axondemo.domain.model.OrderProduct;
import com.axon.axondemo.domain.model.OrderId;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Map;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private OrderId id;
    private String username;
    private double payment;
    private String state="processing";

    @AggregateMember
    private Map<String, OrderProduct> products;

    public OrderAggregate(){}

    public OrderAggregate(OrderId id, String username, Map<String, OrderProduct> products) {
        apply(new OrderCreatedEvent(id, username, payment, products));
    }

    public OrderId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, OrderProduct> getProducts() {
        return products;
    }

    public String getState() {
        return state;
    }

    /**
     * Divided 100 here because of the transformation of accuracy
     *
     * @return
     */
    public double getPayment() {
        return payment/100;
    }

    public void addProduct(OrderProduct product){
        this.products.put(product.getId(), product);
        payment += product.getPrice() * product.getAmount();
    }

    public void removeProduct(String productId){
        OrderProduct product = this.products.remove(productId);
        payment = payment - product.getPrice() * product.getAmount();
    }

    public void delete() {
        apply(new OrderCancelledEvent(id));
    }

    public void confirm(){
        apply(new OrderConfirmedEvent(id));
    }

    @EventHandler
    public void on(OrderCreatedEvent event){
        this.id = event.getOrderId();
        this.username = event.getUsername();
        this.products = event.getProducts();
        computePrice();
    }

    private void computePrice() {
        products.forEach((id, product) -> {
            payment += product.getPrice() * product.getAmount();
        });
    }

    @EventHandler
    public void on(OrderConfirmedEvent event){
        this.state = "confirmed";
    }

    @EventHandler
    public void on(OrderCancelledEvent event){
        this.state = "deleted";
        markDeleted();
    }
}
