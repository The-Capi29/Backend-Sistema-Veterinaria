package com.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinaria.entity.Servicios;

public interface ServicioRepository extends JpaRepository<Servicios, String> {

}
