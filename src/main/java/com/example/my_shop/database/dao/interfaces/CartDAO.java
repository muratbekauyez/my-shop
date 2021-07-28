package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartDAO {
    void create(Cart cart) throws SQLException;

    void updateAmount (Cart cart) throws SQLException;

    void delete(Cart cart) throws SQLException;

    List<Cart> getCartProducts(Long userId) throws SQLException;

    boolean cartExists(Cart cart) throws SQLException;

    int getSumOfCart(Long userId) throws SQLException;

    void deleteAllProductsFromCart(Long userId) throws SQLException;
}
