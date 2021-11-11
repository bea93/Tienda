package curso.spring.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
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
		Base64 base64 = new Base64();
		String password = "admin";
		String passwordEncriptada = new String(base64.encode(password.getBytes()));
		User u = new User(1, "admin", passwordEncriptada, "admin@tiendaonline.es", 1);
		ur.save(u);
		String pass = "1234";
		String passEncriptada = new String(base64.encode(pass.getBytes()));
		u = new User(2, "pepe", passEncriptada, "pepe@tiendaonline.es", 2);
		ur.save(u);
		String pass2 = "paso";
		String passEncriptada2 = new String(base64.encode(pass2.getBytes()));
		u = new User(3, "bea", passEncriptada2, "bea@bea.es", 3);
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
		Base64 base64 = new Base64();
		List<User> lista = ur.buscarUserLogin(user.getName(), new String(base64.encode(user.getPassword().getBytes())));
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
