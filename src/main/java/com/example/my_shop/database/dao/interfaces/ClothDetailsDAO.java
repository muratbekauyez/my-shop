package com.example.my_shop.database.dao.interfaces;


import com.example.my_shop.entity.ClothDetails;
import com.example.my_shop.entity.Language;

import java.sql.SQLException;
import java.util.List;

public interface ClothDetailsDAO {
    void create(ClothDetails clothDetails) throws SQLException;

    void update(ClothDetails clothDetails) throws SQLException;

    void delete(Long clothDetailsId, Long languageId) throws SQLException;

    ClothDetails getClothDetails(Long id, Language language) throws SQLException;

    List<ClothDetails> getAllClothDetails() throws SQLException;

}
