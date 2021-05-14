package ar.edu.unju.fi.tp4.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Cliente;
import ar.edu.unju.fi.tp4.repository.IClienteDAO;
import ar.edu.unju.fi.tp4.service.IClienteService;
@Service
@Qualifier("implementacionMySQL")
public class ClienteServiceMySQL implements IClienteService{
	
	@Autowired
	Cliente unCliente;
	
	@Autowired
	IClienteDAO clienteDAO;
	
	@Override
	public void guardarCliente(Cliente clienteGuardado) {
		// TODO Auto-generated method stub
		clienteDAO.save(clienteGuardado);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	public Cliente encontrarCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		return clienteDAO.findById(dni).orElseThrow(()->new Exception ("El cliente no fue encontrado"));
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) {
		// TODO Auto-generated method stub
		clienteDAO.save(clienteModificado);
	}

	@Override
	public void eliminarCliente(int dni) {
		// TODO Auto-generated method stub
		clienteDAO.deleteById(dni);
		
	}

}
