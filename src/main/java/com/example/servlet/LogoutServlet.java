package com.example.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession httpSession = req.getSession(false);

        if (httpSession != null) {
            httpSession.removeAttribute("user");
            httpSession.invalidate();
        }

        try {
            resp.sendRedirect("/login.jsp");
        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }
}
