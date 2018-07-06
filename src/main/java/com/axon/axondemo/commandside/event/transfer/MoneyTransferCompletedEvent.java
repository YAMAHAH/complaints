package com.axon.axondemo.commandside.event.transfer;

public class MoneyTransferCompletedEvent {
    private String transferId;

    public MoneyTransferCompletedEvent(){
        this("");
    }

    public MoneyTransferCompletedEvent(String transferId) {
        this.transferId = transferId;
    }

    public String getTransferId() {
        return transferId;
    }
}
