package com.example.studentlessonservlets.manager;


import com.example.studentlessonservlets.dbConnectionProvider.DBConnectionProvider;
import com.example.studentlessonservlets.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LessonManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserManager USER_MANAGER = new UserManager();

    public void add(Lesson lesson) {
        String sql = "INSERT INTO lesson(name,duration,lecturer_name,price,user_id) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setTime(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setDouble(5, lesson.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                lesson.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lesson> getAllLessons() {
        String sql = "SELECT * FROM lesson";
        return giveLessonsList(sql);
    }

    public Lesson getLessonById(int id) {
        String sql = "SELECT * FROM lesson WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getTime("duration"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .user(USER_MANAGER.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteLessonById(int id) {
        String sql = "DELETE  FROM lesson WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Lesson lesson) {
        String sql = "UPDATE lesson SET  name = ?, duration = ?, lecturer_name = ?,price = ?, user_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setTime(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setInt(5, lesson.getUser().getId());
            preparedStatement.setInt(6, lesson.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lesson> getLessonsByUserId(int userId) {
        String sql = "SELECT * FROM lesson WHERE user_id=" + userId;
        return giveLessonsList(sql);
    }

    private List<Lesson> giveLessonsList(String sql) {
        List<Lesson> lessons = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getTime("duration"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .user(USER_MANAGER.getUserById(resultSet.getInt("user_id")))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }


    public Lesson getLessonByName(String name) {
        String sql = "SELECT * FROM lesson WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getTime("duration"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .user(USER_MANAGER.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
