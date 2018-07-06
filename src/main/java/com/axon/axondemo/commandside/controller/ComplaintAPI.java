package com.axon.axondemo.commandside.controller;

import com.axon.axondemo.queryside.entity.ComplaintQueryObject;
import com.axon.axondemo.commandside.repository.ComplaintQueryObjectRepository;
import com.axon.axondemo.commandside.command.FileComplaintCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/complaints")
@RestController
public class ComplaintAPI {

    private final CommandGateway commandGateway;
    private final ComplaintQueryObjectRepository complaintsQueryObjectRepository;

    public ComplaintAPI(CommandGateway commandGateway, ComplaintQueryObjectRepository complaintsQueryObjectRepository) {
        this.commandGateway = commandGateway;
        this.complaintsQueryObjectRepository = complaintsQueryObjectRepository;
    }

    @PostMapping
    public CompletableFuture<String> fileComplaint(@RequestBody Map<String, String> request) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new FileComplaintCommand(id, request.get("company"), request.get("description")));
    }

    @GetMapping
    public List<ComplaintQueryObject> findAll() {
        return complaintsQueryObjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ComplaintQueryObject find(@PathVariable String id) {
        return complaintsQueryObjectRepository.getOne(id);
    }
}
