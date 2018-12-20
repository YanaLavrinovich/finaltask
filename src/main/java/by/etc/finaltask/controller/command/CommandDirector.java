package by.etc.finaltask.controller.command;

import by.etc.finaltask.controller.command.common.Authorization;
import by.etc.finaltask.controller.command.common.ChangeLanguage;
import by.etc.finaltask.controller.command.common.LogOut;
import by.etc.finaltask.controller.command.common.Registration;
import by.etc.finaltask.controller.command.teacher.AllCourses;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
    private Map<CommandType, Command> commands = new HashMap<>();
    private static CommandDirector instance = new CommandDirector();

    private CommandDirector() {
        commands.put(CommandType.AUTHORIZATION, new Authorization());
        commands.put(CommandType.REGISTRATION, new Registration());
        commands.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguage());
        commands.put(CommandType.LOGOUT, new LogOut());
        commands.put(CommandType.ALL_COURSES, new AllCourses());
    }

    public Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        return commands.get(commandName);
    }

    public static CommandDirector getInstance() {
        return instance;
    }
}
