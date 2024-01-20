package com.example.studentlessonservlets.servlet;


import com.example.studentlessonservlets.manager.LessonManager;
import com.example.studentlessonservlets.manager.UserManager;
import com.example.studentlessonservlets.model.Lesson;
import com.example.studentlessonservlets.model.User;
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


    private LessonManager LESSON_MANAGER = new LessonManager();
    private final UserManager USER_MANAGER = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String duration = req.getParameter("duration");
        String lecturerName = req.getParameter("lecturer_name");
        String price = req.getParameter("price");
        if (!price.chars().allMatch(Character::isDigit) || name.trim().isEmpty()) {
            req.getSession().setAttribute("msg", "invalid fields");
            resp.sendRedirect("/addLesson");
        } else {
            User user = (User) req.getSession().getAttribute("user");
            if (LESSON_MANAGER.getLessonByName(name) != null) {
                req.getSession().setAttribute("msg", "the lesson by this name already exist");
                resp.sendRedirect("/addLesson");
            } else {
                try {
                    LESSON_MANAGER.add(Lesson.builder()
                            .name(name)
                            .duration(TimeUtil.stringToTime(duration))
                            .lecturerName(lecturerName)
                            .price(Double.parseDouble(price))
                            .user(USER_MANAGER.getUserById(user.getId()))
                            .build());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/lessons");
            }
        }
    }
}

