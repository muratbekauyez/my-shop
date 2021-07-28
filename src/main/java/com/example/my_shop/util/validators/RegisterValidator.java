package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.*;

public class RegisterValidator implements Validator{
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9._\\-]{3,}$";

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response)  {
        boolean username = request.getParameter(USERNAME) != null && !request.getParameter(USERNAME).equals("");
        boolean password = request.getParameter(PASSWORD) != null && !request.getParameter(PASSWORD).equals("");
        boolean rePassword = request.getParameter(RETYPE_PASSWORD) != null && !request.getParameter(RETYPE_PASSWORD).equals("");
        boolean firstName = request.getParameter(FIRST_NAME) != null && !request.getParameter(FIRST_NAME).equals("");
        boolean lastName = request.getParameter(LAST_NAME) != null && !request.getParameter(LAST_NAME).equals("");
        boolean birthDate = request.getParameter(BIRTH_DATE) != null && !request.getParameter(BIRTH_DATE).equals("");
        boolean gender = request.getParameter(GENDER) != null && !request.getParameter(GENDER).equals("");

        return  username && password && rePassword && firstName && lastName && birthDate && gender;
    }

    public boolean isUsernameValid(String login){
        return login.matches(USERNAME_REGEX);
    }


}
