package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.ReviewDAO;
import com.example.my_shop.entity.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    private static final String ADD_REVIEW = "INSERT INTO \"Review\" (user_id, product_id, content, date) VALUES (?,?,?,?)";
    private static final String GET_CLOTH_REVIEW_BY_USER = "SELECT id, content, date FROM \"Review\" WHERE user_id = ? AND product_id = ?";
    private static final String GET_CLOTH_REVIEWS = "SELECT id, user_id, content, date FROM \"Review\" WHERE product_id = ?";
    private static final String UPDATE_REVIEW = "UPDATE \"Review\" SET content=?, date = ? WHERE user_id = ? AND product_id = ?";
    private static final String DELETE_REVIEW = "DELETE FROM \"Review\" WHERE user_id = ? AND product_id = ?";

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public void create(Review review) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_REVIEW)) {
            preparedStatement.setLong(1, review.getUserId());
            preparedStatement.setLong(2, review.getProductId());
            preparedStatement.setString(3, review.getContent());
            preparedStatement.setDate(4, (Date) review.getDate());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Review getClothReviewByUser(Long userId, Long productId) throws SQLException {
        Review review = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CLOTH_REVIEW_BY_USER)){
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                review = new Review();
                review.setId(resultSet.getLong("id"));
                review.setUserId(userId);
                review.setProductId(productId);
                review.setContent(resultSet.getString("content"));
                review.setDate(resultSet.getDate("date"));
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return review;
    }

    @Override
    public List<Review> getClothReviews(Long productId) throws SQLException {
        List<Review> reviewsOfUser = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CLOTH_REVIEWS)) {
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getLong("id"));
                review.setUserId(resultSet.getLong("user_id"));
                review.setProductId(productId);
                review.setContent(resultSet.getString("content"));
                review.setDate(resultSet.getDate("date"));
                reviewsOfUser.add(review);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return reviewsOfUser;
    }


    @Override
    public void updateReview(Review review) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REVIEW)){
            preparedStatement.setString(1,review.getContent());
            preparedStatement.setDate(2,(Date) review.getDate());
            preparedStatement.setLong(3,review.getUserId());
            preparedStatement.setLong(4,review.getProductId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteReview(Review review) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW)){
            preparedStatement.setLong(1, review.getUserId());
            preparedStatement.setLong(2, review.getProductId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }
}
