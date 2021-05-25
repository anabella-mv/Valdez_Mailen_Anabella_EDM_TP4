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
		 return clienteDAO.obtenerClientes();
		 //return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	public Cliente encontrarCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		return clienteDAO.findByNroDoc(dni).orElseThrow(()->new Exception ("El cliente no fue encontrado"));
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) throws Exception {
        Cliente clienteAModificar = encontrarCliente(clienteModificado.getNroDoc());
        cambiarCliente(clienteModificado, clienteAModificar);
        clienteDAO.save(clienteAModificar);
        }

	 @Override
	    public void eliminarCliente(int dni) throws Exception {
	        Cliente clienteAEliminar = encontrarCliente(dni);
	        clienteAEliminar.setActivo(false);
	        modificarCliente(clienteAEliminar);
		
	}
	 
	 private void cambiarCliente(Cliente desde, Cliente hacia) {
			hacia.setNroDoc(desde.getNroDoc());
			hacia.setTipoDoc(desde.getTipoDoc());
			hacia.setFechaNacimiento(desde.getFechaNacimiento());
			hacia.setCodigoAreaTel(desde.getCodigoAreaTel());
			hacia.setNumTel(desde.getNumTel());
	        hacia.setActivo(desde.getActivo());
	        hacia.setFechaUltimaCompra(desde.getFechaUltimaCompra());
			hacia.setEmail(desde.getEmail());
	        hacia.setNombreApellido(desde.getNombreApellido());
		}

}
