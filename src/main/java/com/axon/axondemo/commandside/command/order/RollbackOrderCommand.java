package com.axon.axondemo.commandside.command.order;

import com.axon.axondemo.domain.model.OrderId;

public class RollbackOrderCommand {
    private OrderId orderId;
    public RollbackOrderCommand(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
