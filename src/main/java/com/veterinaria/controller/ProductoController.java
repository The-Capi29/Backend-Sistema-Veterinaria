package com.veterinaria.controller;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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

import com.veterinaria.entity.Categoria;
import com.veterinaria.entity.Productos;
import com.veterinaria.repository.ProductoRepository;
import com.veterinaria.service.ProductosServices;
import com.veterinaria.utils.AppSetting;

@RestController
@RequestMapping("/app/producto")
@CrossOrigin(origins = AppSetting.URL_API_FRONT_END)
public class ProductoController {

	@Autowired
	private ProductosServices proserv;
	
	@Autowired
	private ProductoRepository repo;
	
	
	/*====cantidad de productos por categoria======*/
	
	@GetMapping("/home")
	public Map<Integer, BigInteger> countByVisitType() {
	    Map<Integer, BigInteger> result = new HashMap<>();
	    List<Object[]> counts = repo.countByVisitType();
	    for (Object[] row : counts) {
	        Integer categoriaId = (Integer) row[0];
	        BigInteger cantidadProductos = (BigInteger) row[1];
	        result.put(categoriaId, cantidadProductos);
	    }
	    return result;
	}
	/*========MANTENIMIENTO DE PRODUCTOS=============*/
	@GetMapping("/lista-producto")
	public ResponseEntity<List<Productos>>ListaProductos(){
		List<Productos> exit = proserv.ListadoProductos();
		return ResponseEntity.ok(exit);
	}
	
	
	@GetMapping("/total")
    public long getTotalProductos() {
        return repo.count();
    }
	@GetMapping("/obtener/{idProducto}")
	 public Optional<Productos> getProductosById(@PathVariable String idProducto ) {
		
	        return repo.findById(idProducto);
	    }
	
	
	@PostMapping("/registro-producto")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> InsertProducto(@Valid @RequestBody Productos pro, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			pro.setFechaRegistro(new Date());
			Productos objSalida =  proserv.InsertUpdateProductos(pro);
			if (objSalida == null) {
				exit.put("mensaje", "Error al registrar");
			} else {
				exit.put("mensaje", "Se registró nuevo producto " + pro.getId_producto());
			}
		} catch (Exception e) {
			e.printStackTrace();
			exit.put("mensaje", "Error en el registro");
		}
		return ResponseEntity.ok(exit);
	}
	
	
	@PutMapping("/updateProducto/{codigo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> UpdateCustomers(@Valid @PathVariable String codigo, @RequestBody Productos pro, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			Optional<Productos> productoExiste = proserv.ObtenerProducto(codigo);
			if (productoExiste.isPresent()) {
				
				
				Productos UpdateProducto = productoExiste.get();
				UpdateProducto.setNombre(pro.getNombre());
				UpdateProducto.setDescripcion(pro.getDescripcion());
				UpdateProducto.setStock(pro.getStock());
				UpdateProducto.setCategoria(pro.getCategoria());
				UpdateProducto.setEstado(pro.isEstado());
				UpdateProducto.setFechaRegistro(new Date());
			
				
				proserv.InsertUpdateProductos(UpdateProducto);
				
				exit.put("mensaje", "Producto Actualizado correctamente" );
			
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

	@DeleteMapping("/eliminaProducto/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaProducto(@PathVariable("id") String idProducto) {
		Map<String, Object> salida = new HashMap<>();
		try {
			proserv.eliminarProducto(idProducto);
			salida.put("mensaje", "Producto eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
	/*========FIN MANTENIMIENTO DE PRODUCTOS=============*/
	
	/*==========MANTENIMIENTO DE CATEGORIA==================*/
	@GetMapping("/lista-categiria")
	public ResponseEntity<List<Categoria>>ListaCategoria(){
		List<Categoria> exit = proserv.ListadoCategoria();
		return ResponseEntity.ok(exit);
	}
	
	@PostMapping("/registro-categoria")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> InsertCategoria(@Valid @RequestBody Categoria cat, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			cat.setFechaRegistro(new Date());
			Categoria objSalida =  proserv.inserUpdateCategoria(cat);
			if (objSalida == null) {
				exit.put("mensaje", "Error al registrar");
			} else {
				exit.put("mensaje", "Se registró nuevo producto " + cat.getId_categoria());
			}
		} catch (Exception e) {
			e.printStackTrace();
			exit.put("mensaje", "Error en el registro");
		}
		return ResponseEntity.ok(exit);
	}
	
	@PutMapping("/updateCategoria/{codigo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> updateCategoria(@Valid @PathVariable int codigo, @RequestBody Categoria cat, Errors errors){
		HashMap<String, Object> exit = new HashMap<String, Object>();
		
		try {
			Optional<Categoria> categoriaExiste = proserv.ObtenerCategoria(codigo);
			if (categoriaExiste.isPresent()) {
				
				
				Categoria UpdateCategoria = categoriaExiste.get();
				UpdateCategoria.setNombre(cat.getNombre());				
				UpdateCategoria.setEstado(cat.isEstado());				
				UpdateCategoria.setFechaRegistro(new Date());			
				
				proserv.inserUpdateCategoria(UpdateCategoria);
				
				exit.put("mensaje", "categiria Actualizado correctamente" );
			
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

	@DeleteMapping("/eliminar-categoria/{id_categoria}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminarCategoria(@PathVariable("id_categoria") int id_categoria) {
		Map<String, Object> salida = new HashMap<>();
		try {
			proserv.eliminarCategoria(id_categoria);
			salida.put("mensaje", "Categoria eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	/*==========FIN DE MANTENIMIENTO CATEGORIA=============*/
}
