package com.veterinaria.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.veterinaria.utils.SecuenciaModels;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name="tb_citas")
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VeterinariaCibertec")
	@GenericGenerator(
			name = "VeterinariaCibertec", strategy = "com.veterinaria.utils.SecuenciaModels", 
			parameters = {
			@Parameter(name = SecuenciaModels.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SecuenciaModels.VALUE_PREFIX_PARAMETER, value = "CI_"),
			@Parameter(name = SecuenciaModels.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(name="id_cita", length = 8)
	private  String id_cita;
	
	
	
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_atencion")
	private Date fecha_atencion;
	
	
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="costo")
	private double costo;
	
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="id_veterinario")
	private Veterinario veterinario;
	

	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicios servicio;
	
	
	private boolean estado;
}
