package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.bean.User;
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


public class Registration implements Command {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String SEX = "sex";
    private static final String ROLE = "role";
    private static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String sex = request.getParameter(SEX);
        String role = request.getParameter(ROLE);

        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();

        HttpSession session = request.getSession(true);

        try {
            userLogic.addNewUser(email, firstName, lastName, sex, role, password);
            User user = userLogic.getUserInformation(email);
            session.setAttribute(USER, user);
        } catch (UserLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (InvalidInputException e) {
            session.setAttribute("error", true);
            response.sendRedirect(JspPagePath.REGISTRATION);
        }

        Command command = CommandDirector.getInstance().getCommand(CommandType.SHOW_HOME_PAGE.toString());
        command.execute(request, response);

    }
}
