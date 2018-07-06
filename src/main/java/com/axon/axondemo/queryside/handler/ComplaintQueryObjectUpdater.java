package com.axon.axondemo.queryside.handler;

import com.axon.axondemo.commandside.event.ComplaintFiledEvent;
import com.axon.axondemo.commandside.repository.ComplaintQueryObjectRepository;
import com.axon.axondemo.queryside.entity.ComplaintQueryObject;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ComplaintQueryObjectUpdater {

    private final ComplaintQueryObjectRepository complaintsQueryObjectRepository;

    public ComplaintQueryObjectUpdater(ComplaintQueryObjectRepository complaintsQueryObjectRepository) {
        this.complaintsQueryObjectRepository = complaintsQueryObjectRepository;
    }

    @EventHandler
    public void on(ComplaintFiledEvent event) {
        complaintsQueryObjectRepository.save(new ComplaintQueryObject(event.getId(), event.getCompany(), event.getDescription()));
    }
}