package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.training.TrainingLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetMark implements Command {
    private static final String MARK = "mark";
    private static final String COMMENT = "comment";
    private static final String STUDENT_ID = "studentId";
    private static final String COURSE_ID = "courseId";
    private static final String ERROR = "error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mark = request.getParameter(MARK);
        String comment = request.getParameter(COMMENT);

        String studentId = request.getParameter(STUDENT_ID);
        String courseId = request.getParameter(COURSE_ID);

        HttpSession session = request.getSession(true);
        TrainingLogic trainingLogic = LogicFactory.getInstance().getTrainingLogic();
        try {
            trainingLogic.setMark(courseId, studentId, mark, comment);
            Command requestCommand = CommandDirector.getInstance().getCommand(CommandType.SHOW_COURSE.toString());
            requestCommand.execute(request, response);
        } catch (CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (InvalidInputException e) {
            session.setAttribute(ERROR, true);
            CommandDirector.getInstance().getCommand(CommandType.SHOW_MARK_PAGE.toString()).execute(request,response);
        }
    }
}
