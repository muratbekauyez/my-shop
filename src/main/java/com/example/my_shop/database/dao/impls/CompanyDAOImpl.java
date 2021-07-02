package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.CompanyDAO;
import com.example.my_shop.entity.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyDAOImpl implements CompanyDAO {
    private ConnectionPool connectionPool;
    private Connection connection;


    @Override
    public void create(Company company) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String ADD_COMPANY = "INSERT INTO \"Company\" (company_name, country) VALUES (?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_COMPANY)){
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCountry());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public void update(Company company) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String UPDATE_COMPANY = "UPDATE \"Company\" SET company_name = ?, country = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMPANY)){
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCountry());
            preparedStatement.setLong(3, company.getId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public Company getCompany(String companyName) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_COMPANY = "SELECT company_name, country FROM \"Company\" WHERE company_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_COMPANY);
        preparedStatement.setString(1, companyName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Company company = new Company();
            company.setCompanyName(resultSet.getString("company_name"));
            company.setCountry(resultSet.getString("country"));
            connectionPool.returnConnection(connection);
            return company;
        }
        connectionPool.returnConnection(connection);
        return null;
    }

    @Override
    public Company getCompany(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_COMPANY = "SELECT company_name, country FROM \"Company\" WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_COMPANY);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Company company = new Company();
            company.setCompanyName(resultSet.getString("company_name"));
            company.setCountry(resultSet.getString("country"));
            connectionPool.returnConnection(connection);
            return company;
        }
        connectionPool.returnConnection(connection);
        return null;
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        List<Company> allCompanies = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_COMPANIES = "SELECT id, company_name, country FROM \"Company\" ORDER BY id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_COMPANIES)){
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Company company = new Company();
                company.setId(resultSet.getLong("id"));
                company.setCompanyName(resultSet.getString("company_name"));
                company.setCountry(resultSet.getString("country"));
                allCompanies.add(company);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return allCompanies;
    }

    @Override
    public Map<Long, String> getAllCompaniesWithId() throws SQLException {
        Map<Long, String> allCompanies = new HashMap<Long,String>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_COMPANIES = "SELECT id, company_name, country FROM \"Company\"";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_COMPANIES)){
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                allCompanies.put(resultSet.getLong("id"), resultSet.getString("company_name"));
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return allCompanies;
    }
}
