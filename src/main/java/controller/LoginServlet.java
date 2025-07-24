package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        RequestDispatcher rd;
        User users = new User();
        users.setUsername(username);
        users.setPasscode(password);

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        if (users.loginUser(username, password)) {
            session.setAttribute("users", users);
            rd = request.getRequestDispatcher("./forms/loginsuccess.jsp");
            rd.forward(request, response);
        } else {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}