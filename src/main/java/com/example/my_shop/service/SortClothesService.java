package com.example.my_shop.service;

import com.example.my_shop.database.dao.factory.ClothFactory;
import com.example.my_shop.database.dao.impls.ClothDAOImpl;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.entity.Cloth;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.example.my_shop.util.constants.PageConstants.FEED_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class SortClothesService implements Service {
    private final ClothDAO clothDAO = new ClothDAOImpl();
    private final ClothFactory clothFactory = new ClothFactory();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        List<Cloth> clothList;
        String sortName = request.getParameter(SORT_NAME);
        if(session.getAttribute(FILTERED_CLOTHES) != null){
            clothList = (List<Cloth>) session.getAttribute(FILTERED_CLOTHES);

        }else{
            clothList = clothDAO.getAvailableClothes();
        }

        clothList = clothFactory.sortClothes(clothList, sortName);

        session.setAttribute(FILTERED_CLOTHES,clothList);
        request.setAttribute(ARE_FILTERS_APPLIED, POSITIVE);
        request.getRequestDispatcher(FEED_PAGE).forward(request,response);

    }
}
