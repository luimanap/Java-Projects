package Tienda;

import java.util.ArrayList;

import Usuarios.Carta;
import Usuarios.Cliente;

public class FacturaCompraVenta extends Factura{
	private Cliente cliente;
	//private Vendedor vendedor;
	private ArrayList<Carta> carrito;
	public FacturaCompraVenta(String idFactura, double cantidad, String concepto, Cliente cliente,
			ArrayList<Carta> carrito) {
		super(idFactura, cantidad, concepto);
		this.cliente = cliente;
		this.carrito = carrito;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ArrayList<Carta> getCarrito() {
		return carrito;
	}
	public void setCarrito(ArrayList<Carta> carrito) {
		this.carrito = carrito;
	}
	@Override
	public void mostrarFactura() {
		// TODO Auto-generated method stub
		super.mostrarFactura();
		System.out.println("CLIENTE: "+this.cliente);
		for (Carta carta : carrito) {
			carta.mostrarCarta();
		}
	}
	@Override
	public String toString() {
		return super.toString()+"FacturaCompraVenta [cliente=" + cliente + ", carrito=" + carrito + "]";
	}
	
}
