package Tienda;

public class FacturaLuz extends Factura{
	private double kw;
	private boolean pagado;
	public FacturaLuz(String idFactura, double cantidad, String concepto, double kw, boolean pagado) {
		super(idFactura, cantidad, concepto);
		this.kw = kw;
		this.pagado = pagado;
	}
	public FacturaLuz(String idFactura, double cantidad, String concepto, double kw) {
		super(idFactura, cantidad, concepto);
		this.kw = kw;
		this.pagado = false;
	}
	public double getKw() {
		return kw;
	}
	public void setKw(double kw) {
		this.kw = kw;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	@Override
	public void mostrarFactura() {
		// TODO Auto-generated method stub
		super.mostrarFactura();
		System.out.println("KW: "+ this.kw);
		if(pagado) {
			System.out.println("Factura pagada");
		}
		else {
			System.out.println("Factura sin pagar");
		}
	}
	@Override
	public String toString() {
		return super.toString()+"FacturaLuz [kw=" + kw + ", pagado=" + pagado + "]";
	}
	
	
	
	
}
