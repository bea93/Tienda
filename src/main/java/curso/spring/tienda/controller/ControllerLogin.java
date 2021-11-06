package curso.spring.tienda.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.tienda.model.User;

@Controller
@RequestMapping("/user")
public class ControllerLogin {
	
	private ArrayList<User> usersList = usersList();
	
	// Para ir al formulario
		@GetMapping("/form")
		public String formulario(Model model) {
			model.addAttribute("user", new User());
			return "login";
		}

		// Para recoger los datos del formulario
		@PostMapping("/login")
		public String logarse(Model model,@ModelAttribute User user) {		
			if(comprobarLogin(user)) {
				model.addAttribute("name", user.getName());
				model.addAttribute("mensaje", "Bienvenid@, " + user.getName());
				return "index"; 
			}else {
				model.addAttribute("mensaje", "Login incorrecto");
				return "login";
			}
		}
		
		public boolean comprobarLogin(User user) {
			boolean result = false;
			for(int i = 0; i < usersList.size(); i++) {
				User u = usersList.get(i);
				if (u.getName().equals(user.getName())  && u.getPassword().equals(user.getPassword())) {
					result = true;
				}
			}
			return result;
		}
		
		public ArrayList<User> usersList() {
			ArrayList<User> lista = new ArrayList<User>();
			User u = new User("bea", "1234");
			lista.add(u);
			u = new User("admin", "admin");
			lista.add(u);
			return lista;
		}
}
