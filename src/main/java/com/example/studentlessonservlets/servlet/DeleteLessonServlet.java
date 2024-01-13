package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.LessonManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteLesson")
public class DeleteLessonServlet extends HttpServlet {

    private LessonManager lessonManager = new LessonManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("lessonId");
        if (!id.chars().allMatch(Character::isDigit)){
            resp.sendRedirect("/");
        }else {
            lessonManager.deleteLessonById(Integer.parseInt(id));
            resp.sendRedirect("/lessons");
        }
    }
}
