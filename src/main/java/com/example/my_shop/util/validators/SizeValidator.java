package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.*;

public class SizeValidator implements Validator{
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {
        boolean clothId = request.getParameter(CLOTH_ID) != null && !request.getParameter(CLOTH_ID).equals("");
        boolean clothSizeId = request.getParameter(CLOTH_SIZE_ID) != null && !request.getParameter(CLOTH_SIZE_ID).equals("");
        boolean clothAmount = request.getParameter(CLOTH_AMOUNT) != null && !request.getParameter(CLOTH_AMOUNT).equals("");

        return clothId && clothSizeId && clothAmount;
    }
}
