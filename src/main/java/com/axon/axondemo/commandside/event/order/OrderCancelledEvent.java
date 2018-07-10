package com.axon.axondemo.commandside.event.order;

import com.axon.axondemo.domain.model.OrderId;

public class OrderCancelledEvent {
    private OrderId id;
    public OrderCancelledEvent(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}
