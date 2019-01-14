package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.user.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveUser implements Command {
    private static final String USER = "user";
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER);
        String id = String.valueOf(user.getId());
        User.Role role = user.getRole();
        String userRole;
        if (role.equals(User.Role.ADMIN)) {
            id = request.getParameter(USER_ID);
            userRole = request.getParameter(USER_ROLE);
            userRole = userRole.toLowerCase();
        } else {
            userRole = String.valueOf(role).toLowerCase();
        }
        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        try {
            userLogic.remove(id, userRole);
            if (role.equals(User.Role.ADMIN)) {
                CommandDirector.getInstance().getCommand(CommandType.SHOW_ALL_USERS.toString()).execute(request, response);
            } else {
                CommandDirector.getInstance().getCommand(CommandType.LOGOUT.toString()).execute(request, response);
            }
        } catch (UserLogicException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
