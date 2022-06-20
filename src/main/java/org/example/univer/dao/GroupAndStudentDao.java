package org.example.univer.dao;

import org.example.univer.core.dto.*;
import org.example.univer.core.dto.groupAndStudentGet.GroupsStudentGet;
import org.example.univer.core.dto.groupAndStudentGet.ListGroupsAndStudents;
import org.example.univer.core.dto.groupAndStudentGet.ListStudentsGet;
import org.example.univer.core.dto.groupAndStudentGet.api.IGroupAndStudentGet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupAndStudentDao {

    private static final String BD = "univer.groups_students";

    private static final String SELECT_ALL =
            "SELECT \n" +
                "g.id AS group_id, \n" +
                "g.name AS group_name, \n" +
                "s.id AS student_id, \n" +
                "s.name AS student_name, \n" +
                "s.age AS student_age, \n" +
                "s.score AS student_score, \n" +
                "s.olympic_gamer AS student_olympic_gamer\n" +
            "FROM \n" +
                BD + " AS gs\n" +
            "JOIN \n" +
                "univer.groups AS g \n" +
            "ON \n" +
                "gs.group_id = g.id\n" +
            "JOIN \n" +
                "univer.students AS s \n" +
            "ON \n" +
                "gs.student_id = s.id\n" +
            "ORDER BY \n" +
                "g.id ASC, \n" +
                "s.id ASC;"
            ;

    private static final String SELECT_GET_GROUP =
            "SELECT \n" +
                "g.id AS group_id, \n" +
                "g.name AS group_name \n" +
            "FROM \n" +
                BD + " AS gs\n" +
            "JOIN \n" +
                "univer.groups AS g \n" +
            "ON \n" +
                "gs.group_id = g.id\n" +
            "WHERE \n" +
                "gs.student_id = ?;"
            ;

    private static final String SELECT_GET_STUDENTS =
            "SELECT \n" +
                "s.id AS student_id, \n" +
                "s.name AS student_name, \n" +
                "s.age AS student_age, \n" +
                "s.score AS student_score, \n" +
                "s.olympic_gamer AS student_olympic_gamer\n" +
            "FROM \n" +
                BD + " AS gs\n" +
            "JOIN \n" +
                "univer.students AS s \n" +
            "ON \n" +
                "gs.student_id = s.id\n" +
            "WHERE \n" +
                "gs.group_id = ? \n" +
            "ORDER BY \n" +
                "s.id ASC;"
            ;

    private static final String INSERT =
            "INSERT INTO " +
                    BD + " (" +
                    "group_id, " +
                    "student_id " +
                    ") " +
                    "VALUES " +
                    "(?, ?);"
            ;

    private static final String DELETE =
            "DELETE FROM " +
                    BD + " " +
                    "WHERE " +
                    "group_id = ? " +
                    "AND " +
                    "student_id = ?;"
            ;

    public void create(GroupAndStudents item) {

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            for (long id : item.getStudentsId()) {
                preparedStatement.setObject(1, item.getGroupId());
                preparedStatement.setObject(2, id);

                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании GroupAndStudents", e);
        }

    }

    public IGroupAndStudentGet getAll() {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                ListGroupsAndStudents listGs = new ListGroupsAndStudents();
                GroupsStudentGet gs = new GroupsStudentGet();
                Group group;

                while (resultSet.next()) {
                    group = mapGroup(resultSet);

                    if (gs.getGroup() != null && gs.getGroup().getId() != group.getId()) {
                        listGs.addGroupStudent(gs);

                        gs = new GroupsStudentGet();
                    }

                    gs.setGroup(group);
                    gs.addStudent(mapStudent(resultSet));
                }

                if (gs.getGroup() != null) {
                    listGs.addGroupStudent(gs);
                }

                return listGs;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при get методе", e);
        }
    }

    public IGroupAndStudentGet getGroup(long id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GET_GROUP)) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
               return mapGroup(resultSet);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при getGroup", e);
        }
    }

    public IGroupAndStudentGet getStudents(long id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GET_STUDENTS)) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            ListStudentsGet students = new ListStudentsGet();
            while (resultSet.next()) {
                students.addStudent(mapStudent(resultSet));
            }

            return students;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при getGroup", e);
        }
    }

    public void delete(GroupAndStudents item) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            for (Long id : item.getStudentsId()) {
                preparedStatement.setObject(1, item.getGroupId());
                preparedStatement.setObject(2, id);

                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при Удалении GroupAndStudents", e);
        }
    }

    private Group mapGroup(ResultSet resultSet) throws SQLException {
        Group group = new Group();

        group.setId(resultSet.getLong("group_id"));
        group.setName(resultSet.getString("group_name"));

        return group;
    }

    private Student mapStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        student.setId(resultSet.getLong("student_id"));
        student.setName(resultSet.getString("student_name"));
        student.setAge(resultSet.getInt("student_age"));
        student.setScore(resultSet.getDouble("student_score"));
        student.setOlympicGamer(resultSet.getBoolean("student_olympic_gamer"));

        return student;
    }
}
