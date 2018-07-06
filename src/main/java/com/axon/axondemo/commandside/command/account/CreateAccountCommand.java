package com.axon.axondemo.commandside.command.account;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private final String accountId;
    private final int overdraftLimit;

    public CreateAccountCommand(String accountId , int overdraftLimit){

        this.accountId = accountId;
        this.overdraftLimit = overdraftLimit;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getOverdraftLimit() {
        return overdraftLimit;
    }
}
