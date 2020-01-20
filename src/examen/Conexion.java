package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {	//Ex1
	//Credenciales y direccion del a bbdd
	private String USER = "root";
	private String PASS = "P@ssw0rd";
	private String URL = "jdbc:mysql://localhost/seguros?serverTimezone=UTC";	

	Connection getConexion() throws SQLException {	//Simple metodo para inicializar conexion
		return DriverManager.getConnection(URL, USER, PASS);
	}

}
