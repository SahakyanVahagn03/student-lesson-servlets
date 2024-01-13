package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.StudentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/students")
public class StudentsServlet extends HttpServlet {

    private final StudentManager STUDENT_MANAGER = new StudentManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", STUDENT_MANAGER.getAllStudents());
        req.getRequestDispatcher("/WEB-INF/students.jsp").forward(req,resp);
    }
}
