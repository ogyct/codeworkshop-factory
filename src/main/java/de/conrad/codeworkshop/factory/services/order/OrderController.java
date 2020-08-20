package de.conrad.codeworkshop.factory.services.order;

import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.OrderConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Andreas Hartmann
 */
@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService factoryOrderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.factoryOrderService = orderService;
    }

    @PostMapping("/create")
    public OrderConfirmation createOrder(@Valid @RequestBody final Order order) {
        return factoryOrderService.createOrder(order);
    }
}