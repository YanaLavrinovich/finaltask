package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.command.JspPagePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowHomePage implements Command {
    private static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Role role = user.getRole();

        List<Command> commands = new ArrayList<>();
        if (role.equals(Role.TEACHER)) {
            commands.add(CommandDirector.getInstance().getCommand(CommandType.TEACHER_COURSE.toString()));
            commands.add(CommandDirector.getInstance().getCommand(CommandType.ADD_COUNT_REQUEST.toString()));
        }
        if (role.equals(Role.STUDENT)) {
            commands.add(CommandDirector.getInstance().getCommand(CommandType.ACTUAL_COURSES.toString()));
        }
        if(role.equals(Role.ADMIN)) {
            commands.add(CommandDirector.getInstance().getCommand(CommandType.ALL_COURSES.toString()));
        }
        for (Command command : commands) {
            command.execute(request, response);
        }
        try {
            request.getRequestDispatcher(JspPagePath.HOME_PAGE).forward(request, response);
        } catch (ServletException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
