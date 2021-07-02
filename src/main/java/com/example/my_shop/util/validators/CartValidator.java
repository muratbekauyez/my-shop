package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.CART_AMOUNT;
import static com.example.my_shop.util.constants.ParameterConstants.CLOTH_SIZE_ID;

public class CartValidator implements Validator{
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {
        boolean cartAmount = request.getParameter(CART_AMOUNT) != null && !request.getParameter(CART_AMOUNT).equals("");
        boolean clothSizeId = request.getParameter(CLOTH_SIZE_ID) != null && !request.getParameter(CLOTH_SIZE_ID).equals("");
        boolean amountValidator = Integer.parseInt(request.getParameter(CART_AMOUNT)) > 0;
        return  cartAmount && clothSizeId && amountValidator;
    }
}
