package com.veterinaria.service.Impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.entity.Usuario;
import com.veterinaria.entity.UsuarioRol;
import com.veterinaria.repository.RolRepository;
import com.veterinaria.repository.UsuarioRepository;
import com.veterinaria.service.UsuarioService;



@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		
		Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
		if(usuarioLocal != null) {
			System.out.println("El usuario ya existe");
			throw new Exception("El usuario ya esta presente");
		}
		else {
			for(UsuarioRol usuarioRol:usuarioRoles) {
				rolRepository.save(usuarioRol.getRol());
			}
			usuario.getUsuarioRoles().addAll(usuarioRoles);
			usuarioLocal = usuarioRepository.save(usuario);
			
		}
		return usuarioLocal;
		
	}

	@Override
	public Usuario obtenerUsuario(String username) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public void eliminarUsuario(Integer usuarioId) {
		usuarioRepository.deleteById(usuarioId);
		
	}

	@Override
	public Optional<Usuario> getByUsernameOrEmail(String nombreOrEmail) {
		
		return usuarioRepository.findByUsernameOrEmail(nombreOrEmail, nombreOrEmail);
	}

}
