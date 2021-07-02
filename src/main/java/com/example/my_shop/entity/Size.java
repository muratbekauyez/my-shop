package com.example.my_shop.entity;

import java.util.Objects;

public class Size {
    Long id;
    String sizeName;

    public Size(Long id, String sizeName) {
        this.id = id;
        this.sizeName = sizeName;
    }

    public Size() {

    }

    public Long getId() {
        return id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return Objects.equals(id, size.id) && Objects.equals(sizeName, size.sizeName);
    }

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", sizeName='" + sizeName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sizeName);
    }
}
