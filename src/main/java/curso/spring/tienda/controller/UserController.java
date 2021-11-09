package curso.spring.tienda.controller;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.tienda.model.User;
import curso.spring.tienda.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService us;

	// Muestra el formulario del login
	@GetMapping("/form")
	public String formulario(Model model) {
		model.addAttribute("user", new User());
		return "user/login";
	}

	// Recoge los datos del formulario del login
	@PostMapping("/login")
	public String logarse(Model model, @ModelAttribute User user, HttpSession session) {
		if (us.comprobarLogin(user)) {
			User u = us.getUserByName(user.getName());
			model.addAttribute("name", u.getName());
			session.setAttribute("mensaje", "Bienvenid@, " + u.getName());
			session.setAttribute("rol", u.getId_rol());
			return "redirect:/";
		} else {
			model.addAttribute("mensaje", "Login incorrecto");
			return "user/login";
		}
	}

	// Muestra el formulario de registro
	@GetMapping("/new")
	public String registro(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}

	// Recoge los datos del formulario de registro
	@PostMapping("/new")
	public String registrarse(Model model, @ModelAttribute User user, HttpSession session) {
		if(user.getPassword() == user.getPassword()) {
			Base64 base64 = new Base64();
			user.setId_rol(3);
			String passEncriptada = new String(base64.encode(user.getPassword().getBytes()));;
			user.setPassword(passEncriptada);
			us.addUser(user);
			session.setAttribute("rol", user.getId_rol());
			session.setAttribute("mensaje", "Usuario creado. Bienvenid@, " + user.getName());
			return "redirect:/";
		}else {
			model.addAttribute("mensaje", "Las contraseñas no coinciden");
		}
		return "user/register";
	}

	// Muestra el formulario de registro
	@GetMapping("/update/{id}")
	public String edicion(@PathVariable int id, Model model) {
		User u = us.getUserXId(id);
		model.addAttribute("user", u);
		return "user/edit";
	}

	// Recoge los datos del formulario de registro
	@PostMapping("/update/submit")
	public String modificar(@ModelAttribute User user, HttpSession session) {
		us.editUser(user);
		session.setAttribute("mensaje", "Usuario actualizado.");
		return "redirect:/";

	}

	//Para borrar usuario
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable int id) {
		us.delUser(id);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
