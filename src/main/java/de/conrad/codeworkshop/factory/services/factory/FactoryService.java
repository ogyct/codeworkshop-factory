package de.conrad.codeworkshop.factory.services.factory;

import de.conrad.codeworkshop.factory.services.order.api.Order;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;

public interface FactoryService {
    void enqueue(Order order);

    @Async
    @SneakyThrows
    void removeEntry(Order order);
}
