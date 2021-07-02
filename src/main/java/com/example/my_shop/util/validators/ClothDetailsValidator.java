package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my_shop.util.constants.ParameterConstants.*;
import static com.example.my_shop.util.constants.ParameterConstants.CLOTH_ABOUT;

public class ClothDetailsValidator implements Validator{
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {
        boolean clothId = request.getParameter(CLOTH_ID) != null && !request.getParameter(CLOTH_ID).equals("");
        boolean clothLanguageId = request.getParameter(CLOTH_LANGUAGE_ID) != null && !request.getParameter(CLOTH_LANGUAGE_ID).equals("");
        boolean clothName = request.getParameter(CLOTH_NAME) != null && !request.getParameter(CLOTH_NAME).equals("");
        boolean clothColor = request.getParameter(CLOTH_COLOR) != null && !request.getParameter(CLOTH_COLOR).equals("");
        boolean numberOfPockets = request.getParameter(CLOTH_NUMBER_OF_POCKETS) != null && !request.getParameter(CLOTH_NUMBER_OF_POCKETS).equals("");
        boolean clothSeason = request.getParameter(CLOTH_SEASON) != null && !request.getParameter(CLOTH_SEASON).equals("");
        boolean clothPattern = request.getParameter(CLOTH_PATTERN) != null && !request.getParameter(CLOTH_PATTERN).equals("");
        boolean clothAbout = request.getParameter(CLOTH_ABOUT) != null && !request.getParameter(CLOTH_ABOUT).equals("");

        return clothId && clothLanguageId && clothName && clothColor && numberOfPockets && clothSeason && clothPattern && clothAbout;
    }
}
