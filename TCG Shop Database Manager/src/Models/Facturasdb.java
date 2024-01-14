package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Tienda.Factura;
import Usuarios.Cliente;

public class Facturasdb {

	// ------------------------------------------------------------
	public static boolean insertarFactura(Factura f) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return false;
		}
		try {
			String ordensql = "INSERT INTO facturas (idFactura, concepto, importe, tipo) VALUES (?,?,?,?);";
			PreparedStatement sentencia = conexion.prepareStatement(ordensql);
			sentencia.setString(1, f.getIdFactura());
			sentencia.setString(2, f.getConcepto());
			sentencia.setDouble(3, f.getImporte());
			sentencia.setString(4, f.getTipo());

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
	public static ArrayList<Factura> obtenerFacturas() {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return null;
		}
		ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
		try {
			Statement sentencia = conexion.createStatement();
			String ordenSQL = "SELECT * FROM facturas ORDER BY idFactura";
			ResultSet resultado = sentencia.executeQuery(ordenSQL);
			while (resultado.next()) {
				String id = resultado.getString("idFactura");
				double importe = resultado.getDouble("importe");
				String concepto = resultado.getString("concepto");
				String tipo = resultado.getString("tipo");
				Factura c = new Factura(id, concepto, importe, tipo);
				listaFacturas.add(c);
			}
			resultado.close();
			sentencia.close();
			conexion.close();
			return listaFacturas;
		} catch (SQLException e) {
			e.printStackTrace();
			return listaFacturas;
		}
	}

	// ------------------------------------------------------------
	public static boolean borrarFactura(Factura a) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return false;
		}
		try {
			String ordensql = "DELETE FROM facturas WHERE idFactura=?";
			PreparedStatement sentencia = conexion.prepareStatement(ordensql);
			sentencia.setString(1, a.getIdFactura());
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
	public static boolean editarFactura(Factura a) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return false;
		}
		try {
			String ordenSQL = "UPDATE facturas SET idFactura=?, concepto=?, importe=?, tipo=? WHERE `idFactura` like ?";
			PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
			sentencia.setString(1, a.getIdFactura());
			sentencia.setString(2, a.getConcepto());
			sentencia.setDouble(3, a.getImporte());
			sentencia.setString(4, a.getTipo());
			sentencia.setString(5, a.getIdFactura());
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
	public static ArrayList<Cliente> buscarPorNombre(String clientebuscado) {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return null;
		}
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		try {
			String ordensql = "SELECT * FROM articulos WHERE descripcion LIKE ?";
			PreparedStatement sentencia = conexion.prepareStatement(ordensql);
			String textobuscado = "%" + clientebuscado + "%";
			sentencia.setString(1, textobuscado);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				String dni = resultado.getString("dni");
				String nombre = resultado.getString("nombre");
				double saldo = resultado.getDouble("saldo");
				Cliente c = new Cliente(dni, nombre, saldo);
				listaClientes.add(c);
			}
			resultado.close();
			sentencia.close();
			conexion.close();
			return listaClientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return listaClientes;
		}
	}
	// ------------------------------------------------------------
}
