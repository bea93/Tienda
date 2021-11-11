package curso.spring.tienda.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Order {

	@Id @GeneratedValue
	private int id;
	private int idUsuario;
	private String fecha;
	private String metodoPago;
	private String estado;
	private String numFactura;
	private double total;
	
	public Order() {
		super();
	}
	
	public Order(int id, int idUsuario, String fecha, String metodoPago, String estado, String numFactura,
			double total) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.numFactura = numFactura;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public static Double calcularTotalDetalles(ArrayList<OrderDetails> cart) {
		Double total = 0d;
		
		for(OrderDetails od : cart)
			total += od.getTotal();
		
		return total;
	}
	
}
