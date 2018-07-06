package com.axon.axondemo.commandside.event.transfer;

public class MoneyTransferRequestedEvent {

    private String transferId="";
    private String sourceAccount="";
    private String targetAccount="";
    private int amount=0;

    public MoneyTransferRequestedEvent(){

    }

    public MoneyTransferRequestedEvent(String transferId, String sourceAccount, String targetAccount, int amount) {
        this.transferId = transferId;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
    }

    public String getTransferId() {
        return transferId;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public int getAmount() {
        return amount;
    }
}
