package com.veterinaria.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.veterinaria.entity.Usuario;
import com.veterinaria.repository.UsuarioRepository;
import com.veterinaria.service.UsuarioService;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	 	@Autowired
	    private UsuarioRepository usuarioRepository;
	 	@Autowired
	 	private UsuarioService serv;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Usuario usuario = serv.getByUsernameOrEmail(username).get();
	        if(usuario == null){
	            throw new UsernameNotFoundException("Usuario no encontrado");
	        }
	        
	        
		return usuario;
	}

}
