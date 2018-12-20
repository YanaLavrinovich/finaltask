package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguage implements Command {
    private static final String LANGUAGE = "language";
    private static final String PAGE = "page";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String page = request.getParameter(PAGE);
        String lang = request.getParameter(LANGUAGE);
        session.setAttribute(LANGUAGE, lang);
        response.sendRedirect(page);
    }
}
