package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.UserLogicException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetMark implements Command {
    private static final String MARK = "mark";
    private static final String COMMENT = "comment";
    private static final String STUDENT_ID = "studentId";
    private static final String COURSE_ID = "courseId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mark = Integer.valueOf(request.getParameter(MARK));
        String comment = request.getParameter(COMMENT);

        int studentId = Integer.valueOf(request.getParameter(STUDENT_ID));
        int courseId = Integer.valueOf(request.getParameter(COURSE_ID));

        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            courseLogic.setMark(courseId, studentId, mark, comment);
            Command requestCommand = CommandDirector.getInstance().getCommand(CommandType.SHOW_COURSE.toString());
            requestCommand.execute(request, response);
        } catch (CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
