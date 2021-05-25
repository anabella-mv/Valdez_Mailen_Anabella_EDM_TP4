package ar.edu.unju.fi.tp4.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Cliente;
import ar.edu.unju.fi.tp4.service.IClienteService;
import ar.edu.unju.fi.tp4.util.ListadoClientes;

@Service
@Qualifier("ListaImp")
public class ClienteServiceImp implements IClienteService {

	public List<Cliente> clientes = ListadoClientes.clientes;

	@Autowired
	Cliente unCliente;

	@Override
	public void guardarCliente(Cliente unCliente) {
		clientes.add(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {

		return clientes;
	}

	@Override
	public Cliente encontrarCliente(int dni) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNroDoc() == dni) {
				return clientes.get(i);
			}
		}
		return null;
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNroDoc() == clienteModificado.getNroDoc()) {
				clientes.set(i, clienteModificado);
				break;
			}
		}
	}

	@Override
	public void eliminarCliente(int dni) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNroDoc() == dni) {
				clientes.remove(i);
				break;
			}
		}
	}
}