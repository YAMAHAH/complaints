package com.axon.axondemo.commandside.event.account;

public abstract class BalanceUpdatedEvent {

    private final String accountId;
    private final int balance;

    public BalanceUpdatedEvent(String accountId, int balance){

        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getBalance() {
        return balance;
    }
}
