package org.example.univer.dao;

import org.example.univer.core.dto.Group;
import org.example.univer.core.dto.Student;
import org.example.univer.dao.api.IGroupDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IGroupDao {

    private static final String BD = "univer.groups";

    private static final String SELECT_ALL =
            "SELECT " +
                "id, " +
                "name " +
            "FROM " +
                BD + ";"
            ;

    private static final String INSERT =
            "INSERT INTO " +
                BD + " (" +
                "name" +
                ") " +
            "VALUES " +
                "(?);"
            ;

    private static final String UPDATE =
            "UPDATE " +
                BD + " " +
            "SET " +
                "name = ? " +
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
    public void create(Group item) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setObject(1, item.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании Group", e);
        }
    }

    @Override
    public List<Group> get() {
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
    public Group update(Long aLong, Group item) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(aLong);

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            int index = 0;
            for (Object param : params) {
                preparedStatement.setObject(++index, param);
            }
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении Group", e);
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
            throw new RuntimeException("Ошибка при Удалении Group", e);
        }
    }

    private List<Group> map(ResultSet resultSet) throws SQLException {
        List<Group> groups = new ArrayList<>();

        while (resultSet.next()) {
            Group group = new Group();
            group.setId(resultSet.getLong("id"));
            group.setName(resultSet.getString("name"));
            groups.add(group);
        }

        return groups;
    }
}
