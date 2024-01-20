package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.UserManager;
import com.example.studentlessonservlets.model.User;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")

public class UserLoginServlet extends HttpServlet {

    private final UserManager USER_MANAGER = new UserManager();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            req.getSession().setAttribute("msg", "login or password is invalid");
            resp.sendRedirect("/index.jsp");
        } else {
            User userByEmail = USER_MANAGER.getUserByEmail(email);
            if (userByEmail != null && userByEmail.getPassword().equals(password)) {
                req.getSession().setAttribute("user", userByEmail);
                resp.sendRedirect("/home");
            } else {
                req.getSession().setAttribute("msg", "login or password is invalid");
                resp.sendRedirect("/index.jsp");
            }
        }
    }
}
