package curso.spring.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.spring.tienda.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByName(String name);

	@Query(value = "select * from User where name=?1 and password=?2", nativeQuery = true)
	List<User> buscarUserLogin(String name, String password);
}
