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
import java.io.IOException;

public class StopTraining implements Command {
    private static final String COURSE_ID = "courseId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String courseId = request.getParameter(COURSE_ID);

        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            courseLogic.stopTraining(courseId);
            CommandDirector.getInstance().getCommand(CommandType.SHOW_COURSE.toString()).execute(request,response);
        } catch (InvalidInputException | CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}