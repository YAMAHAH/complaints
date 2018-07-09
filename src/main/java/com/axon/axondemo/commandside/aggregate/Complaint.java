package com.axon.axondemo.commandside.aggregate;

import com.axon.axondemo.commandside.event.ComplaintFiledEvent;
import com.axon.axondemo.commandside.command.FileComplaintCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


@Aggregate
public class Complaint {

    @AggregateIdentifier
    private String complaintId;

    public Complaint() {
    }

    @CommandHandler
    public Complaint(FileComplaintCommand command) {
//         Assert.hasLength(commandside.getCompany());
        apply(new ComplaintFiledEvent(command.getId(), command.getCompany(), command.getDescription()));
    }

    @EventSourcingHandler
    protected void on(ComplaintFiledEvent event) {
        this.complaintId = event.getId();
    }

}

