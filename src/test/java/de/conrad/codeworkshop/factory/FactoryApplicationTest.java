package de.conrad.codeworkshop.factory;

import de.conrad.codeworkshop.factory.services.order.OrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FactoryApplicationTest {
    @Autowired
    private OrderController orderController;

    @Test
    public void contextLoads() {
        assertThat(orderController).isNotNull();
    }

}