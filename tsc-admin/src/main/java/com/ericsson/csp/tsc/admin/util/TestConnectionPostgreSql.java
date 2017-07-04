package com.ericsson.csp.tsc.admin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConnectionPostgreSql {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestConnectionPostgreSql.class);

    private Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/geely";
            conn = DriverManager.getConnection(url, "wangsy", "123456");
            LOGGER.debug(conn.getCatalog());
        } catch (ClassNotFoundException e) {
            LOGGER.info("ClassNotFoundException", e);
        } catch (SQLException e) {
            LOGGER.info("SQLException : {}", e);
        }
        return conn;
    }

    public static void main(String[] args) {
        TestConnectionPostgreSql clazz = new TestConnectionPostgreSql();
        clazz.connect();
    }
}
