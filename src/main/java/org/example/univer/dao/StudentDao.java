package org.example.univer.dao;

import org.example.univer.core.dto.Student;
import org.example.univer.dao.api.IStudentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IStudentDao {
    private static final StudentDao instance = new StudentDao();
    private static final String BD = "univer.students";

    private static final String SELECT_ALL =
            "SELECT " +
                "id, " +
                "name, " +
                "age, " +
                "score, " +
                "olympic_gamer " +
            "FROM " +
                BD + ";"
            ;

    private static final String SELECT_STUDENT =
            "SELECT " +
                "id, " +
                "name, " +
                "age, " +
                "score, " +
                "olympic_gamer " +
            "FROM " +
                BD + " " +
            "WHERE " +
                "id = ?;"
            ;

    private static final String INSERT =
            "INSERT INTO " +
                BD + " (" +
                "name, " +
                "age, " +
                "score, " +
                "olympic_gamer " +
                ") " +
            "VALUES " +
                "(?, ?, ?, ?);"
            ;

    private static final String UPDATE =
            "UPDATE " +
                BD + " " +
            "SET " +
                "name = ?, " +
                "age = ?, " +
                "score = ?, " +
                "olympic_gamer = ? " +
                "WHERE " +
                "id = ?;"
            ;

    private static final String DELETE =
            "DELETE FROM " +
                BD + " " +
            "WHERE " +
                "id = ?;"
            ;

    private StudentDao() {
    }

    @Override
    public long create(Student item) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(item.getAge());
        params.add(item.getScore());
        params.add(item.isOlympicGamer());

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            int index = 0;
            for (Object param : params) {
                preparedStatement.setObject(++index, param);
            }
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new RuntimeException("Студент не вставился");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании Student", e);
        }
    }

    @Override
    public List<Student> getAll() {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            List<Student> students = new ArrayList<>();
            Student student;

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    student = map(resultSet);
                    students.add(student);
                }
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при getAll методе", e);
        }
    }

    @Override
    public Student get(long id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT)) {

            preparedStatement.setObject(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return map(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при get методе", e);
        }
    }

    @Override
    public void update(Student item) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(item.getAge());
        params.add(item.getScore());
        params.add(item.isOlympicGamer());
        params.add(item.getId());

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            int index = 0;
            for (Object param : params) {
                preparedStatement.setObject(++index, param);
            }
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении Student", e);
        }
    }

    @Override
    public void delete(Long aLong) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setObject(1, aLong);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при Удалении Student", e);
        }
    }

    private Student map(ResultSet resultSet) throws SQLException {
            Student student = new Student();

            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setScore(resultSet.getDouble("score"));
            student.setOlympicGamer(resultSet.getBoolean("olympic_gamer"));

        return student;
    }

    public static StudentDao getInstance() {
        return instance;
    }
}
