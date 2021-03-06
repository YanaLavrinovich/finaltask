package by.etc.finaltask.controller.command;

import by.etc.finaltask.controller.command.admin.AllCourses;
import by.etc.finaltask.controller.command.admin.AllUsers;
import by.etc.finaltask.controller.command.admin.RestoreCourse;
import by.etc.finaltask.controller.command.admin.RestoreUser;
import by.etc.finaltask.controller.command.common.*;
import by.etc.finaltask.controller.command.student.ActualCourses;
import by.etc.finaltask.controller.command.student.ShowMyCoursesPage;
import by.etc.finaltask.controller.command.student.SubmitCourse;
import by.etc.finaltask.controller.command.teacher.*;

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
        commands.put(CommandType.SHOW_REQUEST, new ShowRequest());
        commands.put(CommandType.ADD_COUNT_REQUEST, new AddCountRequest());
        commands.put(CommandType.REJECT_SUBSCRIBER, new RejectSubscriber());
        commands.put(CommandType.ACCEPT_SUBSCRIBER, new AcceptSubscriber());
        commands.put(CommandType.COURSE_CREATION_PAGE, new ShowCreateCoursePage());
        commands.put(CommandType.SHOW_COURSE, new ShowCourse());
        commands.put(CommandType.REMOVE_COURSE, new RemoveCourse());
        commands.put(CommandType.EXCLUDE_STUDENT, new ExcludeStudent());
        commands.put(CommandType.SHOW_MARK_PAGE, new ShowMarkPage());
        commands.put(CommandType.SET_MARK, new SetMark());
        commands.put(CommandType.SHOW_PROFILE, new ShowProfile());
        commands.put(CommandType.SHOW_EDIT_COURSE_PAGE, new ShowEditCoursePage());
        commands.put(CommandType.EDIT_COURSE, new EditCourse());
        commands.put(CommandType.ACTUAL_COURSES, new ActualCourses());
        commands.put(CommandType.REMOVE_USER, new RemoveUser());
        commands.put(CommandType.SUBMIT_COURSE, new SubmitCourse());
        commands.put(CommandType.SHOW_MY_COURSES_PAGE, new ShowMyCoursesPage());
        commands.put(CommandType.SHOW_EDIT_PROFILE_PAGE, new ShowEditProfilePage());
        commands.put(CommandType.EDIT_PROFILE, new EditProfile());
        commands.put(CommandType.START_TRAINING, new StartTraining());
        commands.put(CommandType.STOP_TRAINING, new StopTraining());
        commands.put(CommandType.ALL_COURSES, new AllCourses());
        commands.put(CommandType.RESTORE_COURSE, new RestoreCourse());
        commands.put(CommandType.SHOW_ALL_USERS, new AllUsers());
        commands.put(CommandType.RESTORE_USER, new RestoreUser());
    }

    public Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        return commands.get(commandName);
    }

    public static CommandDirector getInstance() {
        return instance;
    }
}
