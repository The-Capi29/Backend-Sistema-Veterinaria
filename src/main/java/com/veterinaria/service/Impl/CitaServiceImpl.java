package com.veterinaria.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.entity.Cita;
import com.veterinaria.entity.Servicios;
import com.veterinaria.repository.CitaRepository;
import com.veterinaria.repository.ServicioRepository;
import com.veterinaria.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService {

	@Autowired
	private CitaRepository repo;

	@Autowired
	private ServicioRepository repoS;

	/* MANTENIMIENTO CITAS */
	@Override
	public Cita InsertUpdateCita(Cita obj) {

		return repo.save(obj);
	}

	@Override
	public List<Cita> ListadoCitas() {

		return repo.findAll();
	}

	@Override
	public Optional<Cita> ObtenerCitas(String codigo) {

		return repo.findById(codigo);
	}

	@Override
	public void eliminarCitas(String codigo) {
		repo.deleteById(codigo);

	}

	/* FIN DE CITAS */
	
	/*===MANTENIMIIENTO DE LOS TIPOS SERVICIOS PARA LA CITA====*/
	@Override
	public Servicios InsertUpdateServicio(Servicios obj) {

		return repoS.save(obj);
	}

	@Override
	public List<Servicios> ListadoServicios() {

		return repoS.findAll();
	}

	@Override
	public Optional<Servicios> ObtenerServicio(String codigo) {

		return repoS.findById(codigo);
	}

	@Override
	public void eliminarServicio(String codigo) {
		repoS.deleteById(codigo);

	}

	
	
	
}
