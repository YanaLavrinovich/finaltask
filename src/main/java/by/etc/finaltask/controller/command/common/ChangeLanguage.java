package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguage implements Command {
    private static final String LANGUAGE = "language";
    private static final String PREV_COMMAND = "prev_command";
    private static final String PAGE = "page";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String lang = request.getParameter(LANGUAGE);
        session.setAttribute(LANGUAGE, lang);
        String prev_command = request.getParameter(PREV_COMMAND);
        String page = request.getParameter(PAGE);
        if(prev_command != null) {
            response.sendRedirect("controller?command=" + prev_command);
        } else if(page != null) {
            response.sendRedirect(page);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}