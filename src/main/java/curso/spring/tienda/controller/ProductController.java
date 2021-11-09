package curso.spring.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.tienda.model.Product;
import curso.spring.tienda.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService ps;
	
	// Muestra el formulario de registro
		@GetMapping("/new")
		public String creacion(Model model) {
			model.addAttribute("product", new Product());
			return "product/add";
		}

		// Recoge los datos del formulario de registro
		@PostMapping("/new")
		public String crear(Model model, @ModelAttribute Product product, HttpSession session) {
			ps.addProduct(product);
			session.setAttribute("mensaje", "Producto creado.");
			return "redirect:/";

		}

		// Muestra el formulario de registro
		@GetMapping("/update/{id}")
		public String edicion(@PathVariable int id, Model model) {
			Product p = ps.getProductXId(id);
			model.addAttribute("product", p);
			return "product/edit";
		}

		// Recoge los datos del formulario de registro
		@PostMapping("/update/submit")
		public String modificar(@ModelAttribute Product product, HttpSession session) {
			ps.editProduct(product);
			session.setAttribute("mensaje", "Producto actualizado.");
			return "redirect:/";

		}

		@GetMapping("/del/{id}")
		public String eliminar(@PathVariable int id) {
			ps.delProduct(id);
			return "redirect:/";
		}
		
		@GetMapping("/detail/{id}")
		public String detallesProducto(@PathVariable int id, Model model) { 
			Product p = ps.getProductXId(id);   
			model.addAttribute("product", p);    
			return "product/detail";
			
		}
}
