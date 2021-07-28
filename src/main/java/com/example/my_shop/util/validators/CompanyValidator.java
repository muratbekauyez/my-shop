package com.example.my_shop.util.validators;

import com.example.my_shop.database.dao.impls.CompanyDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.ParameterConstants.COMPANY_COUNTRY;
import static com.example.my_shop.util.constants.ParameterConstants.COMPANY_NAME;

public class CompanyValidator implements Validator{
    private final CompanyDAOImpl companyDAO = new CompanyDAOImpl();

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean companyName = request.getParameter(COMPANY_NAME) != null && !request.getParameter(COMPANY_NAME).equals("");
        boolean companyCountry = request.getParameter(COMPANY_COUNTRY) != null && !request.getParameter(COMPANY_COUNTRY).equals("");

        return companyName && companyCountry;
    }

    public boolean companyNameExists(String request) throws SQLException {
        return companyDAO.getCompany(request) != null;
    }
}
