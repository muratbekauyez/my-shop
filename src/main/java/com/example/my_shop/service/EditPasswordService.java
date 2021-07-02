package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.UserDAOImpl;
import com.example.my_shop.database.dao.interfaces.UserDAO;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.hashing.MD5;
import com.example.my_shop.util.validators.PasswordValidator;
import com.example.my_shop.util.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.PROFILE_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class EditPasswordService implements Service {
    private final UserDAO userDAO = new UserDAOImpl();
    private final Validator validator = new PasswordValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(request.getParameter(CURRENT_PASSWORD) != null && !request.getParameter(CURRENT_PASSWORD).equals("") && validator.validate(request, response)) {
            Long id = Long.parseLong(request.getParameter(USER_ID));
            String currentPassword = request.getParameter(CURRENT_PASSWORD);
            String newPassword = request.getParameter(PASSWORD);
            String rePassword = request.getParameter(RETYPE_PASSWORD);
            if (!userDAO.checkPassword(id, currentPassword)) {
                request.setAttribute(PASSWORD_UPDATING, WRONG_CURRENT_PASSWORD);
            } else if (!newPassword.equals(rePassword)) {
                request.setAttribute(PASSWORD_UPDATING, PASSWORDS_NOT_MATCH);
            } else {
                userDAO.updatePassword(id, MD5.getMd5(newPassword));
                request.setAttribute(PASSWORD_UPDATING, POSITIVE);
            }
        }else{
            request.setAttribute(PASSWORD_UPDATING, FILL_ALL_FIELDS);
        }
        request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
    }
}
