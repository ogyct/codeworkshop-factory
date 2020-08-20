package de.conrad.codeworkshop.factory.services.factory;

import de.conrad.codeworkshop.factory.services.notification.NotificationService;
import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.OrderStatus;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Andreas Hartmann
 */
@Service
public class FactoryServiceImpl implements FactoryService {

    private final NotificationService notificationService;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private ConcurrentLinkedQueue<Order> manufacturingQueue = new ConcurrentLinkedQueue<>();

    public FactoryServiceImpl(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void enqueue(final Order order) {
        order.setStatus(OrderStatus.IN_PROGRESS);
        manufacturingQueue.add(order);
    }

    @Override
    @Async
    @SneakyThrows
    public void removeEntry(Order order) {
        System.out.println(Thread.currentThread().getName());
        manufacturingQueue.remove(order);
        Thread.sleep(5000);
        notificationService.notifyCustomer(order);
    }

}
