package by.etc.finaltask.controller.command;

import by.etc.finaltask.controller.exception.AccessIsNotAllowedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.etc.finaltask.bean.User.Role;

public class AccessDirector {
    private Map<CommandType, List<Role>> accessMap = new HashMap<>();
    private static AccessDirector instance = new AccessDirector();

    private static final List<Role> NO_CHECK_ACCESS = null;

    private AccessDirector() {
        accessMap.put(CommandType.AUTHORIZATION, NO_CHECK_ACCESS);
        accessMap.put(CommandType.REGISTRATION, NO_CHECK_ACCESS);
        accessMap.put(CommandType.CHANGE_LANGUAGE, allUsers());
        accessMap.put(CommandType.LOGOUT, allUsers());
        accessMap.put(CommandType.TEACHER_COURSE, teacher());
        accessMap.put(CommandType.CREATE_COURSE, teacher());
        accessMap.put(CommandType.SHOW_HOME_PAGE, allUsers());
        accessMap.put(CommandType.SHOW_REQUEST, teacher());
        accessMap.put(CommandType.ADD_COUNT_REQUEST, teacher());
        accessMap.put(CommandType.REJECT_SUBSCRIBER, teacher());
        accessMap.put(CommandType.ACCEPT_SUBSCRIBER, teacher());
        accessMap.put(CommandType.COURSE_CREATION_PAGE, teacher());
        accessMap.put(CommandType.SHOW_COURSE, allUsers());
        accessMap.put(CommandType.REMOVE_COURSE, adminAndTeacher());
        accessMap.put(CommandType.EXCLUDE_STUDENT, teacher());
        accessMap.put(CommandType.SHOW_MARK_PAGE, teacher());
        accessMap.put(CommandType.SET_MARK, teacher());
        accessMap.put(CommandType.SHOW_PROFILE, allUsers());
        accessMap.put(CommandType.SHOW_EDIT_COURSE_PAGE, adminAndTeacher());
        accessMap.put(CommandType.EDIT_COURSE, adminAndTeacher());
        accessMap.put(CommandType.ACTUAL_COURSES, student());
        accessMap.put(CommandType.REMOVE_USER, admin());
        accessMap.put(CommandType.SUBMIT_COURSE, student());
        accessMap.put(CommandType.SHOW_MY_COURSES_PAGE, student());
        accessMap.put(CommandType.SHOW_EDIT_PROFILE_PAGE, allUsers());
        accessMap.put(CommandType.EDIT_PROFILE, allUsers());
        accessMap.put(CommandType.START_TRAINING, teacher());
        accessMap.put(CommandType.STOP_TRAINING, teacher());
        accessMap.put(CommandType.ALL_COURSES, admin());
        accessMap.put(CommandType.RESTORE_COURSE, admin());
        accessMap.put(CommandType.SHOW_ALL_USERS, admin());
        accessMap.put(CommandType.RESTORE_USER, admin());
    }

    public static AccessDirector getInstance() {
        return instance;
    }

    private List<Role> allUsers() {
        List<Role> accessUsers = new ArrayList<>();
        accessUsers.add(Role.ADMIN);
        accessUsers.add(Role.TEACHER);
        accessUsers.add(Role.STUDENT);
        return accessUsers;
    }

    private List<Role> teacher() {
        List<Role> accessUsers = new ArrayList<>();
        accessUsers.add(Role.TEACHER);
        return accessUsers;
    }

    private List<Role> admin() {
        List<Role> accessUsers = new ArrayList<>();
        accessUsers.add(Role.ADMIN);
        return accessUsers;
    }

    private List<Role> student() {
        List<Role> accessUsers = new ArrayList<>();
        accessUsers.add(Role.STUDENT);
        return accessUsers;
    }

    private List<Role> adminAndTeacher() {
        List<Role> accessUsers = new ArrayList<>();
        accessUsers.add(Role.ADMIN);
        accessUsers.add(Role.TEACHER);
        return accessUsers;
    }

    public void checkAccess(CommandType command, Role role) throws AccessIsNotAllowedException {
        if (command == CommandType.AUTHORIZATION || command == CommandType.REGISTRATION) {
            return;
        }
        if (role != null) {
            List<Role> accessRole = accessMap.get(command);
            boolean isAllowed = accessRole.contains(role);
            if (isAllowed) {
                return;
            }
        }
        throw new AccessIsNotAllowedException("The access is not allowed for this role.");

    }
}
