package curso.spring.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.spring.tienda.model.User;
import curso.spring.tienda.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository ur;

	@PostConstruct
	public /* ArrayList<Usuario> */ void cargarUsuarios() {
		User u = new User(1, "admin", "admin", "admin@tiendaonline.es", 1);
		ur.save(u);
		u = new User(2, "pepe", "1234", "pepe@tiendaonline.es", 2);
		ur.save(u);
	}

	public List<User> getListaUsers() {
		return ur.findAll();
	}

	public void addUser(User usuario) {
		ur.save(usuario);
	}

	public void delUser(int id) {
		User u = ur.getById(id);
		ur.delete(u);
	}

	public void editUser(User u) {
		ur.save(u);

	}

	public boolean comprobarLogin(User user) {
		boolean result = false;
		List<User> lista = ur.buscarUserLogin(user.getName(), user.getPassword());
		if (!lista.isEmpty()) {
			result = true;
		}
		return result;
	}

	public User getUserXId(int id) {
		User u = ur.getById(id);
		return u;
	}

	public User getUserByName(String name) {
		User u = ur.findByName(name);
		return u;
	}

}
