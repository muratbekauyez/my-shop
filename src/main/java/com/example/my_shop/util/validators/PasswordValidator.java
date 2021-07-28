package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.*;

public class PasswordValidator implements Validator{
    public static final String PASSWORD_REGEX = "[a-zA-Z0-9~!@#$%^&*]{5,}";

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean newPassword = request.getParameter(PASSWORD) != null && !request.getParameter(PASSWORD).equals("");
        boolean rePassword = request.getParameter(RETYPE_PASSWORD) != null && !request.getParameter(RETYPE_PASSWORD).equals("");

        return newPassword && rePassword;
    }

    public boolean isPasswordValid(String password){
        return password.matches(PASSWORD_REGEX);
    }
}
