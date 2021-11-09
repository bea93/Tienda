package curso.spring.tienda.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import curso.spring.tienda.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	Product findByName(String name);
}
