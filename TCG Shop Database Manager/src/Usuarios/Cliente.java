package Usuarios;

import java.util.ArrayList;

public class Cliente extends Usuario{
	private ClienteData data = new ClienteData();
	public Cliente(String id, String nombre, double saldo, ArrayList<Carta> coleccion) {
		super(id, nombre);
		this.data.saldo = saldo;
		this.data.coleccion = coleccion;
	}
	public Cliente(String id, String nombre, ArrayList<Carta> coleccion) {
		super(id, nombre);
		this.data.coleccion = coleccion;
		this.data.saldo = 99999;
	}
	public Cliente(String id, String nombre) {
		super(id, nombre);
		this.data.saldo = 99999;
		this.data.coleccion = new ArrayList<Carta>();
	}
	public Cliente(String dni, String nombre, double saldo) {
		super(dni, nombre);
		this.data.saldo = saldo;
		this.data.coleccion = new ArrayList<Carta>();
	}
	public double getSaldo() {
		return data.saldo;
	}
	public void setSaldo(double saldo) {
		this.data.saldo = saldo;
	}
	public ArrayList<Carta> getColeccion() {
		return data.coleccion;
	}
	public void setColeccion(ArrayList<Carta> coleccion) {
		this.data.coleccion = coleccion;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public void mostrarUsuario() {
		// TODO Auto-generated method stub
		super.mostrarUsuario();
		System.out.println("SALDO: "+ this.data.saldo);
		System.out.println("COLECCION:");
		mostrarColeccion();
	}
	@Override
	public String toString() {
		return super.toString()+ "Cliente [saldo=" + data.saldo + ", coleccion=" + data.coleccion + "]";
	}
	
	public void mostrarColeccion() {
		for (Carta carta : data.coleccion) {
			carta.mostrarCarta();
		}
	}
	
	public double aumentarSaldo(double saldo) {
		this.data.saldo = this.data.saldo + saldo;
		return saldo;
	}
	public double disminuirSaldo(double saldo) {
		if(this.data.saldo < saldo) {
			System.out.println("NO HAY SALDO SUFICIENTE");
		}
		else {
			this.data.saldo = this.data.saldo - saldo;
			System.out.println("SALDO RETIRADO CORRECTAMENTE");
		}
		return saldo;
	}
	
	
}
