package by.etc.finaltask.controller.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final String LANGUAGE = "language";
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(LANGUAGE, DEFAULT_LOCALE);
    }
}
