package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Training;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.training.TrainingLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Statement;
import java.util.List;

public class ShowCourse implements Command {
    private static final String COURSE_ID = "courseId";
    private static final String COURSE = "course";
    private static final String STUDENT_LIST = "studentList";
    private static final String USER = "user";
    private static final String COURSE_STATUS = "courseStatus";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        User.Role role = user.getRole();
        String userId = String.valueOf(user.getId());
        String courseId = request.getParameter(COURSE_ID);

        TrainingLogic trainingLogic = LogicFactory.getInstance().getTrainingLogic();
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        if (role.equals(User.Role.TEACHER)) {
            CommandDirector.getInstance().getCommand(CommandType.ADD_COUNT_REQUEST.toString()).execute(request, response);
        } else if (role.equals(User.Role.STUDENT)) {
            String courseStatus = null;
            try {
                courseStatus = trainingLogic.takeCourseStatus(userId, courseId);

            } catch (CourseLogicException | InvalidInputException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            request.setAttribute(COURSE_STATUS, courseStatus);
        }

        try {
            Course course = courseLogic.takeCourse(courseId);
            List<Training> students = trainingLogic.takeStudentForCourse(courseId);
            request.setAttribute(COURSE, course);
            request.setAttribute(STUDENT_LIST, students);
            request.getRequestDispatcher(JspPagePath.COURSE_PAGE).forward(request, response);
        } catch (ServletException | CourseLogicException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
