package com.veterinaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinaria.entity.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	public Usuario findByUsername(String username);
	
	Optional<Usuario> findByUsernameOrEmail(String username, String email);

}
