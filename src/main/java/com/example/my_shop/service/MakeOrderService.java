package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.CartDAOImpl;
import com.example.my_shop.database.dao.impls.ClothDAOImpl;
import com.example.my_shop.database.dao.impls.OrderDAOImpl;
import com.example.my_shop.database.dao.impls.SizeDAOImpl;
import com.example.my_shop.database.dao.interfaces.CartDAO;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.database.dao.interfaces.OrderDAO;
import com.example.my_shop.database.dao.interfaces.SizeDAO;
import com.example.my_shop.entity.Cart;
import com.example.my_shop.entity.Order;
import com.example.my_shop.entity.OrderDetails;
import com.example.my_shop.entity.User;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.my_shop.util.constants.PageConstants.PROFILE_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class MakeOrderService implements Service {
    private final CartDAO cartDAO = new CartDAOImpl();
    private final ClothDAO clothDAO = new ClothDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final SizeDAO sizeDAO = new SizeDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Long orderId;
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(LOGGED_USER);
        List<Cart> userCartClothes = cartDAO.getCartProducts(user.getId());


        for (Cart cartCloth: userCartClothes) {
            if(cartCloth.getAmount() > sizeDAO.getAmountOfClothInSize(cartCloth.getProductId(), cartCloth.getSizeId())){
                request.setAttribute(ORDER_COMPLETION, NEGATIVE);
                request.getRequestDispatcher(PROFILE_PAGE).forward(request,response);
                return;
            }
        }

        Order order = new Order();
        order.setDate(java.sql.Date.valueOf(LocalDate.now()));
        order.setTotalPrice(Integer.parseInt(request.getParameter(TOTAL_PRICE)));
        order.setStatusId(1L);
        order.setUserId(user.getId());

        orderId = orderDAO.create(order);
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (Cart cart : userCartClothes){
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(orderId);
            orderDetails.setProductId(cart.getProductId());
            orderDetails.setSizeId(cart.getSizeId());
            orderDetails.setProductPrice(clothDAO.getCloth(cart.getProductId()).getPrice());
            orderDetails.setAmount(cart.getAmount());
            orderDetailsList.add(orderDetails);
        }

        orderDAO.addOrderDetails(orderDetailsList);
        orderDAO.reduceAmountOfClothes(orderDetailsList);
        cartDAO.deleteAllUserProducts(user.getId());

        session.setAttribute(USER_CART_CLOTHES, null);
        session.setAttribute(CART_SUM, null);
        session.setAttribute(USER_ORDERS, orderDAO.getUserOrders(user.getId()));
        request.setAttribute(ORDER_COMPLETION, POSITIVE);

        request.getRequestDispatcher(PROFILE_PAGE).forward(request,response);


    }
}
