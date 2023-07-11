package com.veterinaria.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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

import com.veterinaria.entity.Cita;
import com.veterinaria.entity.Cliente;
import com.veterinaria.entity.Paciente;
import com.veterinaria.entity.Productos;
import com.veterinaria.entity.Servicios;
import com.veterinaria.entity.Veterinario;
import com.veterinaria.repository.PacienteRepository;
import com.veterinaria.service.CitaService;
import com.veterinaria.service.ClienteService;
import com.veterinaria.service.PacienteService;
import com.veterinaria.service.VeterinarioService;
import com.veterinaria.utils.AppSetting;

import javax.validation.Valid;


@RestController
@RequestMapping("/app/atencion")
@CrossOrigin(origins = AppSetting.URL_API_FRONT_END)
public class AtencionController {

	@Autowired
	private ClienteService serv;

	@Autowired
	private PacienteService servP;
	
	@Autowired
	private CitaService servC;
	
	@Autowired
	private VeterinarioService servV;
	
	@Autowired
	private PacienteRepository repo;
	
	/*======CRUD  CUSTOMERS ============*/
	@GetMapping("/listCustomers")
	@ResponseBody
	public ResponseEntity<List<Cliente>>listadoCliente(){
		List<Cliente> exit = serv.AllListCustomers(); 
		
		return ResponseEntity.ok(exit);
	}
	@GetMapping("/total")
    public long getTotalPacientes() {
        return repo.count();
    }
	
	/*======CRUD  CUSTOMERS ============*/
	@GetMapping("/apellido/{apellido}")
	@ResponseBody
	public ResponseEntity<List<Cliente>>filtroApellido(@PathVariable("apellido") String apellido){
		List<Cliente> exit = serv.filtroApellido(apellido); 
		
		return ResponseEntity.ok(exit);
	}
	@PostMapping("/insertCustomers")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertCustomers(@Valid @RequestBody Cliente cli, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
	
		
		try {
			cli.setFechaRegistro(new Date());
			Cliente obj = serv.insertUpdateCustomer(cli);
			if (obj==null) {
				exit.put("mensaje", "Error en el registro");
			} else {
				exit.put("mensaje", "Se registró Correctamente cliente " + cli.getId_cliente());
			}
		}catch (Exception e) {
			e.printStackTrace();
			exit.put("mensaje", "Error en el registro");
		}
		return ResponseEntity.ok(exit);
	}
	

