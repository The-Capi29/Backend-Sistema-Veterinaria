package com.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veterinaria.entity.Productos;

public interface ProductoRepository extends JpaRepository<Productos, String>{

	/*@Query(value="select p.id_producto, p.descripcion, p.estado, p.nombre, p.precio, p.stock, c.nombre from tb_producto as p join tb_categoria as c on c.id_categoria = p.id_categoria", nativeQuery = true)
	public List<Productos> ListProducto();*/
	
	 	@Query(value = "SELECT categoria_id_categoria, COUNT(*) AS cantidad_productos FROM tb_producto GROUP BY categoria_id_categoria", nativeQuery = true)
	    List<Object[]> countByVisitType();
	
}
