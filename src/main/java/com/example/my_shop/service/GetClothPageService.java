package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.ClothDAOImpl;
import com.example.my_shop.database.dao.impls.ClothDetailsDAOImpl;
import com.example.my_shop.database.dao.impls.ReviewDAOImpl;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.database.dao.interfaces.ClothDetailsDAO;
import com.example.my_shop.database.dao.interfaces.ReviewDAO;
import com.example.my_shop.entity.*;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.example.my_shop.util.constants.PageConstants.*;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class GetClothPageService implements Service {
    private final ClothDAO clothDAO = new ClothDAOImpl();
    private final ClothDetailsDAO clothDetailsDAO = new ClothDetailsDAOImpl();
    private final ReviewDAO reviewDAO = new ReviewDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(LOGGED_USER) != null){
            Language language = new Language();

            User user = (User)session.getAttribute(LOGGED_USER);
            Long clothId = Long.parseLong(request.getParameter(CLOTH_ID));
            language.setId((Long)session.getAttribute(WEB_LANGUAGE_ID));
            language.setLanguageName((String) session.getAttribute(WEB_LANGUAGE_NAME));

            if(clothDAO.getCloth(clothId) == null){
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
                return;
            }

            Cloth cloth = clothDAO.getCloth(clothId);
            ClothDetails clothDetails = clothDetailsDAO.getClothDetails(cloth.getId(),language);
            Review review = reviewDAO.getClothReviewByUser(user.getId(), clothId);
            List<Review> reviewsOfCloth = reviewDAO.getClothReviews(clothId);

            session.setAttribute(CLOTH,cloth);
            session.setAttribute(CLOTH_DETAILS, clothDetails);
            session.setAttribute(USER_REVIEW,review);

            if(reviewsOfCloth.size() > 0){
                session.setAttribute(CLOTH_REVIEWS, reviewsOfCloth);
            }else{
                session.setAttribute(CLOTH_REVIEWS, null);
            }

            request.getRequestDispatcher(CLOTH_PAGE).forward(request,response);
        }else{
            request.getRequestDispatcher(LOGIN_PAGE).forward(request,response);
        }


    }
}
