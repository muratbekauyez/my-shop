package com.example.my_shop.database.dao.impls;

import com.example.my_shop.database.connection.ConnectionPool;
import com.example.my_shop.database.dao.interfaces.LanguageDAO;
import com.example.my_shop.entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAOImpl implements LanguageDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<Language> getAllLanguages() throws SQLException{
        List<Language> allLanguages = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_LANGUAGES = "SELECT id, language_name FROM \"Language\"";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LANGUAGES)){
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Language language = new Language();
                language.setId(resultSet.getLong("id"));
                language.setLanguageName(resultSet.getString("language_name"));
                allLanguages.add(language);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return allLanguages;
    }

    @Override
    public Long getLanguageId(String languageName) throws SQLException{
        long ID = 0L;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_LANGUAGE_ID = "SELECT id FROM \"Language\" where language_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LANGUAGE_ID)) {
            preparedStatement.setString(1, languageName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ID = resultSet.getLong("ID");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return ID;
    }

    @Override
    public String getLanguageName (Long ID) throws SQLException{
        String languageName = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        String GET_LANGUAGE_NAME = "SELECT language_name FROM \"Language\" where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LANGUAGE_NAME)) {
            preparedStatement.setLong(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                languageName = resultSet.getString("language_name");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return languageName;
    }


}
