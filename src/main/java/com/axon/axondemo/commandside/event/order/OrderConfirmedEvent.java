package com.axon.axondemo.commandside.event.order;

import com.axon.axondemo.domain.model.OrderId;

public class OrderConfirmedEvent {
    private OrderId id;
    public OrderConfirmedEvent(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}
