package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.*;
import static com.example.my_shop.util.constants.ParameterConstants.GENDER;

public class ProfileValidator implements Validator{
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean firstName = request.getParameter(FIRST_NAME) != null && !request.getParameter(FIRST_NAME).equals("");
        boolean lastName = request.getParameter(LAST_NAME) != null && !request.getParameter(LAST_NAME).equals("");
        boolean birthDate = request.getParameter(BIRTH_DATE) != null && !request.getParameter(BIRTH_DATE).equals("");
        boolean gender = request.getParameter(GENDER) != null && !request.getParameter(GENDER).equals("");

        return  firstName && lastName && birthDate && gender;
    }
}
