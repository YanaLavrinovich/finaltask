package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AllCourses implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        try {
            List<Course> courses = courseLogic.findCourseForTeacher(userId);
            request.setAttribute("courseList", courses);
        } catch (CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        response.sendRedirect(JspPagePath.HOME_PAGE);

    }
}
