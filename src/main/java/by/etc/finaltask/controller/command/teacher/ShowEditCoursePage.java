package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.domain.Course;
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

public class ShowEditCoursePage implements Command {
    private static final String COURSE_ID = "courseId";
    private static final String COURSE = "course";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommandDirector.getInstance().getCommand(CommandType.ADD_COUNT_REQUEST.toString()).execute(request, response);

        String courseId = request.getParameter(COURSE_ID);

        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            Course course = courseLogic.takeCourse(courseId);
            request.setAttribute(COURSE, course);
            request.getRequestDispatcher(JspPagePath.EDIT_COURSE_PAGE).forward(request,response);
        } catch (CourseLogicException | ServletException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
