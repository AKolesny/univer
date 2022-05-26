package org.example.univer.dao;

import org.example.univer.core.dto.GroupAndStudents;
import org.example.univer.dao.api.IGroupAndStudents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupAndStudentDao implements IGroupAndStudents<GroupAndStudents> {

    private static final String BD = "univer.groups_and_students";

    private static final String SELECT_ALL =
            "SELECT " +
                "group_name, " +
                "id_student " +
            "FROM " +
                BD + ";"
            ;

    private static final String INSERT =
            "INSERT INTO " +
                    BD + " (" +
                    "group_name, " +
                    "id_student " +
                    ") " +
                    "VALUES " +
                    "(?, ?);"
            ;

    private static final String DELETE =
            "DELETE FROM " +
                    BD + " " +
                    "WHERE " +
                    "group_name = ? " +
                    "AND " +
                    "id_student = ?;"
            ;

    public void create(GroupAndStudents item) {

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            for (long id : item.getStudentsId()) {
                preparedStatement.setObject(1, item.getNameGroup());
                preparedStatement.setObject(2, id);

                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании GroupAndStudents", e);
        }

    }

    public List<GroupAndStudents> get() {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при get методе", e);
        }
    }

    public void delete(GroupAndStudents item) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            for (Long id : item.getStudentsId()) {
                preparedStatement.setObject(1, item.getNameGroup());
                preparedStatement.setObject(2, id);

                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при Удалении GroupAndStudents", e);
        }
    }

    private List<GroupAndStudents> map(ResultSet resultSet) throws SQLException {
        List<GroupAndStudents> result = new ArrayList<>();
        GroupAndStudents gs = new GroupAndStudents();

        while (resultSet.next()) {
            String groupName = resultSet.getString("group_name");

            if (gs.getNameGroup() != null && !groupName.equals(gs.getNameGroup())) {
                result.add(gs);

                gs = new GroupAndStudents();
            }

            gs.setNameGroup(resultSet.getString("group_name"));
            gs.addId(resultSet.getLong("id_student"));
        }

        if (gs.getNameGroup() != null) {
            result.add(gs);
        }

        return result;
    }
}
