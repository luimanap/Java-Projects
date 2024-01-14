package Usuarios;

import java.util.Objects;

public class Usuario {
	 private String dni;
	 private String nombre;
	public Usuario(String id, String nombre) {
		this.dni = id;
		this.nombre = nombre;
	}
	public String getId() {
		return dni;
	}
	public void setId(String id) {
		this.dni = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dni, other.dni);
	}
	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", nombre=" + nombre + "]";
	}
	public void mostrarUsuario() {
		System.out.println("DNI: "+this.dni);
		System.out.println("NOMBRE: "+this.nombre);
	}
}
