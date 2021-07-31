package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.CartDAOImpl;
import com.example.my_shop.database.dao.interfaces.CartDAO;
import com.example.my_shop.entity.Cart;
import com.example.my_shop.entity.User;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.PROFILE_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class DeleteCartService implements Service {
    private final CartDAO cartDAO = new CartDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute(LOGGED_USER);

        Long clothId = Long.parseLong(request.getParameter(CLOTH_ID));
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setProductId(clothId);
        cartDAO.delete(cart);

        session.setAttribute(USER_CART_CLOTHES, cartDAO.getCartProducts(user.getId()));
        session.setAttribute(CART_SUM, cartDAO.getSumOfCart(user.getId()));

        request.getRequestDispatcher(PROFILE_PAGE).forward(request,response);
    }
}
