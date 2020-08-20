package de.conrad.codeworkshop.factory.services.factory;

import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FactoryServiceImplTest {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Test
    void removeEntry() {
        Order order = new Order();
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(123456, new BigDecimal("10")));
        positions.add(new Position(1234567, new BigDecimal("0.1")));
        positions.add(new Position(12345678, new BigDecimal("42.42")));
        order.setPositions(positions);
        factoryService.removeEntry(order);
        System.out.println(Thread.currentThread().getName());

    }
}