package com.axon.axondemo.commandside.aggregate.product;

import com.axon.axondemo.commandside.command.product.CreateProductCommand;
import com.axon.axondemo.commandside.event.Product.ProductCreatedEvent;
import com.axon.axondemo.commandside.event.Product.ProductNotEnoughEvent;
import com.axon.axondemo.commandside.event.Product.ProductReservedEvent;
import com.axon.axondemo.commandside.event.order.ReserveCancelledEvent;
import com.axon.axondemo.domain.model.OrderId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.slf4j.LoggerFactory.getLogger;

@Aggregate
public class ProductAggregate {

    private static final Logger LOGGER = getLogger(ProductAggregate.class);

    @AggregateIdentifier
    private String id;
    private String name;
    private int stock;
    private long price;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        apply(new ProductCreatedEvent(command.getId(),command.getName(),command.getPrice(),command.getStock()));
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.stock = event.getStock();
        LOGGER.debug("Product [{}] {} {}x{} is created.", id,name,price,stock);
    }


    public void reserve(OrderId orderId, int amount){
        if(stock>=amount) {
            apply(new ProductReservedEvent(orderId, id, amount));

        }else
            apply(new ProductNotEnoughEvent(orderId, id));
    }

    public void cancellReserve(OrderId orderId, int amount){
        apply(new ReserveCancelledEvent(orderId, id, stock));
    }

    @EventHandler
    public void on(ProductReservedEvent event){
        int oriStock = stock;
        stock = stock - event.getAmount();
        LOGGER.info("Product {} stock change {} -> {}", id, oriStock, stock);
    }

    @EventHandler
    public void on(ReserveCancelledEvent event){
        stock +=event.getAmount();
        LOGGER.info("Reservation rollback, product {} stock changed to {}", id, stock);
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public long getPrice() {
        return price;
    }
}