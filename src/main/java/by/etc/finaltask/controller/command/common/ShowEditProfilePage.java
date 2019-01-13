package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.user.UserLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowEditProfilePage implements Command {
    private static final String USER = "user";
    private static final String TEACHER_ROLE = "TEACHER";
    private static final String USER_ID = "userId";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Role role = user.getRole();

        if (role.toString().equals(TEACHER_ROLE)) {
            CommandDirector.getInstance().getCommand(CommandType.ADD_COUNT_REQUEST.toString()).execute(request, response);
        }
        String userId = request.getParameter(USER_ID);

        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        try {
            User editUser = userLogic.takeUser(userId);
            request.setAttribute(USER, editUser);
            request.getRequestDispatcher(JspPagePath.EDIT_PROFILE_PAGE).forward(request,response);
        } catch (ServletException | UserLogicException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
