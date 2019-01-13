package by.etc.finaltask.controller.command.common;

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

public class EditProfile implements Command {
    private static final String USER_ID = "userId";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String SEX = "sex";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter(USER_ID);
        String email = request.getParameter(EMAIL);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String sex = request.getParameter(SEX);

        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();

        try {
            userLogic.editProfile(userId, email, firstName, lastName, sex);
        } catch (UserLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (InvalidInputException e) {
            CommandDirector.getInstance().getCommand(CommandType.SHOW_EDIT_PROFILE_PAGE.toString()).execute(request, response);
        }
        CommandDirector.getInstance().getCommand(CommandType.SHOW_PROFILE.toString()).execute(request, response);
    }
}
