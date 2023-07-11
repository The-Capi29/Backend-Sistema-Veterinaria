package com.veterinaria.service;

import java.util.Optional;
import java.util.Set;

import com.veterinaria.entity.Usuario;
import com.veterinaria.entity.UsuarioRol;



public interface UsuarioService {
	
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
	public Usuario obtenerUsuario(String username);
	public void eliminarUsuario(Integer usuarioId);
	public Optional<Usuario> getByUsernameOrEmail(String nombreOrEmail);
}
