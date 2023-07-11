package com.veterinaria.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.veterinaria.utils.SecuenciaModels;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Temporal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="tb_cliente")
public class Cliente implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VeterinariaCibertec")
	@GenericGenerator(
			name = "VeterinariaCibertec", strategy = "com.veterinaria.utils.SecuenciaModels", 
			parameters = {
			@Parameter(name = SecuenciaModels.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SecuenciaModels.VALUE_PREFIX_PARAMETER, value = "CL_"),
			@Parameter(name = SecuenciaModels.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(name="id_cliente", length = 8)
	private  String id_cliente;
	

	@NotEmpty(message = "El nombre no puede ser vacio")
	@Pattern(regexp = "[a-zA-ZáéíóúñÁÉÍÓÚÑ\\s]{3,30}" , message="El nombre es de 2 a 30 caracteres")
	@Column(name="nombres", length = 100)
	private  String nombres;
	
	@NotEmpty(message = "El Apellido no puede ser vacio")
	@Pattern(regexp = "[a-zA-ZáéíóúñÁÉÍÓÚÑ\\s]{3,100}" , message="El nombre es de 2 a 100 caracteres")
	@Column(name="apellidos", length = 100)
	private  String apellidos;
	
	@NotEmpty(message = "El Apellido no puede ser vacio")
	@Pattern(regexp = "[a-zA-ZáéíóúñÁÉÍÓÚÑ\\s]{3,100}" , message="la direccion es de 2 a 100 caracteres")
	@Column(name="direccion", length = 100)
	private  String direccion;
	
	
	@Pattern(regexp = "[0-9]{9}", message ="El Celular debe contener 9 digitos")
	@Column(name="celular", length = 9)
	private  String celular;
	
	@NotEmpty(message = "El correo no puede ser vacio")
	@Column(name="correo")
	private  String correo;
	
	@NotEmpty(message = "El documento no puede ser vacio")
	@Pattern(regexp = "[0-9]{8}", message ="El dni debe contener 8 digitos")
	@Column(name="dni", length = 8)
	private  String dni;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaRegistro;
	
	private boolean estado=true;
	
	
	
}
