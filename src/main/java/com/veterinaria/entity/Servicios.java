package com.veterinaria.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.veterinaria.utils.SecuenciaModels;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="tb_servicios")
public class Servicios {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VeterinariaCibertec")
	@GenericGenerator(
			name = "VeterinariaCibertec", strategy = "com.veterinaria.utils.SecuenciaModels", 
			parameters = {
			@Parameter(name = SecuenciaModels.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SecuenciaModels.VALUE_PREFIX_PARAMETER, value = "SE_"),
			@Parameter(name = SecuenciaModels.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(name="id_servicio", length = 8)
	private String id_servicio;
	
	@Column(name="nombre", length = 100)
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaRegistro;
	
	private boolean estado=true;
	
}
