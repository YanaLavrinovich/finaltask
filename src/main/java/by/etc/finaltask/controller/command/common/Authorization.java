package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.domain.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.user.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Authorization implements Command {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String ERROR = "error";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        HttpSession session = request.getSession(true);
        try {
            if (userLogic.isValidUser(email, password)) {
                User user = userLogic.getUserInformation(email);
                session.setAttribute(USER, user);
                Command command = CommandDirector.getInstance().getCommand(CommandType.SHOW_HOME_PAGE.toString());
                command.execute(request, response);
            } else {
                session.setAttribute(ERROR, true);
                response.sendRedirect(JspPagePath.AUTHORIZATION);
            }
        } catch (UserLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (InvalidInputException e) {
            session.setAttribute(ERROR, true);
            response.sendRedirect(JspPagePath.AUTHORIZATION);
        }
    }
}
