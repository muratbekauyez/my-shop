package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.CartDAOImpl;
import com.example.my_shop.database.dao.interfaces.CartDAO;
import com.example.my_shop.entity.Cart;
import com.example.my_shop.entity.Cloth;
import com.example.my_shop.entity.User;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.validators.CartValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.CLOTH_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class AddCartService implements Service {
    private final CartDAO cartDAO = new CartDAOImpl();
    private final CartValidator validator = new CartValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        int cartAmount = Integer.parseInt(request.getParameter(CART_AMOUNT));
        Long clothSizeId = Long.parseLong(request.getParameter(CLOTH_SIZE_ID));

        User user = (User) session.getAttribute(LOGGED_USER);
        Cloth cloth = (Cloth) session.getAttribute(CLOTH);

        if(!validator.isValid(request, response)){
            request.setAttribute(CART_ADDITION, BIGGER_THAN_ZERO);
        }else if(!validator.isClothAmountValid(cloth.getId(),clothSizeId, cartAmount)){
            request.setAttribute(CART_ADDITION, CART_ERROR);
        }else{
            Cart cart = new Cart();
            cart.setUserId(user.getId());
            cart.setProductId(cloth.getId());
            cart.setAmount(cartAmount);
            cart.setSizeId(clothSizeId);
            if(cartDAO.cartExists(cart)){
                request.setAttribute(CART_ADDITION, ALREADY_IN_CART);
            }else{
                cartDAO.create(cart);
                session.setAttribute(USER_CART_CLOTHES, cartDAO.getCartProducts(user.getId()));
                session.setAttribute(CART_SUM, cartDAO.getSumOfCart(user.getId()));
                request.setAttribute(CART_ADDITION, POSITIVE);
            }
        }

        request.getRequestDispatcher(CLOTH_PAGE).forward(request, response);
    }
}
