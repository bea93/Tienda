package curso.spring.tienda.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.tienda.model.OrderDetails;
//import curso.spring.tienda.model.Order;
import curso.spring.tienda.model.Product;
import curso.spring.tienda.model.User;
import curso.spring.tienda.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	ProductService ps;
	
	
	@GetMapping("/add/{id}")
	public String add(@PathVariable int id, HttpSession session) {
		ArrayList<OrderDetails> cart = (ArrayList<OrderDetails>)session.getAttribute("cart");
		boolean existe = false;
		int unidades = 1;
		Product p = ps.getProductXId(id);
		
		if(cart == null) {
			cart = new ArrayList<OrderDetails>();
			session.setAttribute("cart", cart);
		}
		
		for(OrderDetails od : cart) {
			if(od.getIdProducto() == (p.getId())) {
				od.setUnidades(od.getUnidades() + 1);
				od.setTotal(od.getUnidades() * od.getPrecioUnidad());
				od.calcularTotal();
				existe = true;
				break;
			}
		}
		
		if(!existe) {
			OrderDetails od = new OrderDetails(1,  p.getId(), p.getName(), p.getPrice(), unidades, (unidades * p.getPrice()));
			od.calcularTotal();
			cart.add(od);
		}
		
		session.setAttribute("cart", cart);
		return "redirect:/";
	}
	
	@GetMapping("/del/{id}")
	public String del(@PathVariable int id, HttpSession session) {
		ArrayList<OrderDetails> cart = (ArrayList<OrderDetails>)session.getAttribute("cart");
		Product p = ps.getProductXId(id);
		for(OrderDetails od : cart) {
			if(od.getUnidades() > 1) {
				od.setUnidades(od.getUnidades() - 1);
				od.setTotal(od.getUnidades() * od.getPrecioUnidad());
				od.calcularTotal();
			}
			else{
				cart.remove(id);
			}
		}
		
		return "cart";
	}
	
	//Para ir al carrito
	@GetMapping("")
	public String cargar(HttpSession session, Model model) {
		ArrayList<OrderDetails> cart = (ArrayList<OrderDetails>)session.getAttribute("cart");
		//model.addAttribute("Total pedido: ", String.format("%.2f", Order.calcularTotalDetalles(cart)));
		return "cart";
	}
}
