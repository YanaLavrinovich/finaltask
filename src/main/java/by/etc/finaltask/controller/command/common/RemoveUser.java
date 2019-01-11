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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER);
        String id = String.valueOf(user.getId());
        String role = String.valueOf(user.getRole());
        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        try {
            userLogic.remove(id, role);
            CommandDirector.getInstance().getCommand(CommandType.LOGOUT.toString()).execute(request, response);
        } catch (UserLogicException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
