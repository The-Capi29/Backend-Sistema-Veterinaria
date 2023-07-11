package com.veterinaria.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.entity.Categoria;
import com.veterinaria.entity.Productos;
import com.veterinaria.repository.CategoriaRepository;
import com.veterinaria.repository.ProductoRepository;
import com.veterinaria.service.ProductosServices;

@Service
public class ProductosServiceImpl implements ProductosServices{

	@Autowired
	private ProductoRepository repo;
	
	@Autowired
	private CategoriaRepository repoCat;
	
	@Override
	public Productos InsertUpdateProductos(Productos obj) {
	
		return repo.save(obj);
	}

	@Override
	public List<Productos> ListadoProductos() {
		
		return repo.findAll();
	}

	@Override
	public Optional<Productos> ObtenerProducto(String codigo) {
		
		return repo.findById(codigo);
	}

	@Override
	public void eliminarProducto(String codigo) {
		repo.deleteById(codigo);
		
	}	
	
	/*@Override
	public List<Productos> ListaProducto() {
		
		return repo.ListProducto();
	}
	*/
	
	/*=========Implements Categoria=====================*/

	@Override
	public Categoria inserUpdateCategoria(Categoria obj) {
		return repoCat.save(obj);
	}

	@Override
	public List<Categoria> ListadoCategoria() {
		// TODO Auto-generated method stub
		return repoCat.findAll();
	}

	@Override
	public Optional<Categoria> ObtenerCategoria(int codigo) {
		
		return repoCat.findById(codigo);
	}

	@Override
	public void eliminarCategoria(int codigo) {
		repoCat.deleteById(codigo);
		
	}

	
	
	
}
