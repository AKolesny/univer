package org.example.univer.dao;

import org.example.univer.core.dto.Student;
import org.example.univer.dao.api.IStudentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IStudentDao {

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

    @Override
    public void create(Student item) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(item.getAge());
        params.add(item.getScore());
        params.add(item.isOlympicGamer());

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            int index = 0;
            for (Object param : params) {
                preparedStatement.setObject(++index, param);
            }
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании Student", e);
        }
    }

    @Override
    public List<Student> get() {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при get методе", e);
        }
    }

    @Override
    public Student update(Long aLong, Student item) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(item.getAge());
        params.add(item.getScore());
        params.add(item.isOlympicGamer());
        params.add(aLong);

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
        return null;
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

    private List<Student> map(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();

        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setScore(resultSet.getDouble("score"));
            student.setOlympicGamer(resultSet.getBoolean("olympic_gamer"));
            students.add(student);
        }

        return students;
    }
}
