package by.etc.controller;

import by.etc.logic.UserLogic;
import by.etc.logic.impl.UserLogicImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserLogic userLogic = new UserLogicImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        HttpSession session = req.getSession();
        String nextURL = null;
        try {
            if(userLogic.isValidUser(userLogin, userPassword)) {
                nextURL = "/WEB-INF/after.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher(nextURL).forward(req, resp);
    }
}
