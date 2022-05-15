package com.sofka.challenge.concurso.vo;

import java.io.Serializable;

/**
 * Clase que administra la información de un participante
 * 
 * @author JaviierCarrillo
 *
 */
public class Participante implements Serializable {

	private int idParticipante;
	private String nombre;
	private int puntaje;

	public Participante(int idParticipante, String nombre) {
		this.idParticipante = idParticipante;
		this.nombre = nombre;
		puntaje = 0;
	}

	public int getIdParticipante() {
		return idParticipante;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

}
