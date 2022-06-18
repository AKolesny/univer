package org.example.univer.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final DataSource dataSource;

    static {
        ComboPooledDataSource pool = new ComboPooledDataSource();
        try {
            pool.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Проверь имя драйвера!", e);
        }

        pool.setJdbcUrl("jdbc:postgresql://localhost:5432/json");
        pool.setUser("postgres");
        pool.setPassword("postgres");

        dataSource = pool;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() throws SQLException {
        DataSources.destroy(dataSource);
    }
}
