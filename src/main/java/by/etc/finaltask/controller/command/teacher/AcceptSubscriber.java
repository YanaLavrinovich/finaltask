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
import java.io.IOException;

public class AcceptSubscriber implements Command {
    private static final String STUDENT_ID = "studentId";
    private static final String COURSE_ID = "courseId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentId = request.getParameter(STUDENT_ID);
        String courseId = request.getParameter(COURSE_ID);
        TrainingLogic trainingLogic = LogicFactory.getInstance().getTrainingLogic();
        try {
            trainingLogic.acceptSubscriber(courseId, studentId);
            Command requestCommand = CommandDirector.getInstance().getCommand(CommandType.SHOW_REQUEST.toString());
            requestCommand.execute(request, response);
        } catch (CourseLogicException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
