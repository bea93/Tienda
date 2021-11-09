package curso.spring.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.spring.tienda.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

}
