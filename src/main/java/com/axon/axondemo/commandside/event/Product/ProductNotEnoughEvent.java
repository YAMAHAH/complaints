package com.axon.axondemo.commandside.event.Product;

import com.axon.axondemo.domain.model.OrderId;

public class ProductNotEnoughEvent {
    private OrderId orderId;
    private String productId;

    public ProductNotEnoughEvent(OrderId orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }
}
