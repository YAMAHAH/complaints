package com.axon.axondemo.commandside.command.order;

import com.axon.axondemo.domain.model.OrderId;

import java.util.Map;

public class CreateOrderCommand {

    private OrderId orderId;
    private String username;
    private Map<String, Integer> products;

    public CreateOrderCommand(String username, Map<String, Integer> products) {
        this.orderId = new OrderId();
        this.username = username;
        this.products = products;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }
}

