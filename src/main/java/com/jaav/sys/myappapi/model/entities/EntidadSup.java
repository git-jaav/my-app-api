package com.jaav.sys.myappapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntidadSup implements Serializable{

	private static final long serialVersionUID = 1L;

	private int inicio;
	private int numeroFilas;			
	private int contadorTotal;
	private String accionDB;


}
