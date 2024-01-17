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
import java.sql.Time;
import java.text.ParseException;

@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {


    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String duration = req.getParameter("duration");
        String lecturerName = req.getParameter("lecturer_name");
        String price = req.getParameter("price");
        if (!price.chars().allMatch(Character::isDigit)) {
           resp.sendRedirect("/");
        } else {
            try {
                lessonManager.add(Lesson.builder()
                        .name(name)
                        .duration(TimeUtil.stringToTime(duration))
                        .lecturerName(lecturerName)
                        .price(Double.parseDouble(price))
                        .build());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("/lessons");
        }

    }
}

