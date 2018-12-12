package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.logic.UserLogic;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.impl.UserLogicImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.etc.finaltask.controller.command.ParameterType.LOGIN;
import static by.etc.finaltask.controller.command.ParameterType.PASSWORD;
import static by.etc.finaltask.controller.command.ParameterType.USER;


public class Authorization implements Command {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        UserLogic userLogic = new UserLogicImpl();
        try {
            if (userLogic.isValidUser(login, password)) {
                HttpSession session = request.getSession(true);
                User user = userLogic.getUserInformation(login);
                session.setAttribute(USER, user);
                response.sendRedirect("homePage");
            } else {
                //
            }
        } catch (UserLogicException e) {
            response.sendRedirect("homePage"); //to index.jsp
        }
    }
}
