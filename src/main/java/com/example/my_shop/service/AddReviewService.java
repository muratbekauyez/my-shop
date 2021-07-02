package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.ReviewDAOImpl;
import com.example.my_shop.database.dao.interfaces.ReviewDAO;
import com.example.my_shop.entity.Cloth;
import com.example.my_shop.entity.Review;
import com.example.my_shop.entity.User;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.example.my_shop.util.constants.PageConstants.CLOTH_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class AddReviewService implements Service {
    private final ReviewDAO reviewDAO = new ReviewDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute(LOGGED_USER);
        Cloth cloth = (Cloth) session.getAttribute(CLOTH);

        if (request.getParameter(REVIEW_CONTENT) != null && !request.getParameter(REVIEW_CONTENT).equals("")) {
            Review review = new Review();
            review.setUserId(user.getId());
            review.setProductId(cloth.getId());
            review.setContent(request.getParameter(REVIEW_CONTENT));
            review.setDate(java.sql.Date.valueOf(LocalDate.now()));
            reviewDAO.create(review);

            List<Review> reviewsOfCloth = reviewDAO.getClothReviews(cloth.getId());

            session.setAttribute(USER_REVIEW, review);
            session.setAttribute(CLOTH_REVIEWS, reviewsOfCloth);
        }
        request.getRequestDispatcher(CLOTH_PAGE).forward(request, response);
    }
}
