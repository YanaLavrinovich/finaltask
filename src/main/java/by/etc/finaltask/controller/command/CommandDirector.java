package by.etc.finaltask.controller.command;

import by.etc.finaltask.controller.command.common.*;
import by.etc.finaltask.controller.command.teacher.TeacherCourse;
import by.etc.finaltask.controller.command.teacher.CreateCourse;

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
        commands.put(CommandType.TEACHER_COURSE, new TeacherCourse());
        commands.put(CommandType.CREATE_COURSE, new CreateCourse());
        commands.put(CommandType.SHOW_HOME_PAGE, new ShowHomePage());
    }

    public Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        return commands.get(commandName);
    }

    public static CommandDirector getInstance() {
        return instance;
    }
}
