package ar.edu.unju.fi.tp4.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp4.model.Producto;
import ar.edu.unju.fi.tp4.service.IProductoService;


@Controller
public class ProductoController {
	private static final Log BELLA = LogFactory.getLog(ProductoController.class);
	
	
	@Autowired
	IProductoService productoService;
	
	@GetMapping("/producto")
	public ModelAndView cargarProducto() {
		ModelAndView modelView = new ModelAndView("producto");
		modelView.addObject("modoEditar", false);
		modelView.addObject("unProducto", productoService.obtenerNuevoProducto());
		
		return modelView;
	}
	
	@GetMapping("/producto/mostrar")
	public String mostrarProductos(Model modelo){
		modelo.addAttribute("productos", productoService.obtenerProductos());
		return "resultado";
	}

	@GetMapping("/ultimo-producto")
	public String mostrarUltimoProducto(Model model) {
		try {
			model.addAttribute("ultimo", productoService.obtenerUltimoProducto());
			return "ultimo-producto";
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
			model.addAttribute("productos", productoService.obtenerProductos());
			return "resultado";
		}
		
	}
	@GetMapping("/producto/editar/{codigo}")
	public String editarProducto(@PathVariable(name = "codigo")int codigo, Model model) throws Exception{
		try {
			Producto encontrado = productoService.obtenerProducto(codigo);
			model.addAttribute("unProducto", encontrado);
			model.addAttribute("modoEditar", true);
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
			model.addAttribute("modoEditar", false);
		}
		model.addAttribute("productos", productoService.obtenerProductos());
		return "producto";
	}
	@GetMapping("/producto/eliminar/{codigoProducto}")
	public String eliminarProducto(@PathVariable(name = "codigoProducto") int codigo,Model model) throws Exception{
		try {
			productoService.eliminarProducto(codigo);
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
		}
		model.addAttribute("productos", productoService.obtenerProductos());
		return "resultado";
	}
	
	
	@PostMapping("/producto/guardar")
	public String productoGuardar(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
		productoService.guardarProducto(nuevoProducto);
		BELLA.error("Solo de prueba");
		return "redirect:/producto";
	}

	@PostMapping("/producto/modificar")
	public String productoModificar(@ModelAttribute(name = "unProducto")Producto productoModificado,Model model){
		productoService.modificarProducto(productoModificado);
		model.addAttribute("productos", productoService.obtenerProductos());
		return "resultado";
	}
	
}
