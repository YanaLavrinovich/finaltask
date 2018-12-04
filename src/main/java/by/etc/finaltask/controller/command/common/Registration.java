package by.etc.finaltask.controller.command.common;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.Sex;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.logic.UserLogic;
import by.etc.finaltask.logic.builder.UserBuilder;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.impl.UserLogicImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.etc.finaltask.controller.command.ParameterType.*;

public class Registration implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        Sex sex = Sex.valueOf(request.getParameter(SEX).toUpperCase());
        Role role = Role.valueOf(request.getParameter(ROLE).toUpperCase());

        UserLogic userLogic = new UserLogicImpl();
        UserBuilder builder = new UserBuilder();

        User user = builder.build(login, password, firstName, lastName, sex, role);
        try {
            userLogic.addNewUser(user);
        } catch (UserLogicException e) {
            //log
            System.out.println("pisis");
            //in response send page with exception
        }
        response.sendRedirect("homePage");
    }
}
