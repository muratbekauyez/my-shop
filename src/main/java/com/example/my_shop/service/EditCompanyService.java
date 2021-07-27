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

import static com.example.my_shop.util.constants.PageConstants.EDIT_COMPANY_PAGE;
import static com.example.my_shop.util.constants.ParameterConstants.*;

public class EditCompanyService implements Service {
    private final CompanyDAO companyDAO = new CompanyDAOImpl();
    private final Validator validator = new CompanyValidator();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(validator.isValid(request, response)){
            Company company = new Company();
            company.setId(Long.parseLong(request.getParameter(COMPANY_ID)));
            company.setCompanyName(request.getParameter(COMPANY_NAME));
            company.setCountry(request.getParameter(COMPANY_COUNTRY));
            companyDAO.update(company);
            request.setAttribute(COMPANY_EDITION, POSITIVE);
        }else {
            request.setAttribute(COMPANY_EDITION, NEGATIVE);
        }
        request.getRequestDispatcher(EDIT_COMPANY_PAGE).forward(request,response);
    }
}
