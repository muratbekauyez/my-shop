package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Cloth;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ClothDAO {

    void create(Cloth cloth) throws SQLException;

    void update(Cloth cloth) throws SQLException;

    Cloth getCloth(Long id) throws SQLException;

    List<Cloth> getAllClothes() throws SQLException, IOException;

    void addClothAmount(Long clothId, Long sizeId, int amount) throws SQLException;

    List<Cloth> filterClothes(String[] sizeIds,String[] companyIds)throws SQLException;

    List<Cloth> getAvailableClothes() throws SQLException;

    void setAmountToZero(Long id) throws SQLException;

}
