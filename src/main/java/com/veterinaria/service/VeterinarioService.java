package com.veterinaria.service;

import java.util.List;
import java.util.Optional;


import com.veterinaria.entity.Veterinario;

public interface VeterinarioService {
	public abstract Veterinario InsertUpdateVeterinario(Veterinario obj);
	public abstract List<Veterinario> ListadoVeterinario();
	public Optional<Veterinario> ObtenerVeterinario(String codigo);
	public void eliminarVeterinario(String codigo);
	public abstract List<Veterinario> ListaXCelular(String celular);
}
