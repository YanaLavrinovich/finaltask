package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.controller.command.CommandDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.command.JspPagePath;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.training.TrainingLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShowRequest implements Command {
    private static final String USER = "user";
    private static final String REQUEST_MAP = "requestMap";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String userId = String.valueOf(user.getId());
        CommandDirector.getInstance().getCommand(CommandType.ADD_COUNT_REQUEST.toString()).execute(request, response);

        TrainingLogic trainingLogic = LogicFactory.getInstance().getTrainingLogic();
        try {
            Map<Course, List<User>> requests = trainingLogic.findRequest(userId);
            request.setAttribute(REQUEST_MAP, requests);

            request.getRequestDispatcher(JspPagePath.REQUEST_PAGE).forward(request, response);
        } catch (ServletException | CourseLogicException | InvalidInputException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
