package curso.spring.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.spring.tienda.model.Product;
import curso.spring.tienda.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository pr;
	
	@PostConstruct
	public void cargarProductos() {
		Product p = new Product(1, "Cerveza", 1.0);
		pr.save(p);
		p = new Product(2, "Macarrones", 1.8);
		pr.save(p);
		p = new Product(3, "Queso", 2.5);
		pr.save(p);
		p = new Product(4, "Chocolate", 3.0);
		pr.save(p);
	}
	
	
	public List<Product> getListaProductos() {
		return pr.findAll();
	}
	
	public void addProduct(Product product) {
		pr.save(product);
	}
	
	public void delProduct(int id) {
		Product p = pr.getById(id);
		pr.delete(p);
	}
	
	public void editProduct(Product p) {
		pr.save(p);
		
	}
	
	public Product getProductXId(int id) {
		Product p = pr.getById(id);
		return p;
	}
}
