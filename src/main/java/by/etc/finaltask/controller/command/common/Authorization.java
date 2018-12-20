package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.user.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.etc.finaltask.controller.command.ParameterType.*;


public class Authorization implements Command {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        HttpSession session = request.getSession(true);
        try {
            if (userLogic.isValidUser(email, password)) {
                User user = userLogic.getUserInformation(email);
                session.setAttribute(USER, user);

                response.sendRedirect(JspPagePath.HOME_PAGE);
            } else {
                session.setAttribute("error", true);
                response.sendRedirect(JspPagePath.AUTHORIZATION);
            }
        } catch (UserLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (InvalidInputException e) {
            session.setAttribute("error", true);
            response.sendRedirect(JspPagePath.AUTHORIZATION);
        }
    }
}
