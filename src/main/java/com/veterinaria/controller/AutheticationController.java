package com.veterinaria.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.entity.JwtRequest;
import com.veterinaria.entity.JwtResponse;
import com.veterinaria.entity.Usuario;
import com.veterinaria.security.jwt.config.JwtUtils;
import com.veterinaria.service.Impl.UserDetailsServiceImpl;
import com.veterinaria.utils.AppSetting;



@RestController
@CrossOrigin(origins = AppSetting.URL_API_FRONT_END)
public class AutheticationController {

	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserDetailsServiceImpl userDetailsService;

	    @Autowired
	    private JwtUtils jwtUtils;
	    
	   
	    
	    
	    @PostMapping("/generate-token")
	    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
	        try{
	            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());
	        }catch (Exception exception){
	            exception.printStackTrace();
	            throw new Exception("Usuario no encontrado");
	        }

	        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
	        String token = this.jwtUtils.generateToken(userDetails);
	        return ResponseEntity.ok(new JwtResponse(token));
	    }

	    private void autenticar(String username,String password) throws Exception {
	        try{
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
	        }catch (DisabledException exception){
	            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
	        }catch (BadCredentialsException e){
	            throw  new Exception("Credenciales invalidas " + e.getMessage());
	        }
	    }

	    @GetMapping("/actual-usuario")
	    public Usuario obtenerUsuarioActual(Principal principal){
	        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
	    }
	
	
}
