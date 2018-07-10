package com.axon.axondemo.commandside.event.order;

import com.axon.axondemo.domain.model.OrderId;

public class ReserveCancelledEvent {
    private OrderId orderId;
    private String productId;
    private int amount;

    public ReserveCancelledEvent(OrderId orderId, String productId, int amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }
}
