package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.CartDAOImpl;
import com.example.my_shop.database.dao.impls.OrderDAOImpl;
import com.example.my_shop.database.dao.impls.UserDAOImpl;
import com.example.my_shop.database.dao.interfaces.CartDAO;
import com.example.my_shop.database.dao.interfaces.OrderDAO;
import com.example.my_shop.database.dao.interfaces.UserDAO;
import com.example.my_shop.entity.User;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.validators.LoginValidator;
import com.example.my_shop.util.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.LOGIN_PAGE;
import static com.example.my_shop.util.constants.PageConstants.PROFILE_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class LoginService implements Service {
    private final UserDAO userDAO = new UserDAOImpl();
    private final CartDAO cartDAO = new CartDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final Validator validator = new LoginValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        if (validator.validate(request, response)) {
            User user = userDAO.getUserByLoginAndPassword(request.getParameter(USERNAME), request.getParameter(PASSWORD));
            if (user != null) {
                session.setAttribute(LOGGED_USER, user);
                session.setAttribute(USER_CART_CLOTHES, cartDAO.getCartProducts(user.getId()));
                session.setAttribute(CART_SUM, cartDAO.getSumOfCart(user.getId()));
                session.setAttribute(USER_ORDERS, orderDAO.getUserOrders(user.getId()));
                request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
            } else {
                request.setAttribute(ERROR_LOGIN, WRONG_CREDENTIALS);
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }

        } else {
            request.setAttribute(ERROR_LOGIN, FILL_ALL_FIELDS);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
