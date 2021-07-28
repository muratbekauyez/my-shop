package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Size;

import java.sql.SQLException;
import java.util.List;

public interface SizeDAO {
    String getSizeName (Long sizeId) throws SQLException;

    boolean clothSizeExists(Long clothId, Long sizeId) throws SQLException;

    int getClothAmount(Long clothId, Long sizeId) throws SQLException;

    List<Size> getAllSizesOfCloth(Long clothId) throws SQLException;

}
