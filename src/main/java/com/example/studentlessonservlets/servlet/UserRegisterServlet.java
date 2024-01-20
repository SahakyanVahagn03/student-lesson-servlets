package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.UserManager;
import com.example.studentlessonservlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)

public class UserRegisterServlet extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "C:\\Users\\37494\\IdeaProjects\\student-lesson-servlets\\uploadDirectory";
    private final UserManager USER_MANAGER = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Part picture = req.getPart("picture");
        String picName = null;
        if (picture != null && picture.getSize() > 0) {
            picName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
            picture.write(UPLOAD_DIRECTORY + File.separator + picName);
        }
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            req.setAttribute("msg", "login or password is invalid");
            req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req,resp);
        } else {
            if (USER_MANAGER.getUserByEmail(email) == null) {
                USER_MANAGER.add(User.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .picture(picName)
                        .build());
                req.getSession().setAttribute("msg","registration finished successfully,  try to enter the profile");
                resp.sendRedirect("/index.jsp");
            } else {
                req.setAttribute("msg", "this email address already exist");
                req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req,resp);
            }
        }
    }
}
