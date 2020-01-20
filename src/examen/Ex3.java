package examen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex3 {

	private static String nombre;
	private static String apellido1;
	private static String apellido2;
	private static Scanner sc;

	public static void main(String[] args) {

		preparaQuery();

		ejecutaQuery();

		sc.close();
	}

	static void preparaQuery() { // Metodo para recoger informacion
		sc = new Scanner(System.in);

		System.out.println("Bienvenido a la consulta del doctor Hibbert \rIntroduzca el nombre del paciente: ");
		nombre = sc.next();

		System.out.println("Introduce el primer apellido:");
		apellido1 = sc.next();

		System.out.println("Introduce el segundo apellido:");
		apellido2 = sc.next();

	}

	static void ejecutaQuery() { // Metodo que ejecuta y printa la query
		Connection c;

		try {
			c = new Conexion().getConexion();

			String queryId = "select idSeguro from seguro " + "" + "where LOWER(nombre) like '" + nombre.toLowerCase()
					+ "' " + "AND LOWER(ape1) like '" + apellido1.toLowerCase() + "' " + "AND LOWER(ape2) like '"
					+ apellido2.toLowerCase() + "'";

			// DEJO LA QUERY AQUI QUE SE VE MAS VISUAL
//			 "select idSeguro from seguro " + ""
//				+ "where LOWER(nombre) like '" + nombre.toLowerCase() + "' "
//				+ "AND LOWER(ape1) like '" + apellido1.toLowerCase() + "' " 
//				+ "AND LOWER(ape2) like '"+ apellido2.toLowerCase() + "'";

			ResultSet rs = c.prepareStatement(
					"select count(fecha), SUM(importe) from asistenciamedica where IdSeguro = (" + queryId + ")")
					.executeQuery();

			if (rs.next() && rs.getInt(1) != 0) {
				System.out.println("---------------- FICHA PACIENTE ----------------");
				System.out.println("Nombre del asegurado: " + nombre.toUpperCase() + " " + apellido1.toUpperCase() + " "
						+ apellido2.toUpperCase());
				System.out.println("Total asistencia medica: " + rs.getInt(1));
				System.out.println("Importe: " + rs.getFloat(2));
			} else
				System.out.println("Este paciente no figura en nuestra bbdd, por lo que esta sano como un gusano");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al crear la conexion: " + e.getMessage());
		}

	}

}
