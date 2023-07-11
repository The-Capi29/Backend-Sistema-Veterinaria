package com.veterinaria.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.veterinaria.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

	@Query("select d from Cliente d where d.dni = ?1")
	public List<Cliente> LisCustomersXdni(String dni);
	
	@Query("select c from Cliente c where c.celular = ?1")
	public List<Cliente> LisCustomersXPhone(String cel);
	
	@Query(value="SELECT * FROM tb_cliente WHERE apellidos LIKE %?1%", nativeQuery = true)
	public List<Cliente> findByApellido(String apellido);
	
	
}
