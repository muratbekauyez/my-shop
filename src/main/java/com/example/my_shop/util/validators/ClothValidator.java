package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import static com.example.my_shop.util.constants.ParameterConstants.*;


public class ClothValidator implements Validator{
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean vendorCode = request.getParameter(CLOTH_VENDOR_CODE) != null && !request.getParameter(CLOTH_VENDOR_CODE).equals("");
        boolean price = request.getParameter(PRICE) != null && !request.getParameter(PRICE).equals("");
        boolean companyId = request.getParameter(COMPANY_ID) != null && !request.getParameter(COMPANY_ID).equals("");
        boolean priceBiggerZero = Integer.parseInt(request.getParameter(PRICE)) > 0;
        return vendorCode && price && companyId && priceBiggerZero;

    }

    public boolean isImageFileValid(Part filePart, HttpServletRequest request) {
        String fileName = filePart.getSubmittedFileName();
        String mimiType = request.getServletContext().getMimeType(fileName);
        return mimiType.startsWith("image/");
    }

}
