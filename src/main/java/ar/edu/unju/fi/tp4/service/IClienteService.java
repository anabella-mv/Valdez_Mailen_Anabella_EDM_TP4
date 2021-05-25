package ar.edu.unju.fi.tp4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Cliente;

@Service
public interface IClienteService {
	
	public void guardarCliente(Cliente clienteGuardado);
	public Cliente crearCliente();
	public List<Cliente> obtenerTodosClientes();
	public Cliente encontrarCliente(int dni) throws Exception;
	public void modificarCliente(Cliente clienteModificado) throws Exception;
	public void eliminarCliente(int dni) throws Exception;
}