package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        UserDAO dao = new UserDAO();
        if (dao.validate(user)) {
            req.getSession().setAttribute("username", username);
            res.sendRedirect("forms/dashboard.jsp");
        } else {
            req.setAttribute("error", "Invalid credentials");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}
