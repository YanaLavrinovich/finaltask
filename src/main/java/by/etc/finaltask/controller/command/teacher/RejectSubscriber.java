package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RejectSubscriber implements Command {
    private static final String STUDENT_ID = "studentId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studentId = Integer.valueOf(request.getParameter(STUDENT_ID));
        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            courseLogic.rejectSubscriber(studentId);
            Command requestCommand = CommandDirector.getInstance().getCommand(CommandType.SHOW_REQUEST.toString());
            requestCommand.execute(request, response);
        } catch (CourseLogicException e) {
            e.printStackTrace();
        }
    }
}