	@PutMapping("/updateCustomers/{codigo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> UpdateCustomers(@Valid @PathVariable String codigo, @RequestBody Cliente cli, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			Optional<Cliente> clienteExiste = serv.obtenerCliente(codigo);
			if (clienteExiste.isPresent()) {
				
				
				Cliente UpdateCustomers = clienteExiste.get();
				UpdateCustomers.setNombres(cli.getNombres());
				UpdateCustomers.setApellidos(cli.getApellidos());
				UpdateCustomers.setCorreo(cli.getCorreo());
				UpdateCustomers.setDni(cli.getDni());
				UpdateCustomers.setEstado(cli.isEstado());
				UpdateCustomers.setFechaRegistro(new Date());
				
				serv.insertUpdateCustomer(UpdateCustomers);
				
				exit.put("mensaje", "Actualizacion del propietario Exitoso" );
			
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
	
	
	@DeleteMapping("/eliminaCliente/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaCliente(@PathVariable("id") String idCliente) {
		Map<String, Object> salida = new HashMap<>();
		try {
			serv.eliminaCliente(idCliente);
			salida.put("mensaje", "Cliente eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
	/*====================CRUD PACIENTE=======================================*/
	

	@GetMapping("/listPaciente")
	@ResponseBody
	public ResponseEntity<List<Paciente>>ListadoPaciente(){
		List<Paciente> exit = servP.ListadoPaciente(); 
		
		return ResponseEntity.ok(exit);
	}

	@GetMapping("/obtener/{idPaciente}")
	 public Optional<Paciente> getPacienteById(@PathVariable String idPaciente ) {
		
	        return repo.findById(idPaciente);
	    }
	
	@PostMapping("/insertPaciente")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> InsertPaciente(@Valid @RequestBody Paciente pac, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			
			Optional<Cliente> clienteSearch =  serv.obtenerCliente(pac.getCliente().getId_cliente());
			
			
			if (clienteSearch.isPresent()) {
				Cliente cliente = clienteSearch.get();
				pac.setCliente(cliente);
			    servP.InsertUpdatePaciente(pac);
			    exit.put("mensaje", "Se registró nuevo paciente " + pac.getId_paciente());
			} else {
				exit.put("mensaje", "Error al registrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exit.put("mensaje", "Error en el registor");
		}
		return ResponseEntity.ok(exit);
	}
	
	@PutMapping("/updatePaciente/{codigo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> UpdatePaciente(@Valid @PathVariable String codigo, @RequestBody Paciente pa, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			Optional<Paciente> pacienteExiste = servP.obtenerPaciente(codigo);
			if (pacienteExiste.isPresent()) {
				Paciente UpdatePaciente = pacienteExiste.get();
				UpdatePaciente.setApodo(pa.getApodo());
				UpdatePaciente.setEdad(pa.getEdad());
				UpdatePaciente.setEspecie(pa.getEspecie());
				UpdatePaciente.setRaza(pa.getRaza());
				UpdatePaciente.setSexo(pa.getSexo());
				UpdatePaciente.setTamaño(pa.getTamaño());
				UpdatePaciente.setPeso(pa.getPeso());
				UpdatePaciente.setFechaNacimiento(pa.getFechaNacimiento());
				
				servP.InsertUpdatePaciente(UpdatePaciente);
				
				exit.put("mensaje", "Actualizacion del paciente exitosa");
			
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
	
	
	@DeleteMapping("/eliminaPaciente/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminiaCliente(@PathVariable("id") String id_paciente) {
		Map<String, Object> salida = new HashMap<>();
		try {
			servP.DeletePaciente(id_paciente);
			salida.put("mensaje", "Paciente eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
	
	/*=====================MANEJO DE CITAS PARA PACIENTES =========================*/
	
	@GetMapping("/lista-citas")
	@ResponseBody
	public ResponseEntity<List<Cita>>listadoCita(){
		List<Cita> exit = servC.ListadoCitas(); 
		
		return ResponseEntity.ok(exit);
	}

	
	@PostMapping("/registro-cita")
	@ResponseBody
    public ResponseEntity<HashMap<String, Object>>crearCita(@RequestBody Cita cita) {
		HashMap<String, Object> exit = new HashMap<String, Object>();
        try {
            // Obtener el paciente y veterinario por su ID
        	 Optional<Paciente> pacienteSearch =  servP.obtenerPaciente(cita.getPaciente().getId_paciente());
            
        	 Optional<Veterinario> veterinarioSearch =  servV.ObtenerVeterinario(cita.getVeterinario().getId_veterinario());
            
        	 
        	    // Verificar si los objetos Paciente y Veterinario existen
             if (pacienteSearch.isPresent() && veterinarioSearch.isPresent()) {
                 Paciente paciente = pacienteSearch.get();
                 Veterinario veterinario = veterinarioSearch.get();
                 
                 // Establecer la relación entre la cita, el paciente y el veterinario
                 cita.setPaciente(paciente);
                 cita.setVeterinario(veterinario);
                 
                 // Guardar la cita en la base de datos
                servC.InsertUpdateCita(cita);
                exit.put("mensaje", "Cita registrada con ID: " + cita.getId_cita());
             }else {
            	 exit.put("mensaje", "Error al registrar");
             }
           
            
        } catch (Exception e) {
        	exit.put("mensaje", "Error en el registro");
        }
        return ResponseEntity.ok(exit);
    }
	
	
	
	
	@PutMapping("/updateCita/{codigo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> updateCita(@Valid @PathVariable String codigo, @RequestBody Cita ci, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			Optional<Cita> citaExiste = servC.ObtenerCitas(codigo);
			if (citaExiste.isPresent()) {
				Cita UpdateCita= citaExiste.get();
				UpdateCita.setPaciente(ci.getPaciente());
				UpdateCita.setVeterinario(ci.getVeterinario());
				UpdateCita.setServicio(ci.getServicio());
				UpdateCita.setFecha_atencion(ci.getFecha_atencion());
			
				UpdateCita.setDescripcion(ci.getDescripcion());
				UpdateCita.setCosto(ci.getCosto());
				UpdateCita.setEstado(ci.isEstado());
			
				servC.InsertUpdateCita(UpdateCita);
				
				exit.put("mensaje", "Cita actualizada correctamente");
			
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
	

	@DeleteMapping("/eliminaCita/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaCita(@PathVariable("id") String id_cita) {
		Map<String, Object> salida = new HashMap<>();
		try {
			servC.eliminarCitas(id_cita);
			salida.put("mensaje", "Cita eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
	
	//MANTENIMIENTO DE TIPO SERVICIO
	
	@GetMapping("/lista-servicio")
	@ResponseBody
	public ResponseEntity<List<Servicios>>litadoServicio(){
		List<Servicios> exit = servC.ListadoServicios(); 
		return ResponseEntity.ok(exit);
	}

	
	@PostMapping("/registro-servicio")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> RegitroServicio(@Valid @RequestBody Servicios servicio, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			servicio.setFechaRegistro(new Date());
			Servicios objSalida =  servC.InsertUpdateServicio(servicio);
			if (objSalida == null) {
				exit.put("mensaje", "Error al registrar");
			} else {
				exit.put("mensaje", "Se registró nuevo Servicio " + servicio.getId_servicio());
			}
		} catch (Exception e) {
			e.printStackTrace();
			exit.put("mensaje", "Error en el registro");
		}
		return ResponseEntity.ok(exit);
	}
	
	@PutMapping("/updateServicio/{codigo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> updateServicio(@Valid @PathVariable String codigo, @RequestBody Servicios servicio, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			Optional<Servicios> servicioExiste = servC.ObtenerServicio(codigo);
			if (servicioExiste.isPresent()) {
				Servicios UpdateServicio= servicioExiste.get();
				UpdateServicio.setNombre(servicio.getNombre());
				UpdateServicio.setFechaRegistro(new Date());
				UpdateServicio.setEstado(servicio.isEstado());
			
				servC.InsertUpdateServicio(UpdateServicio);
				
				exit.put("mensaje", "Servicio actualizado correctamente");
			
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
	
	@DeleteMapping("/eliminaServicio/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaServicio(@PathVariable("id") String id_servicio) {
		Map<String, Object> salida = new HashMap<>();
		try {
			servC.eliminarServicio(id_servicio);
			salida.put("mensaje", "Servicio eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
}
