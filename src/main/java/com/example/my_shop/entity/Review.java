package com.example.my_shop.entity;

import java.util.Date;
import java.util.Objects;

public class Review {
    private Long id;
    private Long userId;
    private Long productId;
    private String content;
    private Date date;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(userId, review.userId) && Objects.equals(productId, review.productId) && Objects.equals(content, review.content) && Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, content, date);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
