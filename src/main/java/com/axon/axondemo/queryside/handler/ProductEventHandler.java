package com.axon.axondemo.queryside.handler;

import com.axon.axondemo.commandside.event.Product.ProductCreatedEvent;
import com.axon.axondemo.commandside.event.Product.ProductReservedEvent;
import com.axon.axondemo.commandside.event.order.ReserveCancelledEvent;
import com.axon.axondemo.queryside.entity.ProductEntry;
import com.axon.axondemo.queryside.repository.saga.ProductEntryRepository;
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


    @EventHandler
    public void on(ProductReservedEvent event){
        ProductEntry product = repository.findById(event.getProductId()).get();
        if(product==null){
            LOGGER.error("Cannot find product with id {}", product.getId());
            return;
        }
        product.setStock(event.getAmount());
        repository.save(product);
    }

    @EventHandler
    public void on(ReserveCancelledEvent event){
        ProductEntry product = repository.findById(event.getProductId()).get();
        if(product==null){
            LOGGER.error("Cannot find product with id {}", product.getId());
            return;
        }
        product.setStock(event.getAmount());
        repository.save(product);
    }
}