package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void create (User user) throws SQLException;

    void update (User user) throws SQLException;

    List<User> allUsers() throws SQLException;

    void updatePassword (Long id, String newPassword) throws SQLException;

    boolean checkPassword(Long id, String currentPassword) throws SQLException;

    User getUserByLoginAndPassword(String username, String password) throws SQLException;

    Long getIDByLogin(String username) throws SQLException;

    boolean userExists(String username) throws SQLException;

    User getUserById(Long id) throws SQLException;



}
