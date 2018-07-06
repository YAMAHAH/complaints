package com.axon.axondemo.commandside.aggregate.account;

import com.axon.axondemo.commandside.command.account.CreateAccountCommand;
import com.axon.axondemo.commandside.command.account.DepositMoneyCommand;
import com.axon.axondemo.commandside.command.account.WithdrawMoneyCommand;
import com.axon.axondemo.commandside.event.account.AccountCreatedEvent;
import com.axon.axondemo.commandside.event.account.BalanceUpdatedEvent;
import com.axon.axondemo.commandside.event.account.MoneyDepositedEvent;
import com.axon.axondemo.commandside.event.account.MoneyWithdrawnEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Account {

    @AggregateIdentifier
    private String accountId;
    private int balance;
    private int overdraftLimit;

    public Account(){

    }

    @CommandHandler
    public Account(CreateAccountCommand cmd) {
        apply(new AccountCreatedEvent(cmd.getAccountId(), cmd.getOverdraftLimit()));
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand cmd) throws OverdraftLimitExceededException {
        if (balance + overdraftLimit < cmd.getAmount()) {
            throw new OverdraftLimitExceededException();
        }
        apply(new MoneyWithdrawnEvent(accountId, cmd.getTransactionId(), cmd.getAmount(), balance - cmd.getAmount()));
    }

    @CommandHandler
    public void handle(DepositMoneyCommand cmd) {
        apply(new MoneyDepositedEvent(accountId, cmd.getTransactionId(), cmd.getAmount(), balance + cmd.getAmount()));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent event) {
        this.accountId = event.getAccountId();
        this.overdraftLimit = event.getOverdraftLimit();
    }

    @EventSourcingHandler
    protected void on(BalanceUpdatedEvent event) {
        this.balance = event.getBalance();
    }

}
