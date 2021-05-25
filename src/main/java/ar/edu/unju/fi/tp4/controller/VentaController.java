package ar.edu.unju.fi.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.fi.tp4.service.IProductoService;

@Controller
public class VentaController {
	
	@Autowired
	@Qualifier("impmysqlproducto")
	IProductoService productoService;
	
	@GetMapping("/producto/ventas")
	public String cragarVentas(Model model) {
		model.addAttribute("productos", productoService.obtenerProductos());
		return ("ventas");
	}

}