package com.example.my_shop.database.dao.factory;

import com.example.my_shop.entity.Cloth;

import java.util.List;

public class ClothFactory {
    public String prepareQueryForFilter(String[] sizeIds, String[] companyIds){
        StringBuilder sizeIdsQuery = new StringBuilder();
        StringBuilder companyIdsQuery = new StringBuilder();
        String query = null;

        if(sizeIds.length != 0){
            for (int i = 0; i < sizeIds.length; i++) {
                if (i == sizeIds.length - 1) {
                    sizeIdsQuery.append("size_id = ").append(sizeIds[i]);
                } else {
                    sizeIdsQuery.append("size_id = ").append(sizeIds[i]).append(" OR ");
                }
            }
        }
        if(companyIds.length != 0){
            for (int i = 0; i < companyIds.length; i++) {
                if (i == companyIds.length - 1) {
                    companyIdsQuery.append("company_id = ").append(companyIds[i]);
                } else {
                    companyIdsQuery.append("company_id = ").append(companyIds[i]).append(" OR ");
                }
            }
        }

        if(sizeIds.length != 0 && companyIds.length != 0){
            query = "SELECT * FROM \"Clothes\" C JOIN \"Cloth_Size\" CS on C.id = CS.cloth_id WHERE (" + sizeIdsQuery + ") AND (" + companyIdsQuery + ") AND amount > 0";
        }else if(sizeIds.length != 0){
            query = "SELECT * FROM \"Clothes\" C JOIN \"Cloth_Size\" CS on C.id = CS.cloth_id WHERE (" + sizeIdsQuery +  ") AND amount > 0";
        }else if(companyIds.length != 0){
            query = "SELECT * FROM \"Clothes\" C JOIN \"Cloth_Size\" CS on C.id = CS.cloth_id WHERE (" + companyIdsQuery +  ") AND amount > 0";
        }
        return query;
    }


    public List<Cloth> sortClothes (java.util.List<com.example.my_shop.entity.Cloth> clothes, String sortName){
        if(clothes.size() == 1)return clothes;
        switch (sortName) {
            case "priceAsc":
                for (int i = 0; i < clothes.size() - 1; i++) {
                    for (int j = 0; j < clothes.size() - i - 1; j++) {
                        if (clothes.get(j).getPrice() > clothes.get(j + 1).getPrice()) {
                            com.example.my_shop.entity.Cloth temp = clothes.get(j);
                            clothes.set(j, clothes.get(j + 1));
                            clothes.set(j + 1, temp);
                        }
                    }
                }
                return clothes;
            case "priceDesc":
                for (int i = 0; i < clothes.size() - 1; i++) {
                    for (int j = 0; j < clothes.size() - i - 1; j++) {
                        if (clothes.get(j).getPrice() < clothes.get(j + 1).getPrice()) {
                            com.example.my_shop.entity.Cloth temp = clothes.get(j);
                            clothes.set(j, clothes.get(j + 1));
                            clothes.set(j + 1, temp);
                        }
                    }
                }
                return clothes;
            case "newFirst":
                for (int i = 0; i < clothes.size() - 1; i++) {
                    for (int j = 0; j < clothes.size() - i - 1; j++) {
                        if (clothes.get(j).getId() < clothes.get(j + 1).getId()) {
                            com.example.my_shop.entity.Cloth temp = clothes.get(j);
                            clothes.set(j, clothes.get(j + 1));
                            clothes.set(j + 1, temp);
                        }
                    }
                }
                return clothes;
        }
        return null;
    }
}
