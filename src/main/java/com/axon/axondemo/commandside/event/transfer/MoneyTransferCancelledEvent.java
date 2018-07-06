package com.axon.axondemo.commandside.event.transfer;

public class MoneyTransferCancelledEvent {
    private String transferId;

    public MoneyTransferCancelledEvent(){
        this("");
    }

    public MoneyTransferCancelledEvent(String transferId) {
        this.transferId = transferId;
    }

    public String getTransferId() {
        return transferId;
    }
}
