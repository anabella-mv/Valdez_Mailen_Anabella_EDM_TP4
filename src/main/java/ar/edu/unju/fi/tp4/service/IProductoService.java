package ar.edu.unju.fi.tp4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Producto;

@Service
public interface IProductoService {
	
	public void guardarProducto(Producto unProducto);
	public void eliminarProducto(int codigo) throws Exception;
	public void modificarProducto(Producto productoModificado) throws Exception;
	public Producto obtenerProducto(int codigo);
	public List<Producto> obtenerProductos();
	public Producto obtenerNuevoProducto();
	public Producto obtenerUltimoProducto();
	public Producto encontrarUnProducto(int cod) throws Exception;
	public Producto crearProducto();
}