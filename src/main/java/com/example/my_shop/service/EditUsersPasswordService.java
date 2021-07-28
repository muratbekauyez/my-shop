package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.UserDAOImpl;
import com.example.my_shop.database.dao.interfaces.UserDAO;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.hashing.MD5;
import com.example.my_shop.util.validators.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.EDIT_USERS_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class EditUsersPasswordService implements Service {
    private final UserDAO userDAO = new UserDAOImpl();
    private final PasswordValidator validator = new PasswordValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(!validator.isValid(request, response)) {
            request.setAttribute(PASSWORD_UPDATING, FILL_ALL_FIELDS);
        }else if(!validator.isPasswordValid(request.getParameter(PASSWORD))){
            request.setAttribute(PASSWORD_UPDATING,WRONG_CREDENTIALS);
        }else if(!request.getParameter(PASSWORD).equals(request.getParameter(RETYPE_PASSWORD))){
            request.setAttribute(PASSWORD_UPDATING, PASSWORDS_NOT_MATCH);
        }else{
            userDAO.updatePassword(Long.parseLong(request.getParameter(USER_ID)), MD5.getMd5(request.getParameter(PASSWORD)));
            request.setAttribute(PASSWORD_UPDATING, POSITIVE);
        }


        request.getRequestDispatcher(EDIT_USERS_PAGE).forward(request, response);
    }
}
