package by.etc.finaltask.controller.command.student;

import by.etc.finaltask.bean.Training;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowMyCoursesPage implements Command {
    private static final String USER = "user";
    private static final String TRAINING_LIST = "trainingList";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER);
        String userId = String.valueOf(user.getId());

        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            List<Training> trainingList = courseLogic.takeTraining(userId);
            request.setAttribute(TRAINING_LIST, trainingList);
            request.getRequestDispatcher(JspPagePath.MY_COURSES_PAGE).forward(request, response);
        } catch (ServletException | InvalidInputException | CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
