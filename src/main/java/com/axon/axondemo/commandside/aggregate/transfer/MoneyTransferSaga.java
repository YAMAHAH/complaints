package com.axon.axondemo.commandside.aggregate.transfer;

import com.axon.axondemo.commandside.command.account.DepositMoneyCommand;
import com.axon.axondemo.commandside.command.account.WithdrawMoneyCommand;
import com.axon.axondemo.commandside.command.transfer.CancelMoneyTransferCommand;
import com.axon.axondemo.commandside.command.transfer.CompleteMoneyTransferCommand;
import com.axon.axondemo.commandside.controller.LoggingCallback;
import com.axon.axondemo.commandside.event.account.MoneyDepositedEvent;
import com.axon.axondemo.commandside.event.account.MoneyWithdrawnEvent;
import com.axon.axondemo.commandside.event.transfer.MoneyTransferCancelledEvent;
import com.axon.axondemo.commandside.event.transfer.MoneyTransferCompletedEvent;
import com.axon.axondemo.commandside.event.transfer.MoneyTransferRequestedEvent;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

@Saga
public class MoneyTransferSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    private String targetAccount;
    private String transferId;

    @StartSaga
    @SagaEventHandler(associationProperty = "transferId")
    public void on(MoneyTransferRequestedEvent event) {
        targetAccount = event.getTargetAccount();
        transferId = event.getTransferId();
        SagaLifecycle.associateWith("transactionId", transferId);
        commandGateway.send(new WithdrawMoneyCommand(event.getSourceAccount(), transferId, event.getAmount()),
                new CommandCallback<WithdrawMoneyCommand, Object>() {
                    @Override
                    public void onSuccess(CommandMessage<? extends WithdrawMoneyCommand> commandMessage, Object result) {

                    }
                    @Override
                    public void onFailure(CommandMessage<? extends WithdrawMoneyCommand> commandMessage, Throwable cause) {
                        cause.printStackTrace();
                        commandGateway.send(new CancelMoneyTransferCommand(event.getTransferId()));
                    }
                });
    }

    @SagaEventHandler(associationProperty = "transactionId")
    public void on(MoneyWithdrawnEvent event) {
        commandGateway.send(new DepositMoneyCommand(targetAccount, event.getTransactionId(), event.getAmount()),
                LoggingCallback.INSTANCE);
    }

    @SagaEventHandler(associationProperty = "transactionId")
    public void on(MoneyDepositedEvent event) {
        commandGateway.send(new CompleteMoneyTransferCommand(transferId),
                LoggingCallback.INSTANCE);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "transferId")
    public void on(MoneyTransferCompletedEvent event) {
    }

    @SagaEventHandler(associationProperty = "transferId")
    public void on(MoneyTransferCancelledEvent event) {
        end();
    }

}
