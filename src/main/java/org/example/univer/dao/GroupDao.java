package org.example.univer.dao;

import org.example.univer.core.dto.Group;
import org.example.univer.dao.api.IGroupDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IGroupDao {
    private static final GroupDao instance = new GroupDao();
    private static final String BD = "univer.groups";

    private static final String SELECT_ALL =
            "SELECT " +
                "id, " +
                "name " +
            "FROM " +
                BD + ";"
            ;

    private static final String SELECT_GROUP =
            "SELECT " +
                "id, " +
                "name " +
            "FROM " +
                BD + " " +
            "WHERE " +
                    "id = ?;"
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

    private GroupDao() {
    }

    @Override
    public long create(Group item) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, item.getName());
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new RuntimeException("Группа не вставилась");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании Group", e);
        }
    }

    @Override
    public List<Group> getAll() {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            List<Group> groups = new ArrayList<>();
            Group group;

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                   group = map(resultSet);
                   groups.add(group);
                }
            }
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при getAll методе", e);
        }
    }

    @Override
    public Group get(long id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP)) {

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
    public void update(Group item) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(item.getId());

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

    private Group map(ResultSet resultSet) throws SQLException {
            Group group = new Group();

            group.setId(resultSet.getLong("id"));
            group.setName(resultSet.getString("name"));

        return group;
    }

    public static GroupDao getInstance() {
        return instance;
    }
}
