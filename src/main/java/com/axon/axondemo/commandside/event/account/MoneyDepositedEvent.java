package com.axon.axondemo.commandside.event.account;

public class MoneyDepositedEvent extends BalanceUpdatedEvent {
    private final String accountId;
    private final String transactionId;
    private final int amount;
    private final int balance;

    public MoneyDepositedEvent(){
        this("","",0,0);
    }

    public MoneyDepositedEvent(String accountId, String transactionId, int amount, int balance) {
        super(accountId,balance);
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.balance = balance;
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

    public int getBalance() {
        return balance;
    }
}
