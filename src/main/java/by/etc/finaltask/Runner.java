package by.etc.finaltask;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.connector.ConnectionPool;
import by.etc.finaltask.dao.connector.ConnectionPoolException;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;

import javax.servlet.http.HttpSession;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();

        int userId = 2;
        try {
            List<Course> courses = courseLogic.findCourseForTeacher(userId);

        } catch (CourseLogicException e) {
            e.printStackTrace();
        }
    }
}
