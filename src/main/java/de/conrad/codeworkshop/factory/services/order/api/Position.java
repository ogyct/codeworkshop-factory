package de.conrad.codeworkshop.factory.services.order.api;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Andreas Hartmann
 */
public class Position {
    @NotNull
    private Integer productId;
    @NotNull
    private BigDecimal quantity;

    public Position() {
    }

    public Position(@NotNull Integer productId, @NotNull BigDecimal quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
