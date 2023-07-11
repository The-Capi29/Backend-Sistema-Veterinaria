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
@Table(name="tb_paciente")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VeterinariaCibertec")
	@GenericGenerator(
			name = "VeterinariaCibertec", strategy = "com.veterinaria.utils.SecuenciaModels", 
			parameters = {
			@Parameter(name = SecuenciaModels.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SecuenciaModels.VALUE_PREFIX_PARAMETER, value = "PA_"),
			@Parameter(name = SecuenciaModels.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(name="id_paciente", length = 8)
	private String id_paciente;
	@Column(name="apodo", length = 30)
	private String apodo;
	@Column(name="edad", length = 20)
	private String edad;
	@Column(name="especie", length = 30)
	private String especie;
	@Column(name="raza", length = 30)
	private String raza;
	@Column(name="sexo", length = 9)
	private String sexo;
	@Column(name="tamaño", length = 5)
	private double tamaño;
	@Column(name="peso", length = 5)
	private double peso;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	
	
	
	@ManyToOne
	@JoinColumn(name= "id_cliente")
	private Cliente cliente;
	
	
	
	
}
