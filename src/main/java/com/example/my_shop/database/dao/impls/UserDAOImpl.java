package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.UserDAO;
import com.example.my_shop.entity.User;
import com.example.my_shop.util.hashing.MD5;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public void create(User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String ADD_USER = "INSERT INTO \"User\" (username,password,first_name,last_name,birth_date,registration_date, gender_id, role_id) VALUES (?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)){
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setDate(5, (Date) user.getBirthDate());
            preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
            preparedStatement.setLong(7, user.getGenderId());
            preparedStatement.setLong(8,2);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String UPDATE_USER = "UPDATE \"User\" SET first_name = ?, last_name = ?, birth_date = ?, gender_id = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)){
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setDate(3,(Date) user.getBirthDate());
            preparedStatement.setLong(4,user.getGenderId());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<User> allUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_ALL_USERS = "SELECT * FROM \"User\" ORDER BY id";

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setRegistrationDate(resultSet.getDate("registration_date"));
                user.setGenderId(resultSet.getLong("gender_id"));
                allUsers.add(user);
            }
        }
        return allUsers;
    }

    @Override
    public void updatePassword (Long id, String newPassword) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String UPDATE_PASSWORD = "UPDATE \"User\" SET password = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)){
            preparedStatement.setString(1, newPassword);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public boolean checkPassword(Long id, String currentPassword) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String CHECK_PASSWORD = "SELECT password FROM \"User\" WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_PASSWORD);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            connectionPool.returnConnection(connection);
            return resultSet.getString("password").equals(MD5.getMd5(currentPassword));

        }
        connectionPool.returnConnection(connection);
        return false;
    }

    @Override
    public Long getIDByLogin(String username) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_USER = "SELECT id FROM \"User\" WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            connectionPool.returnConnection(connection);
            return resultSet.getLong("id");
        }
        connectionPool.returnConnection(connection);
        return null;
    }

    @Override
    public User getUserById(Long id) throws SQLException{
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_USER = "SELECT username, first_name, last_name, birth_date, registration_date, gender_id, role_id FROM \"User\" WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            User user = new User();
            user.setId(id);
            user.setUsername(resultSet.getString("username"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setBirthDate(resultSet.getDate("birth_date"));
            user.setRegistrationDate(resultSet.getDate("registration_date"));
            user.setGenderId(resultSet.getLong("gender_id"));
            user.setRoleId(resultSet.getLong("role_id"));
            connectionPool.returnConnection(connection);
            return user;
        }
        connectionPool.returnConnection(connection);
        return null;
    }

    @Override
    public User getUserByLoginAndPassword(String username, String password) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_USER = "SELECT username, first_name, last_name, birth_date, registration_date, gender_id, role_id FROM \"User\" WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, MD5.getMd5(password));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            User user = new User();
            user.setId(getIDByLogin(username));
            user.setUsername(resultSet.getString("username"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setBirthDate(resultSet.getDate("birth_date"));
            user.setRegistrationDate(resultSet.getDate("registration_date"));
            user.setGenderId(resultSet.getLong("gender_id"));
            user.setRoleId(resultSet.getLong("role_id"));
            connectionPool.returnConnection(connection);
            return user;
        }
        connectionPool.returnConnection(connection);
        return null;
    }

    @Override
    public boolean isUserExist(String username) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String CHECK_USERNAME = "SELECT * FROM \"User\" WHERE username = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME)){
            preparedStatement.setString(1, username);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                connectionPool.returnConnection(connection);
                return true;
            }
        }
        connectionPool.returnConnection(connection);
        return false;
    }
}
