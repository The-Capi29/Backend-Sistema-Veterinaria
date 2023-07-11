package com.veterinaria.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.entity.Paciente;
import com.veterinaria.repository.PacienteRepository;
import com.veterinaria.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{
	
	@Autowired
	private PacienteRepository repo;

	@Override
	public List<Paciente> ListadoPaciente() {
		
		return repo.findAll();
	}

	@Override
	public Paciente InsertUpdatePaciente(Paciente obj) {
		
		return repo.save(obj);
	}

	@Override
	public void DeletePaciente(String id_paciente) {
		
		repo.deleteById(id_paciente);
	}

	@Override
	public Optional<Paciente> obtenerPaciente(String id) {
		
		return repo.findById(id);
	}

}
