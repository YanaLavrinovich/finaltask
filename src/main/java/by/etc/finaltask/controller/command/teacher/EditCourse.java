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

public class EditCourse implements Command {
    private static final String COURSE_ID = "courseId";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String DATE_START = "dateStart";
    private static final String DATE_FINISH = "dateFinish";
    private static final String ERROR = "error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String courseId = request.getParameter(COURSE_ID);
        String nameCourse = request.getParameter(NAME);
        String description = request.getParameter(DESCRIPTION);
        String dateStart = request.getParameter(DATE_START);
        String dateFinish = request.getParameter(DATE_FINISH);

        HttpSession session = request.getSession(true);
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            courseLogic.editCourse(courseId, nameCourse, description, dateStart, dateFinish);
            CommandDirector.getInstance().getCommand(CommandType.SHOW_COURSE.toString()).execute(request, response);
        } catch (CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (InvalidInputException e) {
            session.setAttribute(ERROR, true);
            CommandDirector.getInstance().getCommand(CommandType.SHOW_EDIT_COURSE_PAGE.toString()).execute(request, response);
        }
    }
}
