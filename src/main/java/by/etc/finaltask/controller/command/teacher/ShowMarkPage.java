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
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.user.UserLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowMarkPage implements Command {
    private static final String STUDENT_ID = "studentId";
    private static final String COURSE_ID = "courseId";
    private static final String STUDENT = "student";
    private static final String COURSE = "course";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studentId = Integer.valueOf(request.getParameter(STUDENT_ID));
        int courseId = Integer.valueOf(request.getParameter(COURSE_ID));

        CommandDirector.getInstance().getCommand(CommandType.ADD_COUNT_REQUEST.toString()).execute(request, response);
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        UserLogic userLogic = LogicFactory.getInstance().getUserLogic();
        try {
            Course course = courseLogic.takeCourse(courseId);
            User student = userLogic.takeUser(studentId);
            request.setAttribute(COURSE, course);
            request.setAttribute(STUDENT, student);
            request.getRequestDispatcher(JspPagePath.MARK_PAGE).forward(request, response);
        } catch (ServletException | CourseLogicException | UserLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
