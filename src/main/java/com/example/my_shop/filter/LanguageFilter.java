package com.example.my_shop.filter;

import com.example.my_shop.database.dao.impls.LanguageDAOImpl;
import com.example.my_shop.database.dao.interfaces.LanguageDAO;
import com.example.my_shop.entity.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

import static com.example.my_shop.util.constants.ParameterConstants.*;

public class LanguageFilter implements Filter {
    private final LanguageDAO languageDAO = new LanguageDAOImpl();
    private final Logger LOGGER  = LogManager.getLogger(this.getClass().getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);

        if(session.getAttribute(ALL_LANGUAGES) == null){
            try {
                session.setAttribute(ALL_LANGUAGES, languageDAO.getAllLanguages());
            } catch (SQLException sqlException) {
                LOGGER.warn(sqlException);
            }
        }
        Language language = new Language();

        Long language_id = (Long) session.getAttribute(WEB_LANGUAGE_ID);;
        String language_name = (String) session.getAttribute(WEB_LANGUAGE_NAME);

        language.setId(language_id);
        language.setLanguageName(language_name);

        if (language_id == null && language_name == null) {
            session.setAttribute(WEB_LANGUAGE,language);
            session.setAttribute(WEB_LANGUAGE_ID, ENG_LANG_ID);
            session.setAttribute(WEB_LANGUAGE_NAME, ENGLISH);
        }else if(language_id.equals(ENG_LANG_ID) && language_name.equals(ENGLISH)){
            session.setAttribute(WEB_LANGUAGE,language);
            session.setAttribute(WEB_LANGUAGE_ID, ENG_LANG_ID);
            session.setAttribute(WEB_LANGUAGE_NAME, ENGLISH);
        } else if (language_id.equals(RUS_LANG_ID) && language_name.equals(RUSSIAN)) {
            session.setAttribute(WEB_LANGUAGE,language);
            session.setAttribute(WEB_LANGUAGE_ID, RUS_LANG_ID);
            session.setAttribute(WEB_LANGUAGE_NAME, RUSSIAN);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
