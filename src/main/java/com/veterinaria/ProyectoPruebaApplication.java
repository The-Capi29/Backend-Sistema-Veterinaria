package com.veterinaria;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.veterinaria.entity.Rol;
import com.veterinaria.entity.Usuario;
import com.veterinaria.entity.UsuarioRol;
import com.veterinaria.service.UsuarioService;

@SpringBootApplication
public class ProyectoPruebaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProyectoPruebaApplication.class, args);
	}

	
	/*@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/
	
	 
	@Override
	public void run(String... args) throws Exception {
		/*
		Usuario usuario = new Usuario();
		usuario.setName("Ricardo");
		usuario.setLastname("Espinoza");
		usuario.setUsername("ricardo@gmail.com");
		usuario.setPassword(bCryptPasswordEncoder.encode("ricardo"));
		usuario.setEmail("ricardo@gmail.com");
		usuario.setPhone("988212020");
		
		Rol rol = new Rol();
		rol.setRolid(2);
		rol.setNameRol("VETERINARIO");
		Set<UsuarioRol> usuariosRoles = new HashSet<>();
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setRol(rol);
		usuarioRol.setUsuario(usuario);
		usuariosRoles.add(usuarioRol);
		Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario,usuariosRoles);
		System.out.println(usuarioGuardado.getUsername());*/
	}

}
