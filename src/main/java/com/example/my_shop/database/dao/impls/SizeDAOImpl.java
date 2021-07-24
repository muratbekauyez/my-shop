package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.SizeDAO;
import com.example.my_shop.entity.Size;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SizeDAOImpl implements SizeDAO {
    private static final String GET_SIZE_NAME = "SELECT size_name FROM \"Size\" WHERE id = ?";
    private static final String CHECK_CLOTH_SIZE = "SELECT * FROM \"Cloth_Size\" WHERE cloth_id = ? AND size_id = ?";
    private static final String GET_SIZES_OF_CLOTH = "SELECT size_id, amount, S.size_name  FROM \"Cloth_Size\" CS JOIN \"Size\" S on S.id = CS.size_id WHERE CS.cloth_id = ?";


    private ConnectionPool connectionPool;
    private Connection connection;


    @Override
    public String getSizeName(Long sizeId) throws SQLException {
        String sizeName = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SIZE_NAME)) {
            preparedStatement.setLong(1, sizeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                sizeName = resultSet.getString("size_name");
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return sizeName;
    }

    @Override
    public boolean clothSizeExists(Long clothId, Long sizeId) throws SQLException {
        boolean clothSizeExists = false;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CLOTH_SIZE)) {
            preparedStatement.setLong(1, clothId);
            preparedStatement.setLong(2, sizeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                if(amount > 0) {
                    clothSizeExists =  true;
                }
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return clothSizeExists;
    }

    @Override
    public int amountOfClothInSize(Long clothId, Long sizeId) throws SQLException {
        int amount = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CLOTH_SIZE)) {
            preparedStatement.setLong(1, clothId);
            preparedStatement.setLong(2, sizeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("amount");
                connectionPool.returnConnection(connection);
                return amount;
            }
        }
        connectionPool.returnConnection(connection);
        return amount;
    }

    @Override
    public List<Size> allSizesOfCloth (Long clothId) throws SQLException {
        List<Size> allSizesOfCloth = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SIZES_OF_CLOTH)) {
            preparedStatement.setLong(1, clothId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size size = new Size();
                size.setId(resultSet.getLong("size_id"));
                size.setSizeName(resultSet.getString("size_name"));
                if(resultSet.getInt("amount") > 0){
                    allSizesOfCloth.add(size);
                }
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return allSizesOfCloth;
    }


    @Override
    public List<Size> allSizes() {
        List<Size> allSizes = new ArrayList<>();
        allSizes.add(new Size(1L, "S"));
        allSizes.add(new Size(2L, "M"));
        allSizes.add(new Size(3L, "L"));
        allSizes.add(new Size(4L, "XL"));
        return allSizes;
    }
}
