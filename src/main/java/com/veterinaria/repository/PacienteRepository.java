package com.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.veterinaria.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, String>{

}
