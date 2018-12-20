package by.etc.finaltask.controller;

import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CommandDirector commandDirector = CommandDirector.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = commandDirector.getCommand(commandName);
        command.execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // response.setContentType("test/html");
        String commandName = request.getParameter("command");
        Command command = commandDirector.getCommand(commandName);
        command.execute(request, response);

    }
}
