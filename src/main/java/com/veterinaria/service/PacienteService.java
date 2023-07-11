package com.veterinaria.service;

import java.util.List;
import java.util.Optional;


import com.veterinaria.entity.Paciente;

public interface PacienteService {

	public abstract List<Paciente> ListadoPaciente();
	public Paciente InsertUpdatePaciente(Paciente obj);
	public Optional<Paciente> obtenerPaciente(String id);
	public void DeletePaciente(String id_paciente);
}
