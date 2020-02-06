package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConnectionUtil {
    public static  final ResourceBundle rs=ResourceBundle.getBundle("DatabaseInformation");
    private static final String USER_NAME = rs.getString("datasource.user");
    private static final String PASSWORD = rs.getString("datasource.password");
    private static final String URL = rs.getString("datasource.url");
    private static final String DRIVER_NAME = rs.getString("datasource.driver");

    public static Connection getConnetion() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static void disConnect(Connection connection) {
        try {
            if (connection!=null){

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
