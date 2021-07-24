package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Company;

import java.sql.SQLException;

public interface CompanyDAO {
    void create(Company company) throws SQLException;

    void update(Company company) throws SQLException;
}
