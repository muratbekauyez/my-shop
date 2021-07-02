package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.ClothDAOImpl;
import com.example.my_shop.database.dao.interfaces.ClothDAO;
import com.example.my_shop.entity.Cloth;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.validators.ClothValidator;
import com.example.my_shop.util.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.EDIT_CLOTHES_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class EditClothesService  implements Service {
    private final ClothDAO clothDAO = new ClothDAOImpl();
    private final Validator validator = new ClothValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (validator.validate(request, response)) {
            Cloth cloth = new Cloth();
            cloth.setId(Long.parseLong(request.getParameter(CLOTH_ID)));
            cloth.setVendorCode(request.getParameter(CLOTH_VENDOR_CODE));
            cloth.setPrice(Integer.parseInt(request.getParameter(PRICE)));
            cloth.setCompanyId(Long.parseLong(request.getParameter(COMPANY_ID)));
            Part filePart = request.getPart(CLOTH_IMAGE);
            InputStream fileContent = filePart.getInputStream();
            cloth.setImage(fileContent);
            cloth.setGenderID(clothDAO.getCloth(cloth.getId()).getGenderID());
            clothDAO.update(cloth);
            request.setAttribute(CLOTH_EDITION, POSITIVE);
        }else{
            request.setAttribute(CLOTH_EDITION, NEGATIVE);
        }

        request.getRequestDispatcher(EDIT_CLOTHES_PAGE).forward(request, response);
    }
}
