package com.sofka.challenge.concurso.vo;

import java.util.ArrayList;

public class Pregunta {

	private String pregunta;
	private ArrayList<String> respuestas;

	public Pregunta(String pregunta, ArrayList<String> respuestas) {
		if (respuestas.isEmpty()) {
			System.out.println("no se agregaron respuestas a la pregunta: " + pregunta);
		}
		this.pregunta = pregunta;
		this.respuestas = respuestas;
	}

	public String getPregunta() {
		return pregunta;
	}

	public ArrayList<String> getRespuestas() {
		return respuestas;
	}

	@Override
	public String toString() {
		return "Pregunta: " + pregunta + "\n" + respuestas.get(0) + "\n" + respuestas.get(1) + "\n" + respuestas.get(2)
				+ "\n" + respuestas.get(3) + "\n";
	}

}
