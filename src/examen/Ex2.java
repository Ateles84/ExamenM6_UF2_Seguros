package examen;

import java.sql.Connection;
import java.sql.SQLException;

public class Ex2 {

	public static void main(String[] args) {
		Connection c;

		try {
			c = new Conexion().getConexion();

			int i = c.prepareStatement(
					"update asistenciamedica set importe = 6523.34 where breveDescripcion like 'Dermatitis infecciosa'")
					.executeUpdate();

			if (i > 0)
				System.out.println(i > 1 ? "Se han modificado " + i + " registros" : "Se ha modificado un registro");
		
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al crear la conexion: " + e.getMessage());
		}

	}

}
