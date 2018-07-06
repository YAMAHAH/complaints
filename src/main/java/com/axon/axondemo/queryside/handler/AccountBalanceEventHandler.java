package com.axon.axondemo.queryside.handler;

import com.axon.axondemo.commandside.event.account.BalanceUpdatedEvent;
import com.axon.axondemo.queryside.entity.AccountBalance;
import com.axon.axondemo.queryside.repository.AccountBalanceRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AccountBalanceEventHandler {

    private final AccountBalanceRepository repository;

    @Autowired
    public AccountBalanceEventHandler(AccountBalanceRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(BalanceUpdatedEvent event) {
//        Channel channel = connectionFactory.createConnection().createChannel(isTransactional);
        repository.save(new AccountBalance(event.getAccountId(), event.getBalance()));
    }

    @GetMapping("/balance/{id}")
    public Optional<AccountBalance> getBalance(@PathVariable String id) {
        return repository.findById(id);
    }
}