package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.LessonManager;
import com.example.studentlessonservlets.manager.StudentManager;
import com.example.studentlessonservlets.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/addStudent")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)

public class AddStudentServlet extends HttpServlet {


    private final StudentManager STUDENT_MANAGER = new StudentManager();
    private final LessonManager LESSON_MANAGER = new LessonManager();

    private final String UPLOAD_DIRECTORY = "C:\\Users\\37494\\IdeaProjects\\student-lesson-servlets\\uploadDirectory";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lessons", LESSON_MANAGER.getAllLessons());
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String lessonId = req.getParameter("lessonId");

        if (!age.chars().allMatch(Character::isDigit) || !lessonId.chars().allMatch(Character::isDigit)) {
            resp.sendRedirect("/");
        } else {
            Part picture = req.getPart("picture");
            String picName = null;
            if (picture != null && picture.getSize() > 0) {
                picName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
                picture.write(UPLOAD_DIRECTORY + File.separator + picName);
            }
            STUDENT_MANAGER.add(Student.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(Integer.parseInt(age))
                    .lesson(LESSON_MANAGER.getLessonById(Integer.parseInt(lessonId)))
                    .picName(picName)
                    .build());
            resp.sendRedirect("/students");
        }

    }
}

