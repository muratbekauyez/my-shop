package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.UserDAOImpl;
import com.example.my_shop.database.dao.interfaces.UserDAO;
import com.example.my_shop.entity.User;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.validators.ProfileValidator;
import com.example.my_shop.util.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.*;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class EditProfileService implements Service {
    private final UserDAO userDAO = new UserDAOImpl();
    private final Validator validator = new ProfileValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        if(!validator.isValid(request, response)) {
            request.setAttribute(PROFILE_UPDATING, NEGATIVE);
        }else {
            User user = new User();
            user.setId(Long.parseLong(request.getParameter(USER_ID)));
            user.setFirstName(request.getParameter(FIRST_NAME));
            user.setLastName(request.getParameter(LAST_NAME));
            user.setBirthDate(java.sql.Date.valueOf(request.getParameter(BIRTH_DATE)));
            user.setGenderId(Long.parseLong(request.getParameter(GENDER)));
            userDAO.update(user);
            session.setAttribute(LOGGED_USER,userDAO.getUserById(user.getId()));
            request.setAttribute(PROFILE_UPDATING, POSITIVE);
        }



        if(request.getParameter(PAGE_NAME).equals(EDIT_PROFILE_PAGE)){
            request.getRequestDispatcher(PROFILE_PAGE).forward(request,response);
        }
        if (request.getParameter(PAGE_NAME).equals(EDIT_USERS_PAGE)) {
            request.getRequestDispatcher(EDIT_USERS_PAGE).forward(request, response);

        }

    }
}
