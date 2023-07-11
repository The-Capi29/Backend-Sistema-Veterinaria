package com.veterinaria.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.entity.EmailValuesDTO;
import com.veterinaria.entity.Mensaje;

import com.veterinaria.entity.Usuario;
import com.veterinaria.entity.UsuarioRol;
import com.veterinaria.service.EmailService;
import com.veterinaria.service.UsuarioService;
import com.veterinaria.utils.AppSetting;

@RestController
@RequestMapping("/email-password")
@CrossOrigin(origins = AppSetting.URL_API_FRONT_END)
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@Autowired
	UsuarioService serv;

	@Value("${spring.mail.username}")
	private String mailFrom;
	
	@PostMapping("/send-email")
	public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) throws Exception {
		
		Optional<Usuario> usuarioOpt = serv.getByUsernameOrEmail(dto.getMailTo());
		
		if (!usuarioOpt.isPresent())
			return new ResponseEntity(new Mensaje("No existe usuario con esas credenciales"), HttpStatus.NOT_FOUND);
		
		Usuario usuario = usuarioOpt.get();
		
	
		
		
		dto.setMailFrom(mailFrom);
		dto.setMailTo(dto.getMailTo());
		dto.setSubject("Cambio de contraseña");
		dto.setUserName(usuario.getEmail());
		UUID uuid = UUID.randomUUID();
		
		String tokenPassword = uuid.toString();
		dto.setTokenPassword(tokenPassword);
		usuario.setTokenPassword(tokenPassword);
		
		Set<UsuarioRol> roles = new HashSet<>();
		UsuarioRol usuarioRol = new UsuarioRol();
		roles.add(usuarioRol);
		
		serv.guardarUsuario(usuario, roles);
		emailService.sendEmailTemplate(dto);
		return new ResponseEntity("Correo enviado con éxito", HttpStatus.OK);
	}

}
