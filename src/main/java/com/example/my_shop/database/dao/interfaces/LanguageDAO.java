package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Language;

import java.sql.SQLException;
import java.util.List;

public interface LanguageDAO {
    List<Language> getAllLanguages() throws SQLException;

    String getLanguageName(Long ID) throws SQLException;

}
