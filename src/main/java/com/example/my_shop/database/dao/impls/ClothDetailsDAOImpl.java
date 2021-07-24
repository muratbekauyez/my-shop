package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.ClothDetailsDAO;
import com.example.my_shop.entity.ClothDetails;
import com.example.my_shop.entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClothDetailsDAOImpl implements ClothDetailsDAO {
    private static final String ADD_CLOTH_DETAILS = "INSERT INTO \"Cloth_Details\" (cloth_id, language_id, name, color, number_of_pockets, season, pattern, about) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_CLOTH_DETAILS = "UPDATE \"Cloth_Details\" SET name = ?, color = ?, number_of_pockets = ?, season = ?, pattern = ?, about = ? WHERE cloth_id = ? AND language_id = ?";
    private static final String DELETE_CART = "DELETE FROM \"Cloth_Details\" WHERE cloth_id = ? AND language_id = ?";
    private static final String GET_CLOTH_DETAILS = "SELECT name, color, number_of_pockets, season, pattern, about FROM \"Cloth_Details\" WHERE cloth_id = ? AND language_id = ? ORDER BY cloth_id";
    private static final String GET_CLOTHES_DETAILS = "SELECT cloth_id, language_id, name, color, number_of_pockets, season, pattern, about FROM \"Cloth_Details\"";

    private ConnectionPool connectionPool;
    private Connection connection;


    @Override
    public void create(ClothDetails clothDetails) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CLOTH_DETAILS)) {
            preparedStatement.setLong(1, clothDetails.getId());
            preparedStatement.setLong(2, clothDetails.getLanguageID());
            preparedStatement.setString(3, clothDetails.getName());
            preparedStatement.setString(4, clothDetails.getColor());
            preparedStatement.setInt(5, clothDetails.getNumberOfPockets());
            preparedStatement.setString(6, clothDetails.getSeason());
            preparedStatement.setString(7, clothDetails.getPattern());
            preparedStatement.setString(8, clothDetails.getAbout());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(ClothDetails clothDetails) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLOTH_DETAILS)) {
            preparedStatement.setString(1, clothDetails.getName());
            preparedStatement.setString(2, clothDetails.getColor());
            preparedStatement.setInt(3, clothDetails.getNumberOfPockets());
            preparedStatement.setString(4, clothDetails.getSeason());
            preparedStatement.setString(5, clothDetails.getPattern());
            preparedStatement.setString(6, clothDetails.getAbout());
            preparedStatement.setLong(7, clothDetails.getId());
            preparedStatement.setLong(8, clothDetails.getLanguageID());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void delete(Long clothDetailsId, Long languageId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART)) {
            preparedStatement.setLong(1, clothDetailsId);
            preparedStatement.setLong(2, languageId);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }

    }


    @Override
    public ClothDetails getClothDetails(Long id, Language language) throws SQLException {
        ClothDetails clothDetails = new ClothDetails();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CLOTH_DETAILS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, language.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clothDetails.setId(id);
                clothDetails.setLanguageID(language.getId());
                clothDetails.setName(resultSet.getString("name"));
                clothDetails.setColor(resultSet.getString("color"));
                clothDetails.setNumberOfPockets(resultSet.getInt("number_of_pockets"));
                clothDetails.setSeason(resultSet.getString("season"));
                clothDetails.setPattern(resultSet.getString("pattern"));
                clothDetails.setAbout(resultSet.getString("about"));
            }
        }finally {
            connectionPool.returnConnection(connection);
        }

        return clothDetails;
    }

    @Override
    public List<ClothDetails> allClothDetails() throws SQLException {
        List<ClothDetails> allClothDetails = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CLOTHES_DETAILS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClothDetails clothDetails = new ClothDetails();
                clothDetails.setId(resultSet.getLong("cloth_id"));
                clothDetails.setLanguageID(resultSet.getLong("language_id"));
                clothDetails.setName(resultSet.getString("name"));
                clothDetails.setColor(resultSet.getString("color"));
                clothDetails.setNumberOfPockets(resultSet.getInt("number_of_pockets"));
                clothDetails.setSeason(resultSet.getString("season"));
                clothDetails.setPattern(resultSet.getString("pattern"));
                clothDetails.setAbout(resultSet.getString("about"));
                allClothDetails.add(clothDetails);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return allClothDetails;
    }
}
