package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.PASSWORD;
import static com.example.my_shop.util.constants.ParameterConstants.USERNAME;

public class LoginValidator implements Validator{
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean username = request.getParameter(USERNAME) != null && !request.getParameter(USERNAME).equals("");
        boolean password = request.getParameter(PASSWORD) != null && !request.getParameter(PASSWORD).equals("");
        return username && password;
    }
}
