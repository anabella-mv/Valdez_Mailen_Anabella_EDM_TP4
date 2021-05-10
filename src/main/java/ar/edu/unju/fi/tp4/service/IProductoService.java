package ar.edu.unju.fi.tp4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Producto;

@Service
public interface IProductoService {
	
	public void guardarProducto(Producto unProducto);
	public void eliminarProducto(int codigo);
	public Producto obtenerProducto(int codigo);
	public List<Producto> obtenerProductos();
	public Producto obtenerNuevoProducto();
	public Producto obtenerUltimoProducto();
	public void modificarProducto(Producto productoModificado);
}