package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private static Connection conn = null;

    public static boolean connect() {

        try {
            String url = "jdbc:sqlite:puntuacion.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static boolean disconnect() {
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
