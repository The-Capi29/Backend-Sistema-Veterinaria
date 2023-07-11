package com.veterinaria.service;

import java.util.List;
import java.util.Optional;

import com.veterinaria.entity.Cliente;

public interface ClienteService {
	public abstract List<Cliente>AllListCustomers();
	/*List para validacion de igualdad de dato dni*/
	public abstract List<Cliente> listCustomersPordni(String dni);
	public abstract List<Cliente> listCustomersPorPhone(String cel);
	public Optional<Cliente> obtenerCliente(String id);
	public abstract Cliente insertUpdateCustomer(Cliente obj);
	public abstract void eliminaCliente(String idCliente);
	public abstract  List<Cliente> filtroApellido(String apellido);
	
}
