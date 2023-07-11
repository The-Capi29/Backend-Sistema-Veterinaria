package com.veterinaria.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.entity.Rol;
import com.veterinaria.entity.Usuario;
import com.veterinaria.entity.UsuarioRol;
import com.veterinaria.repository.UsuarioRepository;
import com.veterinaria.service.UsuarioService;
import com.veterinaria.utils.AppSetting;


@RestController
@RequestMapping("app/usuarios")
@CrossOrigin(origins = AppSetting.URL_API_FRONT_END)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder bCrypPasswordEncoder;
	
	 @Autowired
	 private UsuarioRepository repousu;
	 
	 @GetMapping("/total")
	    public long getTotalProductos() {
	        return repousu.count();
	    }
	
	
	@PostMapping("/registroUsuario")
	public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
		
		usuario.setPassword(this.bCrypPasswordEncoder.encode(usuario.getPassword()));
		Set<UsuarioRol> roles = new HashSet<>();
		Rol rol = new Rol();
		
		rol.setRolid(2);
		rol.setNameRol("VETERINARIO");
		
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setUsuario(usuario);
		usuarioRol.setRol(rol);
		
		roles.add(usuarioRol);
		return usuarioService.guardarUsuario(usuario, roles);
	}
	
	
	@GetMapping("/{username}")
	public Usuario obtenerUsuario(@PathVariable("username") String username) {
		
		return usuarioService.obtenerUsuario(username);
	}
	
	@DeleteMapping("/{usuarioid}")
	public void eliminarusuario(@PathVariable("usuarioid") Integer id) {
		
		 usuarioService.eliminarUsuario(id);
	}
	
	
}
