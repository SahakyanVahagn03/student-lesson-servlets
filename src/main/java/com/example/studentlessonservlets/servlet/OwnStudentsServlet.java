package com.example.studentlessonservlets.servlet;

import com.example.studentlessonservlets.manager.LessonManager;
import com.example.studentlessonservlets.manager.StudentManager;
import com.example.studentlessonservlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/myStudents")

public class OwnStudentsServlet extends HttpServlet{
    private final StudentManager STUDENT_MANAGER = new StudentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("myStudents", STUDENT_MANAGER.getStudentsByUserId(user.getId()));
        req.getRequestDispatcher("/WEB-INF/myStudents.jsp").forward(req, resp);
    }
}


