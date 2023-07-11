package com.veterinaria.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.veterinaria.entity.Cliente;
import com.veterinaria.repository.ClienteRepository;
import com.veterinaria.service.ClienteService;


@Service
public class ClienterServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public List<Cliente> AllListCustomers(){		
		return repo.findAll();
	}

	@Override
	public Cliente insertUpdateCustomer(Cliente obj) {		
		return repo.save(obj);
	}

	@Override
	public List<Cliente> listCustomersPordni(String dni) {
		
		return repo.LisCustomersXdni(dni);
	}

	@Override
	public List<Cliente> listCustomersPorPhone(String cel) {

		return repo.LisCustomersXPhone(cel);
	}

	@Override
	public void eliminaCliente(String idCliente) {
		repo.deleteById(idCliente);
	}

	@Override
	public Optional<Cliente> obtenerCliente(String id) {
		
		return repo.findById(id);
	}

	@Override
	public List<Cliente> filtroApellido(String apellido) {
		// TODO Auto-generated method stub
		return repo.findByApellido(apellido);
	}

}
