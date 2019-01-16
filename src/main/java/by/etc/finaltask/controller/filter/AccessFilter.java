package by.etc.finaltask.controller.filter;

import by.etc.finaltask.domain.User;
import by.etc.finaltask.controller.command.AccessDirector;
import by.etc.finaltask.controller.command.CommandType;
import by.etc.finaltask.controller.exception.AccessIsNotAllowedException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    private AccessDirector director = AccessDirector.getInstance();
    private static final String USER = "user";
    private static final String COMMAND = "command";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandInRequest = request.getParameter(COMMAND);
        if (commandInRequest == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        CommandType command = CommandType.valueOf(commandInRequest.toUpperCase());
        User.Role role = null;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(USER);
        if (user != null) {
            role = user.getRole();
        }
        try {
            director.checkAccess(command, role);
        } catch (AccessIsNotAllowedException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        filterChain.doFilter(request, response);
    }
}
