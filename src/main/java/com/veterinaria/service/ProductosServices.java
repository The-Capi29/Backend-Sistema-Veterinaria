package com.veterinaria.service;

import java.util.List;
import java.util.Optional;

import com.veterinaria.entity.Categoria;
import com.veterinaria.entity.Productos;

public interface ProductosServices {

	public abstract Productos InsertUpdateProductos(Productos obj);
	public abstract List<Productos> ListadoProductos();
	public Optional<Productos> ObtenerProducto(String codigo);
	public void eliminarProducto(String codigo);
	/*public abstract List<Productos> ListaProducto();*/


	/*METODO PARA CATEGORIA CATEGORIA*/
	
	public abstract Categoria inserUpdateCategoria(Categoria obj);
	public abstract List<Categoria> ListadoCategoria();
	public Optional<Categoria> ObtenerCategoria(int codigo);
	public void eliminarCategoria(int codigo);
}
