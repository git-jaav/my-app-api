package com.jaav.sys.myappapi.model.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the me_encuesta_tema database table.
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="me_encuesta_tema")
public class MeEncuestaTema extends EntidadSup {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ente_codigo")
	private String enteCodigo;

	@Column(name="ente_detalles")
	private String enteDetalles;

	@Column(name="ente_estado")
	private String enteEstado;

	@Column(name="ente_tema")
	private String enteTema;

}