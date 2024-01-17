package com.example.studentlessonservlets.servlet;

import com.example.studentlessonservlets.manager.LessonManager;
import com.example.studentlessonservlets.model.Lesson;
import com.example.studentlessonservlets.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/updateLesson")
public class UpdateLessonServlet extends HttpServlet {
    private final LessonManager LESSON_MANAGER = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("lessonId");
        if (!id.chars().allMatch(Character::isDigit)) {
            resp.sendRedirect("/lessons");

        } else {
            Lesson lesson = LESSON_MANAGER.getLessonById(Integer.parseInt(id));
            if (lesson == null) {
                resp.sendRedirect("/lessons");
            } else {
                req.setAttribute("lesson", lesson);
                req.getRequestDispatcher("/WEB-INF/updateLesson.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String duration = req.getParameter("duration");
        String lecturerName = req.getParameter("lecturerName");
        String price = req.getParameter("price");
        try {
            if (LESSON_MANAGER.getLessonById(Integer.parseInt(id)) == null) {
                resp.sendRedirect("/");
            } else {
                Lesson lesson = lessonBuilder(id, name, duration, lecturerName, price);
                LESSON_MANAGER.update(lesson);
                resp.sendRedirect("/lessons");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Lesson lessonBuilder(String... lessonObjectFields) throws ParseException {
        return Lesson.builder()
                .id(Integer.parseInt(lessonObjectFields[0]))
                .name(lessonObjectFields[1])
                .duration(TimeUtil.stringToTime(lessonObjectFields[2]))
                .lecturerName(lessonObjectFields[3])
                .price(Double.parseDouble(lessonObjectFields[4]))
                .build();
    }
}

