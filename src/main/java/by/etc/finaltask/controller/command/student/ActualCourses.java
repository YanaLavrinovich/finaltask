package by.etc.finaltask.controller.command.student;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActualCourses implements Command {
    private static final String COURSE_LIST = "courseList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            List<Course> courses = courseLogic.findActualCourse();
            request.setAttribute(COURSE_LIST, courses);
        } catch (CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
