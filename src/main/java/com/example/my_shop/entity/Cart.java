package com.example.my_shop.entity;

import java.util.Objects;

public class Cart {
    private Long id;
    private Long userId;
    private Long productId;
    private int amount;
    private Long sizeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        Cart cart = (Cart) o;
        return amount == cart.amount && Objects.equals(id, cart.id) && Objects.equals(userId, cart.userId) && Objects.equals(productId, cart.productId) && Objects.equals(sizeId, cart.sizeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, amount, sizeId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", amount=" + amount +
                ", sizeId=" + sizeId +
                '}';
    }
}
