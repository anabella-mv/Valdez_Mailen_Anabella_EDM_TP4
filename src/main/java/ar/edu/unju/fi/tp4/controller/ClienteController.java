package ar.edu.unju.fi.tp4.controller;

import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.tp4.model.Cliente;
import ar.edu.unju.fi.tp4.service.IClienteService;


@Controller
public class ClienteController {
	private static final Log BELLA = LogFactory.getLog(ClienteController.class);

	@Autowired
	@Qualifier("implementacionMySQL")
	IClienteService clienteService;

	@GetMapping("/cliente/mostrar")
	public String crearCliente(Model model) {
		model.addAttribute("modoEditar", false);
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return "cliente";
	}
	
	@GetMapping("/cliente/editar/{numeroDocumento}")
	public String editarCliente(Model model,@PathVariable(name = "numeroDocumento") int dni) throws Exception{
		try{
			Cliente encontrado = clienteService.encontrarCliente(dni);
			model.addAttribute("unCliente", encontrado);
			model.addAttribute("modoEditar", true);
		}
		catch(Exception e){
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
			model.addAttribute("modoEditar", false);
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return "cliente";
	}
	@GetMapping("/cliente/eliminar/{numeroDocumento}")
	public String eliminarCliente(@PathVariable(name = "numeroDocumento")int dni,Model model) throws Exception{
		try {
			clienteService.eliminarCliente(dni);
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
		}
		return "redirect:/cliente/mostrar";
	}


	@PostMapping("/cliente/guardar")
	public String guardarCliente(@ModelAttribute("unCliente") Cliente nuevoCliente, Model model) {
		BELLA.info("METHOD: ingresando el metodo Guardar");
		clienteService.guardarCliente(nuevoCliente);		
		BELLA.info("Tamaño del Listado: "+ clienteService.obtenerTodosClientes().size());
		trabajarConFechas();
		return "redirect:/cliente/mostrar";
	}
	@PostMapping("/cliente/modificar")
	public String modificarCliente(@ModelAttribute("unCliente") Cliente clienteModificado, Model model){
		clienteService.modificarCliente(clienteModificado);
		return "redirect:/cliente/mostrar";
	}

	public void trabajarConFechas() {
		//algunas cosas con fecha;
		//obtengo tres fechas
		LocalDate fecha1 = clienteService.obtenerTodosClientes().get(0).getFechaNacimiento();
		LocalDate fecha2 = LocalDate.now();
		@SuppressWarnings("unused")
		LocalDate fecha3 = LocalDate.of(2021, 4, 22);
		
		//calculo el período entre dos de ellas
		Period periodo = Period.between(fecha1,fecha2);
		int dias = periodo.getDays();		
		System.out.println("dias: "+dias);
	}

}
