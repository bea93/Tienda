package curso.spring.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.tienda.service.ProductService;
import curso.spring.tienda.service.UserService;

@Controller
@RequestMapping("")
public class IndexController {
	
	@Autowired
	UserService us;
	@Autowired
	ProductService ps;
	
	@GetMapping("")
	public String setAtributoDeSesion(Model model, HttpSession session) {
		
		
		model.addAttribute("catalogo", ps.getListaProductos());
		model.addAttribute("list", us.getListaUsers());
		
	    return "index";
	}
	
}
