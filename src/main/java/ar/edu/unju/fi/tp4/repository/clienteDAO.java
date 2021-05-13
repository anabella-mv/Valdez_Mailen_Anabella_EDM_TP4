package ar.edu.unju.fi.tp4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.tp4.model.Cliente;

@Repository
public interface IClienteDAO extends CrudRepository<Cliente, Integer>{

	@Query("from Cliente c order by c.nroDocumento")
	public List<Cliente> obtenerClientes();
	
	
	
	
}