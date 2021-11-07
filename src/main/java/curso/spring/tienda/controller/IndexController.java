package curso.spring.tienda.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.tienda.model.Cathalog;
import curso.spring.tienda.model.Product;

@Controller
@RequestMapping("")
public class ControllerIndex {
	private static ArrayList<Product> catalogo = Cathalog.cargarCatalogo();
	
	@GetMapping("")
	public String cathalog(Model model) {
		model.addAttribute("list", catalogo);
		return "index";
	}
	
}
