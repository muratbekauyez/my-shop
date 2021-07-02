package com.example.my_shop.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Validator {

    boolean validate(HttpServletRequest request, HttpServletResponse response);
}
