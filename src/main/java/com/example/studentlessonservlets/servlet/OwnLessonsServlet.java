package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.LessonManager;
import com.example.studentlessonservlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/myLessons")
public class OwnLessonsServlet extends HttpServlet {
    private final LessonManager LESSON_MANAGER = new LessonManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("myLessons", LESSON_MANAGER.getLessonsByUserId(user.getId()));
        req.getRequestDispatcher("/WEB-INF/myLessons.jsp").forward(req,resp);
    }
}
