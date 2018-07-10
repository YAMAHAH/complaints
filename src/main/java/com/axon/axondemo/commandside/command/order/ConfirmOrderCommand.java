package com.axon.axondemo.commandside.command.order;

import com.axon.axondemo.domain.model.OrderId;

public class ConfirmOrderCommand {
    private OrderId id;

    public ConfirmOrderCommand(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}
