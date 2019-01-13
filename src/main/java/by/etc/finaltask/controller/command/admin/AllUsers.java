package by.etc.finaltask.controller.command.admin;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.user.UserLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllUsers implements Command {
    private static final String USER_LIST = "userList";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        try {
            List<User> users = userLogic.findAllUsers();
            request.setAttribute(USER_LIST, users);
            request.getRequestDispatcher(JspPagePath.ALL_USERS).forward(request, response);
        } catch (UserLogicException | ServletException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
