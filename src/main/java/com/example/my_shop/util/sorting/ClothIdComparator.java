package com.example.my_shop.util.sorting;

import com.example.my_shop.entity.Cloth;

import java.util.Comparator;

public class ClothIdComparator implements Comparator<Cloth> {
    @Override
    public int compare(Cloth c1, Cloth c2) {
        return Long.compare(c1.getId(), c2.getId());
    }

    @Override
    public Comparator<Cloth> reversed() {
        return Comparator.super.reversed();
    }
}
