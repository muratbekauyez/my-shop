package com.example.my_shop.database.dao.factory;

import com.example.my_shop.database.dao.impls.ClothDAOImpl;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.entity.Cloth;
import com.example.my_shop.util.sorting.ClothIdComparator;
import com.example.my_shop.util.sorting.ClothPriceComparator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClothFactory {
    private final ClothDAO clothDAO = new ClothDAOImpl();

    public List<Cloth> filterClothesByCompaniesAndSizes (String[] sizeIds, String[] companyIds) throws SQLException{

        List<Cloth> clothesFilteredBySize = filterClothesByMultipleSizes(sizeIds);
        List<Cloth> clothesFilteredByCompany = filterClothesByMultipleCompanies(companyIds);

        List<Cloth> filteredClothesByCompaniesAndSizes = new ArrayList<>();

        for (Cloth cloth: clothesFilteredByCompany) {
            if(clothesFilteredBySize.contains(cloth)){
                filteredClothesByCompaniesAndSizes.add(cloth);
            }
        }

        return filteredClothesByCompaniesAndSizes;

    }

    public List<Cloth> filterClothesByMultipleCompanies(String[] companyIds) throws SQLException {
        List<Cloth> filteredClothes = new ArrayList<>();

        for (String companyId : companyIds) {
            List<Cloth> clothListOfParticularCompany = clothDAO.filterClothesWithCompany(companyId);
            filteredClothes.addAll(clothListOfParticularCompany);
        }

        return filteredClothes;
    }

    public List<Cloth> filterClothesByMultipleSizes(String[] sizeIds) throws SQLException {
        List<Cloth> filteredClothes = new ArrayList<>();

        for (String sizeId : sizeIds) {
            List<Cloth> clothListOfParticularSize = clothDAO.filterClothesWithSize(sizeId);
            for (Cloth cloth : clothListOfParticularSize){
                if(!filteredClothes.contains(cloth)){
                    filteredClothes.add(cloth);
                }
            }
        }
        return filteredClothes;
    }




    public List<Cloth> sortClothes(List<Cloth> clothes, String sortName) {
        if (clothes.size() == 1) {
            return clothes;
        }

        ClothPriceComparator clothPriceComparator = new ClothPriceComparator();
        ClothIdComparator clothIdComparator = new ClothIdComparator();

        switch (sortName) {
            case "priceAsc":
                clothes.sort(clothPriceComparator);
                return clothes;
            case "priceDesc":
                clothes.sort(clothPriceComparator.reversed());
                return clothes;
            case "newFirst":
                clothes.sort(clothIdComparator.reversed());
                return clothes;
        }


        return null;

    }
}
