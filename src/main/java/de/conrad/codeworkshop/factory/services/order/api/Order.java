package de.conrad.codeworkshop.factory.services.order.api;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import static de.conrad.codeworkshop.factory.services.order.api.OrderStatus.*;

/**
 * @author Andreas Hartmann
 */
public class Order {
    @NotNull
    private List<Position> positions;
    private OrderConfirmation orderConfirmation;
    private OrderStatus status = PENDING;

    public void validate() {
        if (positions.isEmpty() || status != PENDING) {
            status = DECLINED;
            return;
        }
        for (Position pos : positions) {
            if (!(isBetween6or9Size(pos.getProductId()) && isDividableBy10(pos.getQuantity()) || isQuantityBetween0And1(pos.getQuantity()) || isExactly4242(pos.getQuantity()))) {
                status = DECLINED;
                return;
            }
            status = ACCEPTED;
        }

    }

    private boolean isExactly4242(BigDecimal quantity) {
        return quantity.equals(new BigDecimal("42.42"));
    }

    private boolean isDividableBy10(BigDecimal quantity) {
        return quantity.intValue() % 10 == 0;
    }

    private boolean isQuantityBetween0And1(BigDecimal quantity) {
        return quantity.compareTo(BigDecimal.valueOf(0)) > 0 && quantity.compareTo(BigDecimal.valueOf(1)) < 0;
    }

    private boolean isBetween6or9Size(Integer productId) {
        return String.valueOf(productId).length() >= 6 && String.valueOf(productId).length() <= 9;
    }

    public void setOrderConfirmation(final OrderConfirmation orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
