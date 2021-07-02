package com.example.my_shop.entity;

import java.util.Objects;

public class OrderDetails {
    private Long id;
    private Long orderId;
    private Long productId;
    private Long sizeId;
    private int amount;
    private int productPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return amount == that.amount && productPrice == that.productPrice && Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(sizeId, that.sizeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, productId, sizeId, amount, productPrice);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", sizeId=" + sizeId +
                ", amount=" + amount +
                ", productPrice=" + productPrice +
                '}';
    }
}
