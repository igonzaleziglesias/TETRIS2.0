package baseDatos;

import excepciones.ExcepcionSql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

    private Connection connect() {
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

    public void deleteAll() throws ExcepcionSql {
        String sql = "DELETE FROM puntuacion";
        int exception;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

  
            pstmt.executeUpdate();
            exception=0;
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            exception=1;
        }
                if (exception==1){
            throw new ExcepcionSql("Fallo el borrado");
        }
    }

}
