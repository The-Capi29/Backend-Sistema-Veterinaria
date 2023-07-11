package com.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.veterinaria.entity.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, String>{

	
	@Query("select v from Veterinario v where v.celular = ?1")
	public List<Veterinario> listaxcelular(String phone);
}
