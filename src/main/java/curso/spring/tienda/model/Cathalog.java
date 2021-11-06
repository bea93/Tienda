package curso.spring.tienda.model;

import java.util.ArrayList;

public class Cathalog {
	private static ArrayList<Product> catalogo = cargarCatalogo();
	
	public static ArrayList<Product> cargarCatalogo(){
		ArrayList<Product> catalogo = new ArrayList<Product>();
		catalogo.add(new Product(1, "Cerveza", 1.0));
		catalogo.add(new Product(2, "Macarrones", 1.8));
		catalogo.add(new Product(3, "Queso", 2.5));
		catalogo.add(new Product(4, "Chocolate", 3.0));
		catalogo.add(new Product(5, "Galletas", 4.30));
		
		return catalogo;
	}
	
	public static ArrayList<Product> getCatalogo(){
		return catalogo;
	}
	
	public static Product getProductoById(Integer id) {
		for(int i = 0; i < catalogo.size(); i++) {
			Product producto = catalogo.get(i);
			if(producto.getId() == id) {
				return producto;
			}
		}
		
		return null;
	}
}
