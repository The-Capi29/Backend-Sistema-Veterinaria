package com.veterinaria.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.entity.Veterinario;
import com.veterinaria.repository.VeterinarioRepository;
import com.veterinaria.service.VeterinarioService;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{
	
	@Autowired
	private VeterinarioRepository repo;

	@Override
	public Veterinario InsertUpdateVeterinario(Veterinario obj) {
		
		return repo.save(obj);
	}

	@Override
	public List<Veterinario> ListadoVeterinario() {
		
		return repo.findAll();
	}

	@Override
	public Optional<Veterinario> ObtenerVeterinario(String codigo) {
		
		return repo.findById(codigo);
	}

	@Override
	public void eliminarVeterinario(String codigo) {
		repo.deleteById(codigo);
		
	}

	@Override
	public List<Veterinario> ListaXCelular(String celular) {
		
		return repo.listaxcelular(celular);
	}

}
