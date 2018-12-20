package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.JspPagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements Command {
    private static final String LANGUAGE = "language";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String lang = (String) session.getAttribute(LANGUAGE);
        session.invalidate();
        request.getSession().setAttribute(LANGUAGE, lang);
        response.sendRedirect(JspPagePath.AUTHORIZATION);
    }
}
