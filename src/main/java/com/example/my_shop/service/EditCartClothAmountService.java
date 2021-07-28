package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.CartDAOImpl;
import com.example.my_shop.database.dao.impls.ClothDAOImpl;
import com.example.my_shop.database.dao.impls.SizeDAOImpl;
import com.example.my_shop.database.dao.interfaces.CartDAO;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.database.dao.interfaces.SizeDAO;
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

import static com.example.my_shop.util.constants.PageConstants.PROFILE_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;
import static com.example.my_shop.util.validators.NumberParameterValidator.isNumberParameterValid;

public class EditCartClothAmountService implements Service {
    private final CartDAO cartDAO = new CartDAOImpl();
    private final SizeDAO sizeDAO = new SizeDAOImpl();
    private final ClothDAO clothDAO = new ClothDAOImpl();
    private final CartValidator validator = new CartValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);

        if(!(validator.isClothSizeParameterValid(request.getParameter(CLOTH_SIZE_ID)) && isNumberParameterValid(request.getParameter(CART_AMOUNT)))){
            request.setAttribute(CART_UPDATING, NEGATIVE);
        }else if (!(validator.isValid(request, response))) {
            request.setAttribute(CART_UPDATING, NEGATIVE);
        } else {
            int clothAmount = Integer.parseInt(request.getParameter(CART_AMOUNT));
            Cloth cloth = clothDAO.getCloth(Long.parseLong(request.getParameter(CLOTH_ID)));
            Long sizeId = Long.parseLong(request.getParameter(CLOTH_SIZE_ID));
            User user = (User) session.getAttribute(LOGGED_USER);

            if (!(clothAmount > 0 && clothAmount <= sizeDAO.getClothAmount(cloth.getId(), sizeId))) {
                request.setAttribute(CART_UPDATING, NEGATIVE);
            } else {
                Cart cart = new Cart();
                cart.setUserId(user.getId());
                cart.setSizeId(sizeId);
                cart.setProductId(cloth.getId());
                cart.setAmount(clothAmount);
                cartDAO.updateAmount(cart);
                session.setAttribute(USER_CART_CLOTHES, cartDAO.getCartProducts(user.getId()));
                session.setAttribute(CART_SUM, cartDAO.getSumOfCart(user.getId()));
            }


        }
        request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
    }
}
