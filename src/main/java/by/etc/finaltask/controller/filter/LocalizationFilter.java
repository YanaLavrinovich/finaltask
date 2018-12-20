package by.etc.finaltask.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private static final String LANGUAGE = "language";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANGUAGE);
        if(lang == null) {
            lang = "en";
            session.setAttribute(LANGUAGE,lang);
        }
        session.setAttribute(LANGUAGE,lang);
        filterChain.doFilter(request,response);
    }
}
