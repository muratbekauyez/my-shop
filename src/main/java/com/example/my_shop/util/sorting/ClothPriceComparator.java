package com.example.my_shop.util.sorting;

import com.example.my_shop.entity.Cloth;

import java.util.Comparator;

public class ClothPriceComparator implements Comparator<Cloth> {
    @Override
    public int compare(Cloth c1, Cloth c2) {

        return Integer.compare(c1.getPrice(),c2.getPrice());
    }

    @Override
    public Comparator<Cloth> reversed() {

        return Comparator.super.reversed();
    }
}
