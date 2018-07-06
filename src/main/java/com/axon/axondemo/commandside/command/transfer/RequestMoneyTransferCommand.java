package com.axon.axondemo.commandside.command.transfer;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class RequestMoneyTransferCommand {

    @TargetAggregateIdentifier
    private final String transferId;
    private final String sourceAccount;
    private final String targetAccount;
    private final int amount;



    public RequestMoneyTransferCommand(String transferId, String sourceAccount,
                                       String targetAccount, int amount) {

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
