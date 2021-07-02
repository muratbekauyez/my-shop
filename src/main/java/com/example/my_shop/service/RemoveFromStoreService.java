package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.ClothDAOImpl;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.EDIT_CLOTHES_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class RemoveFromStoreService implements Service {
    private final ClothDAO clothDAO = new ClothDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Long clothId = Long.parseLong(request.getParameter(CLOTH_ID));
        clothDAO.setAmountToZero(clothId);
        request.setAttribute(CLOTH_DELETION, POSITIVE);
        request.getRequestDispatcher(EDIT_CLOTHES_PAGE).forward(request, response);
    }
}
