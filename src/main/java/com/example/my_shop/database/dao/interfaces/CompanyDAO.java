package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Company;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CompanyDAO {
    void create(Company company) throws SQLException;

    Company getCompany(String companyName) throws SQLException;

    Company getCompany(Long id) throws SQLException;

    List<Company> getAllCompanies () throws SQLException;

    Map<Long, String> getAllCompaniesWithId () throws SQLException;

    void update(Company company) throws SQLException;
}
