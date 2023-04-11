package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession(false);

        try {
            if (httpSession != null && httpSession.getAttribute("user") != null) {
                resp.sendRedirect("/user/hello.jsp");
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean hasUser = Users.getInstance().getUsers().contains(login);

        try {
            if (hasUser && !password.isEmpty()) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", login);
                resp.sendRedirect("/user/hello.jsp");
            } else {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException exception) {
            exception.printStackTrace();
        }

    }
}
