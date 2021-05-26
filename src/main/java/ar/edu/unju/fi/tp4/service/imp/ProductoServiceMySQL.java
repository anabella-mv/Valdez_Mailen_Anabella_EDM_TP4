package ar.edu.unju.fi.tp4.service.imp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Producto;
import ar.edu.unju.fi.tp4.repository.IProductoDAO;
import ar.edu.unju.fi.tp4.service.IProductoService;

@Service
@Qualifier("impmysqlproducto")
public class ProductoServiceMySQL implements IProductoService{
	@Autowired
	Producto unProducto;
	
	@Autowired
	IProductoDAO productoDAO;
	
	@Override
	public void guardarProducto(Producto productoGuardado) {
		// TODO Auto-generated method stub
		productoDAO.save(productoGuardado);
	}
	@Override
	public Producto obtenerProducto(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		return (ArrayList<Producto>) productoDAO.findAll();
	}
	@Override
	public Producto obtenerNuevoProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}
	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto encontrarUnProducto(int cod) throws Exception {
		return productoDAO.findByCodigo(cod).orElseThrow(()->new Exception("El producto NO existe"));
	}
	
	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public void modificarProducto(Producto productoModificado) throws Exception {
		// TODO Auto-generated method stub
				Producto productoAModificar = productoDAO.findByCodigo(productoModificado.getCodigo()).orElseThrow(()->new Exception("El Producto no fue encontrado"));
				cambiarProducto(productoModificado, productoAModificar);
				productoDAO.save(productoAModificar);
			}
	
	private void cambiarProducto(Producto productoModificado, Producto productoAModificar) {
		// TODO Auto-generated method stub
		productoAModificar.setCodigo(productoModificado.getCodigo());
		productoAModificar.setNombre(productoModificado.getNombre());
		productoAModificar.setMarca(productoModificado.getMarca());
		productoAModificar.setPrecio(productoModificado.getPrecio());
		productoAModificar.setStock(productoModificado.getStock());
		productoAModificar.setDesc(productoModificado.getDesc());
	}
	
	@Override
	public void eliminarProducto(int codigo) throws Exception {
		// TODO Auto-generated method stub
		Producto productoEliminar = productoDAO.findById(codigo).orElseThrow(()->new Exception("El Producto no fue encontrado"));
		productoDAO.delete(productoEliminar);
	}

}