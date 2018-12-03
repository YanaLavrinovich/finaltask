package by.etc.finaltask.controller;

import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.logic.UserLogic;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CommandDirector commandDirector = CommandDirector.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("test/html");
        String commandName = request.getParameter("command");
        Command command = commandDirector.getCommand(commandName);
        command.execute(request, response);

    }
}
