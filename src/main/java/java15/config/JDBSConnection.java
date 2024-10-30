package java15.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBSConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "1234";

    public static Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            return con;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
