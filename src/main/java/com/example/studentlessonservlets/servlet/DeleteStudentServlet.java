package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.LessonManager;
import com.example.studentlessonservlets.manager.StudentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    private final StudentManager STUDENT_MANAGER = new StudentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (!id.chars().allMatch(Character::isDigit)) {
            resp.sendRedirect("/");
        }else {
            STUDENT_MANAGER.deleteStudentById(Integer.parseInt(id));
            resp.sendRedirect("/students");
        }
    }
}
