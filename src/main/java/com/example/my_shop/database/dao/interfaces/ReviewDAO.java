package com.example.my_shop.database.dao.interfaces;

import com.example.my_shop.entity.Review;

import java.sql.SQLException;
import java.util.List;


public interface ReviewDAO {
    void create(Review review) throws SQLException;

    Review getClothReviewByUser(Long userId,Long productId) throws SQLException;

    List<Review> getClothReviews(Long productId) throws SQLException;

    void updateReview (Review review) throws SQLException;

}
