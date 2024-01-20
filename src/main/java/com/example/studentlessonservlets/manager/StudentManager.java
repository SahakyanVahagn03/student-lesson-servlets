package com.example.studentlessonservlets.manager;


import com.example.studentlessonservlets.dbConnectionProvider.DBConnectionProvider;
import com.example.studentlessonservlets.model.Lesson;
import com.example.studentlessonservlets.model.Student;
import com.example.studentlessonservlets.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final LessonManager LESSON_MANAGER = new LessonManager();
    private final UserManager USER_MANAGER = new UserManager();

    public void add(Student student) {
        String sql = "INSERT INTO student(name,surname,email,age,lesson_id,pic_name, user_id) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.setString(6, student.getPicName());
            preparedStatement.setInt(7, student.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        return getStudentsList(sql);
    }


    public void deleteStudentById(int id) {
        String sql = "DELETE  FROM student WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(LESSON_MANAGER.getLessonById(resultSet.getInt("lesson_id")))
                        .picName(resultSet.getString("pic_name"))
                        .user(USER_MANAGER.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(Student student) {
        String sql = "UPDATE student SET  name = ?, surname = ?, email = ?,age = ?, lesson_id = ?  WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.setInt(6, student.getUser().getId());
            preparedStatement.setInt(7, student.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getStudentsByUserId(int userId) {
        String sql = "SELECT * FROM student WHERE user_id=" + userId;
        return getStudentsList(sql);
    }

    private List<Student> getStudentsList(String sql) {
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(LESSON_MANAGER.getLessonById(resultSet.getInt("lesson_id")))
                        .picName(resultSet.getString("pic_name"))
                        .user(USER_MANAGER.getUserById(resultSet.getInt("user_id")))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public Student getStudentByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(LESSON_MANAGER.getLessonById(resultSet.getInt("lesson_id")))
                        .picName(resultSet.getString("pic_name"))
                        .user(USER_MANAGER.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
