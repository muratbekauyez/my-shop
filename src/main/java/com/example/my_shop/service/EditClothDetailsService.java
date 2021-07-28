package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.ClothDetailsDAOImpl;
import com.example.my_shop.database.dao.interfaces.ClothDetailsDAO;
import com.example.my_shop.entity.ClothDetails;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.validators.ClothDetailsValidator;
import com.example.my_shop.util.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.EDIT_CLOTHES_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;
import static com.example.my_shop.util.validators.NumberParameterValidator.isNumberParameterValid;


public class EditClothDetailsService implements Service {
    private final ClothDetailsDAO clothDetailsDAO = new ClothDetailsDAOImpl();
    private final Validator validator = new ClothDetailsValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (validator.isValid(request, response) && isNumberParameterValid(request.getParameter(CLOTH_NUMBER_OF_POCKETS))) {
            ClothDetails clothDetails = new ClothDetails();
            clothDetails.setId(Long.parseLong(request.getParameter(CLOTH_ID)));
            clothDetails.setLanguageID(Long.parseLong(request.getParameter(CLOTH_LANGUAGE_ID)));
            clothDetails.setName(request.getParameter(CLOTH_NAME));
            clothDetails.setColor(request.getParameter(CLOTH_COLOR));
            clothDetails.setNumberOfPockets(Integer.parseInt(request.getParameter(CLOTH_NUMBER_OF_POCKETS)));
            clothDetails.setSeason(request.getParameter(CLOTH_SEASON));
            clothDetails.setPattern(request.getParameter(CLOTH_PATTERN));
            clothDetails.setAbout(request.getParameter(CLOTH_ABOUT));
            clothDetailsDAO.update(clothDetails);
            request.setAttribute(CLOTH_DETAILS_EDITION, POSITIVE);
        }else{
            request.setAttribute(CLOTH_DETAILS_DELETION, NEGATIVE);
        }
        request.getRequestDispatcher(EDIT_CLOTHES_PAGE).forward(request, response);
    }
}
