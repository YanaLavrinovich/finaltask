package by.etc.finaltask.controller.command.teacher;

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

public class CreateCourse implements Command {
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String DATE_START = "dateStart";
    private static final String DATE_FINISH = "dateFinish";
    private static final String USER_ID = "userId";
    private static final String ERROR = "error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nameCourse = request.getParameter(NAME);
        String description = request.getParameter(DESCRIPTION);
        String dateStart = request.getParameter(DATE_START);
        String dateFinish = request.getParameter(DATE_FINISH);
        int userId = Integer.valueOf(request.getParameter(USER_ID));

        HttpSession session = request.getSession(true);
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            courseLogic.addCourse(nameCourse, description, dateStart, dateFinish, userId);
            Command command = CommandDirector.getInstance().getCommand(CommandType.SHOW_HOME_PAGE.toString());
            command.execute(request, response);
        } catch (InvalidInputException e) {
            session.setAttribute(ERROR, true);
            response.sendRedirect(request.getRequestURI());
        } catch (CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
