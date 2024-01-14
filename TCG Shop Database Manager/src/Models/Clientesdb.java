package Models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Usuarios.Cliente;

public class Clientesdb {

    //------------------------------------------------------------
    public static boolean insertarCliente(Cliente a) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "INSERT INTO clientes (dni, nombre, saldo) VALUES (?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setString(1, a.getId());
            sentencia.setString(2, a.getNombre());
            sentencia.setDouble(3, a.getSaldo());
              

            int filasafectadas = sentencia.executeUpdate();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //------------------------------------------------------------
    public static ArrayList<Cliente> obtenerClientes() {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM clientes ORDER BY dni";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                String dni = resultado.getString("dni");
                String nombre = resultado.getString("nombre");
                double saldo  = resultado.getDouble("saldo");
                Cliente c = new Cliente(dni,nombre,saldo);              
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
    
    //------------------------------------------------------------    
    public static boolean borrarCliente(Cliente a) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "DELETE FROM clientes WHERE dni=?";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setString(1, a.getId());
            int filasafectadas = sentencia.executeUpdate();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //------------------------------------------------------------
    public static boolean editarCliente(Cliente a) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordenSQL = "UPDATE clientes SET dni=?, nombre=?, saldo=? WHERE `dni` = ?";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, a.getId());
            sentencia.setString(2, a.getNombre());
            sentencia.setDouble(3, a.getSaldo());
            sentencia.setString(4, a.getId());
            int filasafectadas = sentencia.executeUpdate();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //------------------------------------------------------------
    public static ArrayList<Cliente> buscarPorNombre(String clientebuscado) 
    {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            String ordensql = "SELECT * FROM clientes WHERE nombre LIKE ?";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            String textobuscado = "%"+ clientebuscado + "%";
            sentencia.setString(1, textobuscado);
            ResultSet resultado  = sentencia.executeQuery();    
            while (resultado.next()) {
            	String dni = resultado.getString("dni");
                String nombre = resultado.getString("nombre");
                double saldo  = resultado.getDouble("saldo");
                Cliente c = new Cliente(dni,nombre,saldo);              
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
    //------------------------------------------------------------   
}
