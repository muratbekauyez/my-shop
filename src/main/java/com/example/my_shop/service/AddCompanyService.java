package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.CompanyDAOImpl;
import com.example.my_shop.database.dao.interfaces.CompanyDAO;
import com.example.my_shop.entity.Company;
import com.example.my_shop.service.init.Service;
import com.example.my_shop.util.validators.CompanyValidator;
import com.example.my_shop.util.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.PageConstants.ADD_CLOTH_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;
import static com.example.my_shop.util.constants.ParameterConstants.POSITIVE;

public class AddCompanyService implements Service {
    private final CompanyDAO companyDAO = new CompanyDAOImpl();
    private final Validator validator = new CompanyValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(validator.isValid(request, response)){
            Company company = new Company();
            company.setCompanyName(request.getParameter(COMPANY_NAME));
            company.setCountry(request.getParameter(COMPANY_COUNTRY));
            companyDAO.create(company);
            request.setAttribute(COMPANY_ADDITION, POSITIVE);;
        }else {
            request.setAttribute(COMPANY_ADDITION, NEGATIVE);
        }
        request.getRequestDispatcher(ADD_CLOTH_PAGE).forward(request,response);
    }
}
