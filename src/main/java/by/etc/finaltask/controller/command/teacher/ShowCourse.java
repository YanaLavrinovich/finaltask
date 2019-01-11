package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowCourse implements Command {
    private static final String COURSE_ID = "courseId";
    private static final String COURSE = "course";
    private static final String STUDENT_LIST = "studentList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommandDirector.getInstance().getCommand(CommandType.ADD_COUNT_REQUEST.toString()).execute(request, response);

        String courseId = request.getParameter(COURSE_ID);
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            Course course = courseLogic.takeCourse(courseId);
            List<User> students = courseLogic.takeStudent(courseId);
            request.setAttribute(COURSE, course);
            request.setAttribute(STUDENT_LIST, students);
            request.getRequestDispatcher(JspPagePath.COURSE_PAGE).forward(request, response);
        } catch (ServletException | CourseLogicException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
