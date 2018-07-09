package com.axon.axondemo.queryside.handler;

import com.axon.axondemo.commandside.event.order.OrderCreatedEvent;
import com.axon.axondemo.queryside.entity.OrderEntry;
import com.axon.axondemo.queryside.entity.OrderProductEntry;
import com.axon.axondemo.queryside.repository.OrderEntryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


@Component
public class OrderEventHandler {

    private static final Logger LOGGER = getLogger(OrderEventHandler.class);

    @Autowired
    private OrderEntryRepository repository;

    @EventHandler
    public void on(OrderCreatedEvent event){
        Map<String, OrderProductEntry> map = new HashMap<>();
        final Double[] payment = {0.0};
        event.getProducts().forEach((id, product)->{
            payment[0] += product.getPrice() * product.getAmount();
            map.put(id,
                    new OrderProductEntry(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            product.getAmount()));
        });
        OrderEntry order = new OrderEntry(event.getOrderId().toString(), event.getUsername(),payment[0], map);
        repository.save(order);
    }

}
