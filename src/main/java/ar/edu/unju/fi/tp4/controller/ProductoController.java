package ar.edu.unju.fi.tp4.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.tp4.model.Producto;
import ar.edu.unju.fi.tp4.service.IProductoService;


@Controller
public class ProductoController {
	private static final Log BELLA = LogFactory.getLog(ProductoController.class);
	
	
	@Autowired
	IProductoService productoService;
	
	@GetMapping({"/producto"})
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", productoService.obtenerProductoNuevo());
		return("producto");
	}
	
	@PostMapping("/producto")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
		productoService.guardarProducto(nuevoProducto);
		System.out.println(productoService.obtenerTodosProductos().get(0).getMarca());
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		BELLA.error("Solo de prueba");
		return "resultado";
	}

	@GetMapping("/ultimo")
	public String cargarUltimoProducto(Model model) {
		model.addAttribute("ultimoProducto", productoService.obtenerUltimoProducto());
		return("mostrar-ultimo");
	}
	
	@GetMapping("/volver")
	public String cargarNuevoProducto (Model model) {
		return ("redirect:/producto");
	}
	
	@GetMapping("/todos")
	public String mostrarTodosProductos(Model model) {
		model.addAttribute("todosProductos", productoService.obtenerTodosProductos());
		System.out.println(productoService.obtenerTodosProductos().get(0).getMarca());
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("mostrar-todos");
	}	
	
}
