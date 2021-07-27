package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.*;

public class PasswordValidator implements Validator{
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean newPassword = request.getParameter(PASSWORD) != null && !request.getParameter(PASSWORD).equals("");
        boolean rePassword = request.getParameter(RETYPE_PASSWORD) != null && !request.getParameter(RETYPE_PASSWORD).equals("");

        return newPassword && rePassword;
    }
}
