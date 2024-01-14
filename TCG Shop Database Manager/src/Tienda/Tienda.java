package Tienda;

import java.util.ArrayList;

import Usuarios.Cliente;

public class Tienda {
	private String idTienda;
	private String nombreTienda;
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	//private ArrayList<Vendedor> vendedores;
	
	public Tienda(String idTienda, String nombreTienda, ArrayList<Factura> facturas,
			ArrayList<Cliente> clientes) {
		super();
		this.idTienda = idTienda;
		this.nombreTienda = nombreTienda;
		this.facturas = facturas;
		this.clientes = clientes;
		
	}
	public String getIdTienda() {
		return idTienda;
	}
	public void setIdTienda(String idTienda) {
		this.idTienda = idTienda;
	}
	public String getNombreTienda() {
		return nombreTienda;
	}
	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> factura) {
		this.facturas = factura;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	/*public ArrayList<Vendedor> getVendedores() {
		return vendedores;
	}
	public void setVendedores(ArrayList<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}*/
	public void obtenerFacturasCompraVenta(){
		for (Factura factura : facturas) {
			if(factura instanceof FacturaCompraVenta) {
				factura.mostrarFactura();
			}
		}
	}
	public void mostrarClientes() {
		for (Cliente cliente : clientes) {
			cliente.mostrarUsuario();
		}
	}
	/*public void mostrarVendedores() {
		for (Vendedor vendedor : vendedores) {
			vendedor.mostrarUsuario();
		}
	}*/
	
	
}
