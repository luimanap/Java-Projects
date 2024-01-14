package Models;

import java.sql.Connection;

public class PruebaConexion {

	public static void main(String[] args) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		
		if(conexion!=null) {
			System.out.println("Conexion OK");
		}
		else {
			System.out.println("No se pudo establecer la conexion");
		}

	}

}
