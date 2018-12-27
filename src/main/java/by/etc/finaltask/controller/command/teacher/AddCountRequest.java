package by.etc.finaltask.controller.command.teacher;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.controller.command.Command;
import by.etc.finaltask.logic.LogicFactory;
import by.etc.finaltask.logic.course.CourseLogic;
import by.etc.finaltask.logic.exception.CourseLogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddCountRequest implements Command {
    private static final String USER = "user";
    private static final String COUNT_REQUEST = "countRequest";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int userId = user.getId();

        CourseLogic courseLogic = LogicFactory.getInstance().getCourseLogic();
        try {
            Map<Course, List<User>> requests = courseLogic.findRequest(userId);
            int count = 0;
            for (Map.Entry<Course, List<User>> entry : requests.entrySet()) {
                List<User> users = entry.getValue();
                count += users.size();
            }
            request.setAttribute(COUNT_REQUEST, count);
        } catch (CourseLogicException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
