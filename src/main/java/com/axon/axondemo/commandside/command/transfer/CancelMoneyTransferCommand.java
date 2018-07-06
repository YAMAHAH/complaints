package com.axon.axondemo.commandside.command.transfer;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CancelMoneyTransferCommand {

    @TargetAggregateIdentifier
    private String transferId;

    public  CancelMoneyTransferCommand(String transferId){

        this.transferId = transferId;
    }

    public String getTransferId() {
        return transferId;
    }
}
