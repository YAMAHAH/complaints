package com.axon.axondemo.commandside.handler.order;

import com.axon.axondemo.commandside.aggregate.order.OrderAggregate;
import com.axon.axondemo.commandside.aggregate.product.ProductAggregate;
import com.axon.axondemo.commandside.command.order.ConfirmOrderCommand;
import com.axon.axondemo.commandside.command.order.CreateOrderCommand;
import com.axon.axondemo.commandside.command.order.RollbackOrderCommand;
import com.axon.axondemo.domain.model.OrderProduct;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class OrderHandler {

    private static final Logger LOGGER = getLogger(OrderHandler.class);

    @Autowired
    private Repository<OrderAggregate> repository;

    @Autowired
    private Repository<ProductAggregate> productRepository;

    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        Map<String, OrderProduct> products = new HashMap<>();
        command.getProducts().forEach((productId, number) -> {
            LOGGER.debug("Loading product information with productId: {}", productId);
            Aggregate<ProductAggregate> aggregate = productRepository.load(productId);
            products.put(productId,
                    new OrderProduct(productId,
                            aggregate.invoke(productAggregate -> productAggregate.getName()),
                            aggregate.invoke(productAggregate -> productAggregate.getPrice()),
                            number));
        });
        repository.newInstance(() -> new OrderAggregate(command.getOrderId(), command.getUsername(), products));
    }

    @CommandHandler
    public void handle(RollbackOrderCommand command){
        Aggregate<OrderAggregate> aggregate = repository.load(command.getOrderId().getIdentifier());
        aggregate.execute(aggregateRoot->aggregateRoot.delete());
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command){
        Aggregate<OrderAggregate> aggregate = repository.load(command.getId().getIdentifier());
        aggregate.execute(aggregateRoot->aggregateRoot.confirm());
    }
}

