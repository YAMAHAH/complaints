package com.axon.axondemo.commandside.event.order;

import com.axon.axondemo.domain.model.OrderId;
import com.axon.axondemo.domain.model.OrderProduct;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private OrderId orderId;
    private String username;
    private Double payment;
    private Map<String, OrderProduct> products;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(OrderId orderId, String username,Double payment, Map<String, OrderProduct> products) {
        this.orderId = orderId;
        this.username = username;
        this.products = products;
        this.payment = payment;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, OrderProduct> getProducts() {
        return products;
    }
    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
