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

import static com.example.my_shop.util.constants.PageConstants.*;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class FilterClothesService implements Service {
    private final ClothDAO clothDAO = new ClothDAOImpl();
    private final ClothFactory clothFactory = new ClothFactory();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        List<Cloth> clothes;

        String[] sizeIds = request.getParameterValues(CLOTH_SIZE_ID);
        String[] companyIds = request.getParameterValues(COMPANY_ID);

        if(sizeIds != null && companyIds != null){
            clothes = clothFactory.filterClothesByCompaniesAndSizes(sizeIds,companyIds);
        }
        else if(sizeIds != null){
            clothes = clothFactory.filterClothesByMultipleSizes(sizeIds);
        }else if(companyIds != null){
            clothes = clothFactory.filterClothesByMultipleCompanies(companyIds);
        }else{
            clothes = clothDAO.getAvailableClothes();
            session.setAttribute(FILTERED_CLOTHES, clothes);
            request.setAttribute(ARE_FILTERS_APPLIED, NEGATIVE);
            request.getRequestDispatcher(FEED_PAGE).forward(request, response);
            return;
        }

        session.setAttribute(FILTERED_CLOTHES, clothes);
        request.setAttribute(ARE_FILTERS_APPLIED, POSITIVE);

        request.getRequestDispatcher(FEED_PAGE).forward(request, response);

    }
}
