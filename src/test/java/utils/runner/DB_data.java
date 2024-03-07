package utils.runner;

import utils.reports.LoggerUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB_data {

    private final Connection conn = connection_to_db();
    public final String user = get_value("username");
    public final String password = get_value("password");

    private Connection connection_to_db() {

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" +
                    ProjectProperties.db_name, ProjectProperties.db_user, ProjectProperties.db_pass);

            if (conn != null) {
                LoggerUtils.logInfo("Connection Established");
            } else {
                LoggerUtils.logFatal("Connection failed");
            }

        } catch (Exception e) {
            LoggerUtils.logFatal(e.getMessage());
        }
        return conn;
    }

    private String get_value(String name) {

        Statement statement;
        ResultSet rs;

        try {
            String query = String.format("select * from %s where name = '%s'", ProjectProperties.db_table_name, name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            String value = "";
            while (rs.next()) {
                value = rs.getString("value");
            }

            if (!value.isEmpty()) {
                LoggerUtils.logInfo(name + " selected.");
                return value;
            }

            LoggerUtils.logFatal("Could not get value of " + name);

        } catch (Exception e) {
            LoggerUtils.logFatal(e.getMessage());
        }

        return null;
    }
}
