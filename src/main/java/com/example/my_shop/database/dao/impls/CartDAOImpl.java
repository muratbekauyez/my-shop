package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.CartDAO;
import com.example.my_shop.entity.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    private static final String ADD_PRODUCT = "INSERT INTO \"Cart\" (user_id, product_id, amount, size_id) VALUES (?,?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE \"Cart\" SET amount = ? WHERE user_id = ? AND product_id = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM \"Cart\" WHERE user_id = ? AND product_id = ?";
    private static final String DELETE_USER_PRODUCTS = "DELETE FROM \"Cart\" WHERE user_id = ?";
    private static final String GET_USER_PRODUCTS = "SELECT id, product_id, amount, size_id FROM \"Cart\" WHERE user_id = ?";
    private static final String GET_USER_PRODUCT = "SELECT id, amount FROM \"Cart\" WHERE user_id = ? AND product_id = ? AND size_id = ?";
    private static final String GET_SUM_CART = "SELECT sum(price * \"Cart\".amount) FROM \"Cart\" JOIN \"Clothes\" C on C.id = \"Cart\".product_id WHERE user_id = ?";

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public void create(Cart cart) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setLong(1, cart.getUserId());
            preparedStatement.setLong(2, cart.getProductId());
            preparedStatement.setInt(3, cart.getAmount());
            preparedStatement.setLong(4, cart.getSizeId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void updateAmount(Cart cart) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setInt(1, cart.getAmount());
            preparedStatement.setLong(2, cart.getUserId());
            preparedStatement.setLong(3, cart.getProductId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void delete(Cart cart) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setLong(1, cart.getUserId());
            preparedStatement.setLong(2, cart.getProductId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteAllUserProducts(Long userId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_PRODUCTS)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Cart> getCartProducts(Long userId) throws SQLException {
        List<Cart> cartsOfUser = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_PRODUCTS)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getLong("id"));
                cart.setProductId(resultSet.getLong("product_id"));
                cart.setUserId(userId);
                cart.setAmount(resultSet.getInt("amount"));
                cart.setSizeId(resultSet.getLong("size_id"));
                cartsOfUser.add(cart);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return cartsOfUser;
    }


    @Override
    public boolean isProductAlreadyInCart(Cart cart) throws SQLException {
        boolean cartExists = false;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_PRODUCT)) {
            preparedStatement.setLong(1, cart.getUserId());
            preparedStatement.setLong(2, cart.getProductId());
            preparedStatement.setLong(3, cart.getSizeId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartExists = true;
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return cartExists;
    }

    @Override
    public int getSumOfCart(Long userId) throws SQLException {
        int sum = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SUM_CART)) {
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                sum = resultSet.getInt("sum");
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return sum;
    }
}
