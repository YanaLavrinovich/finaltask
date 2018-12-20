package by.etc.finaltask.logic;

import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.course.CourseLogicImpl;
import by.etc.finaltask.logic.user.UserLogic;
import by.etc.finaltask.logic.user.UserLogicImpl;

public class LogicFactory {
    private static final LogicFactory instance = new LogicFactory();

    private UserLogic userLogic = new UserLogicImpl();
    private CourseLogic courseLogic = new CourseLogicImpl();

    public static LogicFactory getInstance() {
        return instance;
    }

    public UserLogic getUserLogic() {
        return userLogic;
    }

    public CourseLogic getCourseLogic() {
        return courseLogic;
    }
}
