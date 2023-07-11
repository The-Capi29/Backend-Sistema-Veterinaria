package com.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinaria.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, String>  {

}
