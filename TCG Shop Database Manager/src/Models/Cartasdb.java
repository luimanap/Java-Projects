package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Usuarios.Carta;

public class Cartasdb {

	// ------------------------------------------------------------
	public static boolean insertarCarta(Carta a) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return false;
		}
		try {
			
			String ordensql = "INSERT INTO cartas (nombre, stock, precio) VALUES (?,?,?);";
			PreparedStatement sentencia = conexion.prepareStatement(ordensql);
			sentencia.setString(1, a.getNombre());
			sentencia.setInt(2, a.getStock());
			sentencia.setDouble(3, a.getPrecio());

			int filasafectadas = sentencia.executeUpdate();
			if (filasafectadas > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ------------------------------------------------------------
	public static ArrayList<Carta> obtenerCartas() {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return null;
		}
		ArrayList<Carta> listaCartas = new ArrayList<Carta>();
		try {
			Statement sentencia = conexion.createStatement();
			String ordenSQL = "SELECT * FROM cartas ORDER BY nombre";
			ResultSet resultado = sentencia.executeQuery(ordenSQL);
			while (resultado.next()) {
				String nombre = resultado.getString("nombre");
				int stock = resultado.getInt("stock");
				double precio = resultado.getDouble("precio");
				Carta c = new Carta(nombre, stock, precio);
				listaCartas.add(c);
			}
			resultado.close();
			sentencia.close();
			conexion.close();
			return listaCartas;
		} catch (SQLException e) {
			e.printStackTrace();
			return listaCartas;
		}
	}

	// ------------------------------------------------------------
	public static boolean borrarCarta(Carta a) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return false;
		}
		try {
			String ordensql = "DELETE FROM cartas WHERE nombre LIKE ?";
			PreparedStatement sentencia = conexion.prepareStatement(ordensql);
			sentencia.setString(1, a.getNombre());
			int filasafectadas = sentencia.executeUpdate();
			if (filasafectadas > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ------------------------------------------------------------
	public static boolean editarCarta(Carta a) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return false;
		}
		try {
			String ordenSQL = "UPDATE cartas SET nombre=?, stock=?, precio=? WHERE nombre LIKE ?";
			PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
			sentencia.setString(1, a.getNombre());
			sentencia.setInt(2, a.getStock());
			sentencia.setDouble(3, a.getPrecio());
			sentencia.setString(4, a.getNombre());
			int filasafectadas = sentencia.executeUpdate(); // error aqui preguntar duda
			if (filasafectadas > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ------------------------------------------------------------
	public static ArrayList<Carta> buscarPorNombre(String carta) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return null;
		}
		ArrayList<Carta> listaCartas = new ArrayList<Carta>();
		try {
			String ordensql = "SELECT * FROM cartas WHERE nombre LIKE ?";
			PreparedStatement sentencia = conexion.prepareStatement(ordensql);
			String textobuscado = "%" + carta + "%";
			sentencia.setString(1, textobuscado);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				String nombre = resultado.getString("nombre");
				int stock = resultado.getInt("stock");
				double precio = resultado.getDouble("precio");
				Carta a = new Carta(nombre, stock, precio);
				listaCartas.add(a);
			}
			resultado.close();
			sentencia.close();
			conexion.close();
			return listaCartas;
		} catch (SQLException e) {
			e.printStackTrace();
			return listaCartas;
		}
	}
	// ------------------------------------------------------------
	
}
