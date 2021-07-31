package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.ClothDetailsDAOImpl;
import com.example.my_shop.database.dao.interfaces.ClothDetailsDAO;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.EDIT_CLOTHES_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class DeleteClothDetailsService implements Service {
    private final ClothDetailsDAO clothDetailsDAO = new ClothDetailsDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Long clothDetailsId = Long.parseLong(request.getParameter(CLOTH_ID));
        Long languageId = Long.parseLong(request.getParameter(CLOTH_LANGUAGE_ID));

        clothDetailsDAO.delete(clothDetailsId, languageId);

        request.setAttribute(CLOTH_DETAILS_DELETION, POSITIVE);
        request.getRequestDispatcher(EDIT_CLOTHES_PAGE).forward(request, response);
    }
}
