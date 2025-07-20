package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static String jdbcURL = "jdbc:mysql://localhost:3306/pahanadb";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
