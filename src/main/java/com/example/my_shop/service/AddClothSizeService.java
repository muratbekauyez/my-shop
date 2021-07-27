package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.ClothDAOImpl;
import com.example.my_shop.database.dao.impls.SizeDAOImpl;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.database.dao.interfaces.SizeDAO;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.validators.SizeValidator;
import com.example.my_shop.util.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.ADD_CLOTH_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class AddClothSizeService implements Service {
    private final ClothDAO clothDAO = new ClothDAOImpl();
    private final SizeDAO sizeDAO = new SizeDAOImpl();
    private final Validator validator = new SizeValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(validator.isValid(request, response)){
            Long clothId  = Long.parseLong(request.getParameter(CLOTH_ID));
            Long clothSizeId = Long.parseLong(request.getParameter(CLOTH_SIZE_ID));
            int clothAmount = Integer.parseInt(request.getParameter(CLOTH_AMOUNT));

            if(!sizeDAO.clothSizeExists(clothId,clothSizeId)){
                clothDAO.addClothAmount(clothId, clothSizeId, clothAmount);
                request.setAttribute(CLOTH_SIZE_ADDITION, POSITIVE);
            }else {
                request.setAttribute(CLOTH_SIZE_ADDITION, NEGATIVE);
            }
        }else {
            request.setAttribute(CLOTH_SIZE_ADDITION, NEGATIVE);
        }
        request.getRequestDispatcher(ADD_CLOTH_PAGE).forward(request,response);
    }
}
