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
        User user = (User) req.getSession().getAttribute("user");
        try {
            if (LESSON_MANAGER.getLessonById(Integer.parseInt(id)) == null) {
                resp.sendRedirect("/");
            } else {
                if (!price.chars().allMatch(Character::isDigit) || name.trim().isEmpty()) {
                    req.getSession().setAttribute("msg", "invalid fields");
                    resp.sendRedirect("/updateLesson");
                } else if (LESSON_MANAGER.getLessonByName(name) == null) {
                    req.getSession().setAttribute("msg", "the lesson by this name already exist");
                    resp.sendRedirect("/updateLesson");
                }else {
                    LESSON_MANAGER.update(
                     Lesson.builder()
                            .id(Integer.parseInt(id))
                            .name(name)
                            .duration(TimeUtil.stringToTime(duration))
                            .lecturerName(lecturerName)
                            .price(Double.parseDouble(price))
                            .user(user)
                            .build());
                    resp.sendRedirect("/lessons");
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}

