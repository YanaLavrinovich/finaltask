package by.etc.finaltask.controller.command;

import by.etc.finaltask.controller.command.common.Authorization;
import by.etc.finaltask.controller.command.common.Registration;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
    private Map<CommandType, Command> commands = new HashMap<>();
    private static CommandDirector instance = new CommandDirector();

    private CommandDirector() {
        commands.put(CommandType.AUTHORIZATION, new Authorization());
        commands.put(CommandType.REGISTRATION, new Registration());
    }

    public Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        return commands.get(commandName);
    }

    public static CommandDirector getInstance() {
        return instance;
    }
}
