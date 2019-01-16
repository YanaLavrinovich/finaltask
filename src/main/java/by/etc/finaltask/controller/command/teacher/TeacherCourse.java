package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.domain.Course;
import by.etc.finaltask.domain.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TeacherCourse implements Command {
    private static final String USER = "user";
    private static final String COURSE_LIST = "courseList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String userId = String.valueOf(user.getId());
        try {
            List<Course> courses = courseLogic.findCourseForTeacher(userId);
            request.setAttribute(COURSE_LIST, courses);
        } catch (CourseLogicException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
