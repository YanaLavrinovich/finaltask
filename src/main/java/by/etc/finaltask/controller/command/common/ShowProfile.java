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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowProfile implements Command {
    private static final String USER_ID = "userId";
    private static final String USER = "user";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommandDirector.getInstance().getCommand(CommandType.ADD_COUNT_REQUEST.toString()).execute(request, response);

        String userId = request.getParameter(USER_ID);
        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        try {
            User user = userLogic.takeUser(userId);
            request.setAttribute(USER, user);
            request.getRequestDispatcher(JspPagePath.PROFILE_PAGE).forward(request,response);
        } catch (UserLogicException | ServletException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
