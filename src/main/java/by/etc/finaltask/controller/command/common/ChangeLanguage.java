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
    private static final String URI_START = "controller?command=";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String lang = request.getParameter(LANGUAGE);
        session.setAttribute(LANGUAGE, lang);
        String prevCommand = request.getParameter(PREV_COMMAND);
        String page = request.getParameter(PAGE);
        if (prevCommand != null) {
            prevCommand = convert(prevCommand);
            response.sendRedirect(URI_START + prevCommand);
        } else if (page != null) {
            response.sendRedirect(page);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String convert(String prevCommand) {
        return prevCommand.replace(',','&');
    }
}
