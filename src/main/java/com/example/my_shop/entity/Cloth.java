package com.example.my_shop.entity;


import java.io.InputStream;
import java.util.Objects;

public class Cloth {
    private Long id;
    private String vendorCode;
    private int price;
    private Long companyId;
    private InputStream image;
    private String imageFromDd;
    private Long genderID;

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public InputStream getImage() {
        return image;
    }

    public String getImageFromDd() {
        return imageFromDd;
    }

    public void setImageFromDd(String imageFromDd) {
        this.imageFromDd = imageFromDd;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public Long getGenderID() {
        return genderID;
    }

    public void setGenderID(Long genderID) {
        this.genderID = genderID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cloth cloth = (Cloth) o;
        return price == cloth.price && Objects.equals(id, cloth.id) && Objects.equals(vendorCode, cloth.vendorCode) && Objects.equals(companyId, cloth.companyId)  && Objects.equals(imageFromDd, cloth.imageFromDd) && Objects.equals(genderID, cloth.genderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vendorCode, price, companyId, imageFromDd, genderID);
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "id=" + id +
                ", vendorCode='" + vendorCode + '\'' +
                ", price=" + price +
                ", companyId=" + companyId +
                ", genderID=" + genderID +
                '}';
    }
}
