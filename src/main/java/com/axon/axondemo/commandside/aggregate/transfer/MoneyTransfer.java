package com.axon.axondemo.commandside.aggregate.transfer;

import com.axon.axondemo.commandside.command.transfer.CancelMoneyTransferCommand;
import com.axon.axondemo.commandside.command.transfer.CompleteMoneyTransferCommand;
import com.axon.axondemo.commandside.command.transfer.RequestMoneyTransferCommand;
import com.axon.axondemo.commandside.event.transfer.MoneyTransferCancelledEvent;
import com.axon.axondemo.commandside.event.transfer.MoneyTransferCompletedEvent;
import com.axon.axondemo.commandside.event.transfer.MoneyTransferRequestedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

@Aggregate
public class MoneyTransfer {

    public MoneyTransfer(){

    }

    @AggregateIdentifier
    private String transferId;

    @CommandHandler
    public MoneyTransfer(RequestMoneyTransferCommand cmd) {
        apply(new MoneyTransferRequestedEvent(cmd.getTransferId(), cmd.getSourceAccount(), cmd.getTargetAccount(),
                cmd.getAmount()));
    }

    @CommandHandler
    public void handle(CompleteMoneyTransferCommand cmd) {
        apply(new MoneyTransferCompletedEvent(transferId));
    }

    @CommandHandler
    public void handle(CancelMoneyTransferCommand cmd) {
        apply(new MoneyTransferCancelledEvent(transferId));
    }

    @EventSourcingHandler
    protected void on(MoneyTransferRequestedEvent event) {
        this.transferId = event.getTransferId();
    }

    @EventSourcingHandler
    protected void on (MoneyTransferCompletedEvent event) {
        markDeleted();
    }
}