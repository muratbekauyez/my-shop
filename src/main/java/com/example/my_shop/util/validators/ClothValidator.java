package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.*;

public class ClothValidator implements Validator{
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {
        boolean vendorCode = request.getParameter(CLOTH_VENDOR_CODE) != null && !request.getParameter(CLOTH_VENDOR_CODE).equals("");
        boolean price = request.getParameter(PRICE) != null && !request.getParameter(PRICE).equals("");
        boolean companyId = request.getParameter(COMPANY_ID) != null && !request.getParameter(COMPANY_ID).equals("");
        boolean clothImage = request.getParameter(CLOTH_IMAGE) != null && !request.getParameter(CLOTH_IMAGE).equals("");

        return vendorCode && price && companyId && clothImage;

    }
}
