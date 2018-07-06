package com.axon.axondemo.commandside.command.account;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class WithdrawMoneyCommand {

    @TargetAggregateIdentifier
    private final String accountId;
    private final String transactionId;
    private final int amount;

    public WithdrawMoneyCommand(String accountId, String transactionId, int amount){

        this.accountId = accountId;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getAmount() {
        return amount;
    }
}
