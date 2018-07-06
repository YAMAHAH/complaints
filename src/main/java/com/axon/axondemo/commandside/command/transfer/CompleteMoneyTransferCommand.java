package com.axon.axondemo.commandside.command.transfer;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CompleteMoneyTransferCommand {

    @TargetAggregateIdentifier
    private String transferId;

    public  CompleteMoneyTransferCommand(String transferId){

        this.transferId = transferId;
    }

    public String getTransferId() {
        return transferId;
    }
}
