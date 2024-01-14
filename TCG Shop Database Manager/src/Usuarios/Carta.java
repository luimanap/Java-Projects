package Usuarios;

import java.util.Objects;

public class Carta {
	private String nombre;
	private int stock;
	private double precio;
	
	public Carta(String nombre, int stock, double precio) {
	
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
	}
	
	public Carta(String nombre,int stock) { //constructor para las facturas
		this.nombre = nombre;
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}


	

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", stock=" + stock + ", precio=" + precio
				+ "]";
	}
	
	public void mostrarCarta() {
		System.out.println("NOMBRE: "+ this.nombre);
		System.out.println("STOCK:"+ this.stock);
		System.out.println("PRECIO:" + this.precio);
		System.out.println("-------------------------");
		
	}
}