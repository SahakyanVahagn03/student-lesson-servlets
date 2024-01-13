package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.LessonManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/lessons")
public class LessonsServlet extends HttpServlet {

    private final LessonManager LESSON_MANAGER = new LessonManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lessons", LESSON_MANAGER.getAllLessons());
        req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req,resp);
    }
}
