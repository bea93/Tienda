package curso.spring.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.spring.tienda.model.OrderDetails;
import curso.spring.tienda.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {
	@Autowired
	private OrderDetailsRepository odr;
	
	@PostConstruct
	public void cargarOrderDetails() {
		OrderDetails od = new OrderDetails(1, 1, "Cerveza", 1.0, 6);
		odr.save(od);
		od = new OrderDetails(2, 2, "Macarrones", 1.8, 1);
		odr.save(od);
		od = new OrderDetails(3, 4, "Chocolate", 3.0, 2);
		odr.save(od);
	}
	
	
	public List<OrderDetails> getListaOrderDetails() {
		return odr.findAll();
	}
	
	public void addOrderDetails(OrderDetails orderDetails) {
		odr.save(orderDetails);
	}
	
	public void delOrderDetails(int id) {
		OrderDetails od = odr.getById(id);
		odr.delete(od);
	}
	
	public void editOrderDetails(OrderDetails od) {
		odr.save(od);
		
	}
	
	public OrderDetails getOrderDetailsXId(int id) {
		OrderDetails od = odr.getById(id);
		return od;
	}
}
