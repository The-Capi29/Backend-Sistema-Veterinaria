package com.veterinaria.service;

import java.util.List;
import java.util.Optional;

import com.veterinaria.entity.Cita;

import com.veterinaria.entity.Servicios;

public interface CitaService {
	
	/*=========CRUD PARA CITAS=============*/
	public abstract Cita InsertUpdateCita(Cita obj);
	public abstract List<Cita> ListadoCitas();
	public Optional<Cita> ObtenerCitas(String codigo);
	public void eliminarCitas(String codigo);
	/*====================================*/
	
	/*===CRUD DE SERVICIOS PARA CITAS=======*/
	public abstract Servicios InsertUpdateServicio(Servicios obj);
	public abstract List<Servicios> ListadoServicios();
	public Optional<Servicios> ObtenerServicio(String codigo);
	public void eliminarServicio(String codigo);
	/*======================================*/
}
