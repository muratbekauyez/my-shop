package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.OrderDAOImpl;
import com.example.my_shop.database.dao.interfaces.OrderDAO;
import com.example.my_shop.entity.OrderDetails;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.example.my_shop.util.constants.PageConstants.ORDER_DETAILS_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.ORDER_DETAILS_LIST;
import static com.example.my_shop.util.constants.ParameterConstants.ORDER_ID;

public class GetOrderDetailsPageService implements Service {
    private final OrderDAO orderDAO = new OrderDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        Long orderId = Long.parseLong(request.getParameter(ORDER_ID));

        List<OrderDetails> orderDetailsList = orderDAO.getOrderDetails(orderId);

        session.setAttribute(ORDER_DETAILS_LIST, orderDetailsList);


        request.getRequestDispatcher(ORDER_DETAILS_PAGE).forward(request, response);
    }
}
