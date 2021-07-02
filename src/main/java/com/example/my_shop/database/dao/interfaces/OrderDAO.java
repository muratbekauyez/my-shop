package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Order;
import com.example.my_shop.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    Long create (Order order) throws SQLException;

    void addOrderDetails (List<OrderDetails> orderDetails) throws SQLException;

    void reduceAmountOfClothes (List<OrderDetails> orderDetailsList) throws SQLException;

    List<Order> getUserOrders (Long userId) throws SQLException;

    List<OrderDetails> getOrderDetails (Long orderId) throws SQLException;

}
