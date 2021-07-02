package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.COMPANY_COUNTRY;
import static com.example.my_shop.util.constants.ParameterConstants.COMPANY_NAME;

public class CompanyValidator implements Validator{
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {
        boolean companyName = request.getParameter(COMPANY_NAME) != null && !request.getParameter(COMPANY_NAME).equals("");
        boolean companyCountry = request.getParameter(COMPANY_COUNTRY) != null && !request.getParameter(COMPANY_COUNTRY).equals("");

        return companyName && companyCountry;
    }
}
