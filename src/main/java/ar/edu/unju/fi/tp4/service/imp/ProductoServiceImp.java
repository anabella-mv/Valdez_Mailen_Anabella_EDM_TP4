package ar.edu.unju.fi.tp4.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Producto;
import ar.edu.unju.fi.tp4.service.IProductoService;
import ar.edu.unju.fi.tp4.util.ListadoProductos;

@Service
public class ProductoServiceImp implements IProductoService {

	private static final Log ANABELLA = LogFactory.getLog(ProductoServiceImp.class);
	
	@Autowired
	Producto unProducto;
	
	public List<Producto> listadoProductos = ListadoProductos.productos;

	@Override
	public void guardarProducto(Producto unProducto) {
		System.out.println(unProducto.getNombre());
		listadoProductos.add(unProducto);
		System.out.println(listadoProductos.size());

		ANABELLA.info("METHOD: Ingresando a guardar producto");
		ANABELLA.info("RESULT: Guardado" + listadoProductos.get(listadoProductos.size()-1).getNombre());
		ANABELLA.info("Producto Guardado: " + unProducto.getCodigo());
	}


	@Override
	public void eliminarProducto(int codigo) {
		for(int i = 0; i < listadoProductos.size(); i++){
			if(listadoProductos.get(i).getCodigo() == codigo){
				listadoProductos.remove(i);
				break;
			}
		}
	}

	@Override
	public Producto obtenerProducto(int codigo) {
		for(int i = 0; i < listadoProductos.size(); i++){
			if(listadoProductos.get(i).getCodigo() == codigo){
				return listadoProductos.get(i);
			}
		}
		return null;
	}

	@Override
	public List<Producto> obtenerProductos() {
		ANABELLA.info("Todos los productos obtenidos");
		return listadoProductos;
	}

	@Override
	public Producto obtenerNuevoProducto() {
		ANABELLA.info("Nuevo producto obtenido y listo para ser cargado");
		return unProducto;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		int index = listadoProductos.size() - 1;
		ANABELLA.info("Ultimo producto Guardado: " + listadoProductos.get(index).getCodigo());
		return listadoProductos.get(index);
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		for(int i = 0; i < listadoProductos.size(); i++){
			if(listadoProductos.get(i).getCodigo() == productoModificado.getCodigo()){
				listadoProductos.set(i, productoModificado);
				break;
			}
		}
	}
	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}
	@Override
	public Producto encontrarUnProducto(int cod) {
		// TODO Auto-generated method stub
		for (int i=0; i < listadoProductos.size();i++)
		{
			if(listadoProductos.get(i).getCodigo() == cod)
			{
				unProducto = listadoProductos.get(i);
	    	}
		}
		return unProducto;
	}
}