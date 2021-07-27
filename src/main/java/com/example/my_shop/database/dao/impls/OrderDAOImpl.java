package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.OrderDAO;
import com.example.my_shop.database.dao.interfaces.SizeDAO;
import com.example.my_shop.entity.Order;
import com.example.my_shop.entity.OrderDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {
    private static final String ADD_ORDER = "INSERT INTO \"Order\" (date, total_price, status_id, user_id) VALUES (?,?,?,?) RETURNING id";
    private static final String ADD_ORDER_DETAILS = "INSERT INTO \"Order_Details\" (order_id, product_id, size_id, amount, product_price) VALUES (?,?,?,?,?)";
    private static final String REDUCE_AMOUNT = "UPDATE \"Cloth_Size\" SET amount = ? WHERE cloth_id = ? AND size_id = ?";
    private static final String GET_USER_ORDERS = "SELECT id, date, total_price, status_id FROM \"Order\" WHERE user_id = ?";
    private static final String GET_ORDER_DETAILS = "SELECT id,product_id, size_id, amount, product_price FROM \"Order_Details\" WHERE order_id = ?";

    private ConnectionPool connectionPool;
    private Connection connection;
    private final SizeDAO sizeDAO = new SizeDAOImpl();


    @Override
    public Long create(Order order) throws SQLException {
        Long id = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)) {
            preparedStatement.setDate(1, (Date) order.getDate());
            preparedStatement.setInt(2, order.getTotalPrice());
            preparedStatement.setLong(3, order.getStatusId());
            preparedStatement.setLong(4, order.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return id;
    }

    @Override
    public void addOrderDetails(List<OrderDetails> orderDetailsList) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_DETAILS)) {
            for (OrderDetails orderDetail : orderDetailsList) {
                preparedStatement.setLong(1, orderDetail.getOrderId());
                preparedStatement.setLong(2, orderDetail.getProductId());
                preparedStatement.setLong(3, orderDetail.getSizeId());
                preparedStatement.setInt(4, orderDetail.getAmount());
                preparedStatement.setInt(5, orderDetail.getProductPrice());
                preparedStatement.executeUpdate();
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void reduceAmountOfClothes(List<OrderDetails> orderDetailsList) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REDUCE_AMOUNT);) {
            for (OrderDetails orderDetail : orderDetailsList) {
                int size = sizeDAO.getAmountOfClothInSize(orderDetail.getProductId(), orderDetail.getSizeId());
                preparedStatement.setLong(1, size - orderDetail.getAmount());
                preparedStatement.setLong(2, orderDetail.getProductId());
                preparedStatement.setLong(3, orderDetail.getSizeId());
                preparedStatement.executeUpdate();
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ORDERS)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setDate(resultSet.getDate("date"));
                order.setTotalPrice(resultSet.getInt("total_price"));
                order.setStatusId(resultSet.getLong("status_id"));
                orders.add(order);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    @Override
    public List<OrderDetails> getOrderDetails(Long orderId) throws SQLException {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_DETAILS)){
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setId(resultSet.getLong("id"));
                orderDetails.setOrderId(orderId);
                orderDetails.setProductId(resultSet.getLong("product_id"));
                orderDetails.setSizeId(resultSet.getLong("size_id"));
                orderDetails.setAmount(resultSet.getInt("amount"));
                orderDetails.setProductPrice(resultSet.getInt("product_price"));
                orderDetailsList.add(orderDetails);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return orderDetailsList;
    }

    public String statusName(Long statusId) {
        Map<Long, String> statuses = new HashMap<>();
        statuses.put(1L, "Processing");
        statuses.put(2L, "Обрабатывается");
        statuses.put(3L, "Delivering");
        statuses.put(4L, "Доставляется");
        statuses.put(5L, "Delivered");
        statuses.put(6L, "Доставлен");

        return statuses.get(statusId);
    }

}
