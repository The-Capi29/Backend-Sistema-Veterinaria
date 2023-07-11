package com.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinaria.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
