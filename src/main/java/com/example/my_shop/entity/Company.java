package com.example.my_shop.entity;

import java.util.Objects;

public class Company {
    private Long id;
    private String companyName;
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(companyName, company.companyName) && Objects.equals(country, company.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, country);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
