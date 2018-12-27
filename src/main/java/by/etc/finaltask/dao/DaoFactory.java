package by.etc.finaltask.dao;

import by.etc.finaltask.dao.course.CourseDao;
import by.etc.finaltask.dao.course.SQLCourseDao;
import by.etc.finaltask.dao.user.SQLUserDao;
import by.etc.finaltask.dao.user.UserDao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private UserDao userDao = new SQLUserDao();
    private CourseDao courseDao = new SQLCourseDao();

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }
}
