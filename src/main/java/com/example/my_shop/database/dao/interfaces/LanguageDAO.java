package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Language;

import java.sql.SQLException;
import java.util.List;

public interface LanguageDAO {
    public List<Language> getAllLanguages() throws SQLException;

    public Long getLanguageId(String languageName) throws SQLException;

    public String getLanguageName (Long ID) throws SQLException;

}
