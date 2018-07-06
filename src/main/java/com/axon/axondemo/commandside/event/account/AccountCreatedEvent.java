package com.axon.axondemo.commandside.event.account;

public class AccountCreatedEvent {

    private final String accountId;
    private final int overdraftLimit;

    public AccountCreatedEvent(){
        this("",0);
    }

    public AccountCreatedEvent(String accountId, int overdraftLimit) {
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
