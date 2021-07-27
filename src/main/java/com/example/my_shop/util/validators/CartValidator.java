package com.example.my_shop.util.validators;

import com.example.my_shop.database.dao.impls.SizeDAOImpl;
import com.example.my_shop.database.dao.interfaces.SizeDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import static com.example.my_shop.util.constants.ParameterConstants.CART_AMOUNT;
import static com.example.my_shop.util.constants.ParameterConstants.CLOTH_SIZE_ID;

public class CartValidator implements Validator{
    private final SizeDAO sizeDAO = new SizeDAOImpl();

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean cartAmount = request.getParameter(CART_AMOUNT) != null && !request.getParameter(CART_AMOUNT).equals("");
        boolean clothSizeId = request.getParameter(CLOTH_SIZE_ID) != null && !request.getParameter(CLOTH_SIZE_ID).equals("");
        boolean amountValidator = Integer.parseInt(request.getParameter(CART_AMOUNT)) > 0;
        return  cartAmount && clothSizeId && amountValidator;
    }

    public boolean isClothAmountValid (Long clothId, Long sizeId, int cartAmount) throws SQLException {
        boolean clothSizeExists = sizeDAO.clothSizeExists(clothId, sizeId);
        boolean isClothAmountEnough = cartAmount <= sizeDAO.getAmountOfClothInSize(clothId, sizeId);

        return clothSizeExists && isClothAmountEnough;
    }
}
