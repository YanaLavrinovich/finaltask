package by.etc.finaltask.controller.command.admin;

import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.user.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestoreUser implements Command {
    private static final String USER_ID = "userId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter(USER_ID);
        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        try {
            userLogic.restoreUser(userId);
            Command requestCommand = CommandDirector.getInstance().getCommand(CommandType.SHOW_ALL_USERS.toString());
            requestCommand.execute(request, response);
        } catch (InvalidInputException | UserLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
