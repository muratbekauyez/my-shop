package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.UserDAOImpl;
import com.example.my_shop.database.dao.interfaces.UserDAO;
import com.example.my_shop.entity.User;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.hashing.MD5;
import com.example.my_shop.util.validators.PasswordValidator;
import com.example.my_shop.util.validators.RegisterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.my_shop.util.constants.PageConstants.PROFILE_PAGE;
import static com.example.my_shop.util.constants.PageConstants.REGISTER_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class RegisterService implements Service {
    private final UserDAO userDAO = new UserDAOImpl();
    private final RegisterValidator registerValidator = new RegisterValidator();
    private final PasswordValidator passwordValidator = new PasswordValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        if (!registerValidator.isValid(request, response)) {
            request.setAttribute(ERROR_REGISTER, FILL_ALL_FIELDS);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);

        } else if (!registerValidator.isUsernameValid(request.getParameter(USERNAME))) {
            request.setAttribute(ERROR_REGISTER, WRONG_CREDENTIALS);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);

        } else if (!passwordValidator.isPasswordValid(request.getParameter(PASSWORD))) {
            request.setAttribute(ERROR_REGISTER, WRONG_CREDENTIALS);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);

        } else if (userDAO.userExists(request.getParameter(USERNAME))) {
            request.setAttribute(ERROR_REGISTER, USERNAME_TAKEN);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);

        } else if (!request.getParameter(PASSWORD).equals(request.getParameter(RETYPE_PASSWORD))) {
            request.setAttribute(ERROR_REGISTER, PASSWORDS_NOT_MATCH);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);

        } else {
            User user = new User();
            user.setUsername(request.getParameter(USERNAME));
            user.setPassword(MD5.getMd5(request.getParameter(PASSWORD)));
            user.setFirstName(request.getParameter(FIRST_NAME));
            user.setLastName(request.getParameter(LAST_NAME));
            user.setBirthDate(java.sql.Date.valueOf(request.getParameter(BIRTH_DATE)));
            user.setRegistrationDate(java.sql.Date.valueOf(LocalDate.now()));
            user.setGenderId(Long.parseLong(request.getParameter(GENDER)));
            userDAO.create(user);
            session.setAttribute(LOGGED_USER, user);
            request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
        }
    }
}

