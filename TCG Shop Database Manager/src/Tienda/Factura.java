package Tienda;

import java.util.Objects;

public class Factura {
	private String idFactura;
	private double importe;
	private String concepto;
	private String tipo;
	public Factura(String idFactura, double cantidad, String concepto) {
		this.idFactura = idFactura;
		this.importe = cantidad;
		this.concepto = concepto;
	}
	public Factura(String idFactura, double cantidad) {
		this.idFactura = idFactura;
		this.importe = cantidad;
		this.concepto = "sin concepto";
	}
	public Factura(String id, String concepto, double importe, String tipo) {
		this.idFactura = id;
		this.concepto = concepto;
		this.importe = importe;
		this.tipo = tipo;
	}
	public String getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double cantidad) {
		this.importe = cantidad;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idFactura);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(idFactura, other.idFactura);
	}
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", cantidad=" + importe + ", concepto=" + concepto + "]";
	}
	public void mostrarFactura() {
		System.out.println("--------------");
		System.out.println("ID: "+this.idFactura);
		System.out.println("CANTIDAD: "+this.importe+"€");
		System.out.println("CONCEPTO: "+this.concepto);
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
