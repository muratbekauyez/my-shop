package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.entity.Cloth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ClothDAOImpl implements ClothDAO {
    private static final String ADD_CLOTH = "INSERT INTO \"Clothes\" (vendor_code, price, company_id, image, gender_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE_CLOTH = "UPDATE \"Clothes\" SET vendor_code = ?, price = ?, company_id = ?, image = ?, gender_id = ? WHERE id=?";
    private static final String GET_CLOTH = "SELECT vendor_code, price, company_id, image, gender_id FROM \"Clothes\" WHERE id = ?";
    private static final String GET_CLOTHES = "SELECT id,vendor_code, company_id, price, image, gender_id FROM \"Clothes\" ORDER BY id";
    private static final String GET_FILTERED_CLOTHES_BY_SIZE = "SELECT * FROM \"Clothes\" C JOIN \"Cloth_Size\" CS on C.id = CS.cloth_id WHERE CS.size_id = ? AND amount > 0";
    private static final String GET_FILTERED_CLOTHES_BY_COMPANY = "SELECT * FROM \"Clothes\" C JOIN \"Cloth_Size\" CS on C.id = CS.cloth_id WHERE C.company_id = ? AND amount > 0";
    private static final String GET_AVAILABLE_CLOTHES = "SELECT DISTINCT id, vendor_code, image, price, company_id, gender_id FROM \"Clothes\" C JOIN \"Cloth_Size\" CS on C.id = CS.cloth_id WHERE amount > 0 ";
    private static final String ADD_CLOTH_SIZE = "INSERT INTO \"Cloth_Size\" (cloth_id, size_id, amount) VALUES (?,?,?)";
    private static final String SET_0_CLOTH_SIZE = "UPDATE \"Cloth_Size\" SET amount = 0 WHERE cloth_id=?";


    private ConnectionPool connectionPool;
    private Connection connection;


    @Override
    public void create(Cloth cloth) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CLOTH)) {
            preparedStatement.setString(1, cloth.getVendorCode());
            preparedStatement.setInt(2, cloth.getPrice());
            preparedStatement.setLong(3, cloth.getCompanyId());
            preparedStatement.setBinaryStream(4, cloth.getImage());
            preparedStatement.setLong(5, cloth.getGenderID());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public void update(Cloth cloth) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLOTH)) {
            preparedStatement.setString(1, cloth.getVendorCode());
            preparedStatement.setInt(2, cloth.getPrice());
            preparedStatement.setLong(3, cloth.getCompanyId());
            preparedStatement.setBinaryStream(4, cloth.getImage());
            preparedStatement.setLong(5, cloth.getGenderID());
            preparedStatement.setLong(6, cloth.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public Cloth getCloth(Long id) throws SQLException {
        Cloth cloth = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CLOTH)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cloth = new Cloth();
                cloth.setId(id);
                cloth.setVendorCode(resultSet.getString("vendor_code"));
                cloth.setPrice(resultSet.getInt("price"));
                cloth.setCompanyId(resultSet.getLong("company_id"));
                cloth.setImage(resultSet.getBinaryStream("image"));
                cloth.setImageFromDd(Base64.getEncoder().encodeToString(resultSet.getBytes("image")));
                cloth.setGenderID(resultSet.getLong("gender_id"));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return cloth;
    }



    public List<Cloth> getAllClothes() throws SQLException {
        List<Cloth> allClothes = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CLOTHES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cloth cloth = new Cloth();
                cloth.setId(resultSet.getLong("id"));
                cloth.setVendorCode(resultSet.getString("vendor_code"));
                cloth.setPrice(resultSet.getInt("price"));
                cloth.setImage(resultSet.getBinaryStream("image"));
                cloth.setCompanyId(resultSet.getLong("company_id"));
                cloth.setImageFromDd(Base64.getEncoder().encodeToString(resultSet.getBytes("image")));
                cloth.setGenderID(resultSet.getLong("gender_id"));
                allClothes.add(cloth);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return allClothes;
    }

    @Override
    public List<Cloth> getAvailableClothes() throws SQLException {
        List<Cloth> availableClothes = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_AVAILABLE_CLOTHES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cloth cloth = new Cloth();
                cloth.setId(resultSet.getLong("id"));
                cloth.setVendorCode(resultSet.getString("vendor_code"));
                cloth.setPrice(resultSet.getInt("price"));
                cloth.setImage(resultSet.getBinaryStream("image"));
                cloth.setCompanyId(resultSet.getLong("company_id"));
                cloth.setImageFromDd(Base64.getEncoder().encodeToString(resultSet.getBytes("image")));
                cloth.setGenderID(resultSet.getLong("gender_id"));
                if (!availableClothes.contains(cloth)) {
                    availableClothes.add(cloth);
                }
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return availableClothes;
    }


    @Override
    public List<Cloth> filterClothesWithSize (String sizeId) throws SQLException{
        List<Cloth> filteredClothes = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_FILTERED_CLOTHES_BY_SIZE)){
            preparedStatement.setLong(1, Long.parseLong(sizeId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Cloth cloth = new Cloth();
                cloth.setId(resultSet.getLong("id"));
                cloth.setVendorCode(resultSet.getString("vendor_code"));
                cloth.setPrice(resultSet.getInt("price"));
                cloth.setImage(resultSet.getBinaryStream("image"));
                cloth.setCompanyId(resultSet.getLong("company_id"));
                cloth.setImageFromDd(Base64.getEncoder().encodeToString(resultSet.getBytes("image")));
                cloth.setGenderID(resultSet.getLong("gender_id"));
                if (!filteredClothes.contains(cloth)) {
                    filteredClothes.add(cloth);
                }
            }

        }finally {
            connectionPool.returnConnection(connection);
        }

        return filteredClothes;
    }

    @Override
    public List<Cloth> filterClothesWithCompany (String companyId) throws SQLException{
        List<Cloth> filteredClothes = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_FILTERED_CLOTHES_BY_COMPANY)){
            preparedStatement.setLong(1, Long.parseLong(companyId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Cloth cloth = new Cloth();
                cloth.setId(resultSet.getLong("id"));
                cloth.setVendorCode(resultSet.getString("vendor_code"));
                cloth.setPrice(resultSet.getInt("price"));
                cloth.setImage(resultSet.getBinaryStream("image"));
                cloth.setCompanyId(resultSet.getLong("company_id"));
                cloth.setImageFromDd(Base64.getEncoder().encodeToString(resultSet.getBytes("image")));
                cloth.setGenderID(resultSet.getLong("gender_id"));
                if (!filteredClothes.contains(cloth)) {
                    filteredClothes.add(cloth);
                }
            }

        }finally {
            connectionPool.returnConnection(connection);
        }

        return filteredClothes;
    }





    @Override
    public void addClothAmount(Long clothId, Long sizeId, int amount) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CLOTH_SIZE)) {
            preparedStatement.setLong(1, clothId);
            preparedStatement.setLong(2, sizeId);
            preparedStatement.setInt(3, amount);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void setAmountToZero(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SET_0_CLOTH_SIZE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
