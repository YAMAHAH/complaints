package com.axon.axondemo.commandside.command.product;

import com.axon.axondemo.domain.model.OrderId;

public class ReserveProductCommand {
    private OrderId orderId;
    private String productId;
    private int number;

    public ReserveProductCommand(OrderId orderId, String productId, int number) {
        this.orderId = orderId;
        this.productId = productId;
        this.number = number;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getNumber() {
        return number;
    }
}
