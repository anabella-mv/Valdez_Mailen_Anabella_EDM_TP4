package ar.edu.unju.fi.tp4.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.tp4.model.Cliente;

@Repository
public interface IClienteDAO extends CrudRepository<Cliente, Integer>{

	@Query(value = "SELECT * FROM clientes WHERE activo = true ORDER BY nro_doc",nativeQuery = true)
    public List<Cliente> obtenerClientes();

    public Optional<Cliente> findByNroDoc(int dni);
    
}