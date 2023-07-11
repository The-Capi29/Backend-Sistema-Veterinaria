package com.veterinaria.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.veterinaria.entity.Veterinario;
import com.veterinaria.service.VeterinarioService;
import com.veterinaria.utils.AppSetting;

@RestController
@RequestMapping("/app/veterinario")
@CrossOrigin(origins = AppSetting.URL_API_FRONT_END)
public class VeterinarioController {

	@Autowired
	private VeterinarioService servV;
	
	@GetMapping("/listVeterinario")
	@ResponseBody
	public ResponseEntity<List<Veterinario>>listadoVeterinario(){
		List<Veterinario> exit = servV.ListadoVeterinario(); 
		System.out.println(exit);
		return ResponseEntity.ok(exit);
	}
	
	@PostMapping("/registro-veterinario")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertVeterinario(@Valid @RequestBody Veterinario ve, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		List<ObjectError> lst = errors.getAllErrors();
		for(ObjectError x : lst) {
			String campo = ((FieldError) x).getField();
			String mensaje = x.getDefaultMessage();
			exit.put(mensaje, campo);
		}
		try {
			List<Veterinario> lstVeterinario = servV.ListaXCelular(ve.getCelular());
			
			if(CollectionUtils.isEmpty(lstVeterinario)) {				
				lstVeterinario = servV.ListaXCelular(ve.getCelular());
				ve.setFechaRegistro(new Date());
				ve.setEstado(true);
				Veterinario obj = servV.InsertUpdateVeterinario(ve);
				if (obj == null) {
					exit.put("mensaje", "Error en el registro");
				} else {
					exit.put("mensaje", "Cliente " + ve.getId_veterinario()+ " registrado correctamente");
				}
			}else {
				exit.put("mensaje", "El número de celular ya existe ");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			exit.put("mensaje", "Error en el registro ---> " + e.getMessage());
		}		
		return ResponseEntity.ok(exit);
	}
	
	@PutMapping("/updateVeterinario/{codigo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> UpdateVeterinario(@Valid @PathVariable String codigo, @RequestBody Veterinario ve, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			Optional<Veterinario> search = servV.ObtenerVeterinario(codigo);
			if (search.isPresent()) {
				
				
				Veterinario actualizar = search.get();
				actualizar.setNombres(ve.getNombres());
				actualizar.setCelular(ve.getCelular());
				actualizar.setEspecialidad(ve.getEspecialidad());
				actualizar.setEstado(ve.isEstado());
				actualizar.setFechaRegistro(new Date());
				
				servV.InsertUpdateVeterinario(actualizar);
				
				exit.put("mensaje", "Actualizacion del veterinario Exitoso" );
			
			} else {
				
				exit.put("mensaje", "Error en la actualización");
				  return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exit.put("mensaje", "Error en el proceso");
		}
		return ResponseEntity.ok(exit);
	}
	
	@DeleteMapping("/eliminaVeterinario/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaVeterinario(@PathVariable("id") String idVeterinario) {
		Map<String, Object> salida = new HashMap<>();
		try {
			servV.eliminarVeterinario(idVeterinario);
			salida.put("mensaje", "Veterinario eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
	
}
