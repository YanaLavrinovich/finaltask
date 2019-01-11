package by.etc.finaltask.controller.command.student;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SubmitCourse implements Command {
    private static final String COURSE_ID = "courseId";
    private static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER);
        String id = String.valueOf(user.getId());
        String courseId = request.getParameter(COURSE_ID);

        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            courseLogic.submitCourse(id, courseId);
            CommandDirector.getInstance().getCommand(CommandType.SHOW_HOME_PAGE.toString()).execute(request, response);
        } catch (CourseLogicException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
