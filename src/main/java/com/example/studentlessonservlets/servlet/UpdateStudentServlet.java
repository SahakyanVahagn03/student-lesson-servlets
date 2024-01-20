package com.example.studentlessonservlets.servlet;

import com.example.studentlessonservlets.manager.LessonManager;
import com.example.studentlessonservlets.manager.StudentManager;
import com.example.studentlessonservlets.model.Lesson;
import com.example.studentlessonservlets.model.Student;
import com.example.studentlessonservlets.model.User;
import com.example.studentlessonservlets.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(urlPatterns = "/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    private final LessonManager LESSON_MANAGER = new LessonManager();
    private final StudentManager STUDENT_MANAGER = new StudentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("studentId");
        if (!id.chars().allMatch(Character::isDigit)) {
            resp.sendRedirect("/students");
        } else {
            List<Lesson> lessons = LESSON_MANAGER.getAllLessons();
            Student student = STUDENT_MANAGER.getStudentById(Integer.parseInt(id));
            if (lessons == null) {
                resp.sendRedirect("/");
            } else {
                req.setAttribute("lessons", lessons);
                req.setAttribute("student", student);
                req.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String lessonId = req.getParameter("lessonId");
        User user = (User) req.getSession().getAttribute("user");
        if (!age.chars().allMatch(Character::isDigit) || !lessonId.chars().allMatch(Character::isDigit)) {
            resp.sendRedirect("/");
        } else {
            if (STUDENT_MANAGER.getStudentByEmail(email) != null) {
                req.getSession().setAttribute("msg", "the student by this email already exist");
                resp.sendRedirect("/updateStudent");
            } else {
                STUDENT_MANAGER.update(Student.builder()
                        .id(Integer.parseInt(id))
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(Integer.parseInt(age))
                        .lesson(LESSON_MANAGER.getLessonById(Integer.parseInt(lessonId)))
                        .user(user)
                        .build());
                resp.sendRedirect("/students");
            }
        }
    }
}


