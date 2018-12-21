package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowHomePage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = user.getRole();
        Command command = null;
        if (role.toString().equals("TEACHER")) {
            command = CommandDirector.getInstance().getCommand("TEACHER_COURSE");
        }
        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
