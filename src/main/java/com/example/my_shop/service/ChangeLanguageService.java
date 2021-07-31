package com.example.my_shop.service;

import com.example.my_shop.database.dao.impls.LanguageDAOImpl;
import com.example.my_shop.database.dao.interfaces.LanguageDAO;
import com.example.my_shop.service.init.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.my_shop.util.constants.ParameterConstants.*;

public class ChangeLanguageService implements Service {
    private final LanguageDAO languageDAO = new LanguageDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);

        Long languageId = Long.parseLong(request.getParameter(LANGUAGE_TO_CHANGE));
        String languageName = languageDAO.getLanguageName(languageId);

        session.setAttribute(WEB_LANGUAGE_ID, languageId);
        session.setAttribute(WEB_LANGUAGE_NAME, languageName);

        request.getRequestDispatcher(request.getParameter(PAGE_PATH)).forward(request,response);
    }
}
