package baseDatos;

import excepciones.ExcepcionSql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Insert {

    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:puntuacion.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(int id, String name, String puntos) throws ExcepcionSql {
        String sql = "INSERT INTO puntuacion(name,puntos,id) VALUES(?,?,?)";
        int exception;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, puntos);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            exception = 0;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            exception = 1;
        }
        if (exception == 1) {
            throw new ExcepcionSql("Fallo la inserción");
        }

    }

}
