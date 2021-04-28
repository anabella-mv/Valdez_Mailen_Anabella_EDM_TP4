package ar.edu.unju.fi.tp4.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Producto;

@Service
public interface IProductoService {

	public void guardarProducto(Producto unProducto);
	public void modificaProducto(Producto productoAModificar);
	public void eliminarProducto(Producto productoAElimiar);
	public Producto obtenerUnProducto(String nombreProducto);
	public ArrayList<Producto> obtenerTodosProductos();
	public Producto obtenerProductoNuevo();
	public Producto obtenerUltimoProducto();
}

