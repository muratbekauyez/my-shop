package com.example.my_shop.controller;

import com.example.my_shop.service.init.Service;
import com.example.my_shop.service.init.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class MyShopController extends HttpServlet {
    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    Service service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        service = serviceFactory.getService(request.getRequestURI());
        try {
            service.execute(request, response);
        } catch (SQLException sqlException) {
            LOGGER.warn(sqlException);
        }
    }
}
