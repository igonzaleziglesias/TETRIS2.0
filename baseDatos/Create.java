package baseDatos;

import excepciones.excepcionSql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create {

    public static void createTable() throws excepcionSql {

        String url = "jdbc:sqlite:puntuacion.db";
        int exception;
        String sql = "CREATE TABLE IF NOT EXISTS puntuacion ("
                + "	name text NOT NULL,"
                + "	puntos text NOT NULL,"
                + "	id integer PRIMARY KEY"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            exception = 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            exception = 1;
        }
        if (exception == 1) {
            throw new excepcionSql("Fallo la creación de la tabla");
        }
    }

}
