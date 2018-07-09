package com.axon.axondemo.queryside.handler;

import com.axon.axondemo.commandside.event.Product.ProductCreatedEvent;
import com.axon.axondemo.queryside.entity.ProductEntry;
import com.axon.axondemo.queryside.repository.ProductEntryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class ProductEventHandler {

    private static final Logger LOGGER = getLogger(ProductEventHandler.class);

    @Autowired
    ProductEntryRepository repository;

    @EventHandler
    public void on(ProductCreatedEvent event){
        // update the data in the cache or db of the query side
        LOGGER.debug("repository data is updated");
        repository.save(new ProductEntry(event.getId(), event.getName(), event.getPrice(), event.getStock()));
    }
}