package curso.spring.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.tienda.model.User;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	
	@GetMapping("")
	public String formulario() {
		return "cart";
	}
}
