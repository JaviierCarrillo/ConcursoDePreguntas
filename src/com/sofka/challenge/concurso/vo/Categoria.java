package com.sofka.challenge.concurso.vo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que va a vargar todas las preguntas por categoria
 * 
 * @author JaviierCarrillo
 *
 */
public class Categoria {
	private ArrayList<Pregunta> preguntas;
	BufferedReader buffer;

	public Categoria(String rutaCategoria) {
		preguntas = new ArrayList<>();
		cargarPreguntas(rutaCategoria);
	}

	public void cargarPreguntas(String fileName) {
		String pregunta, resp1, resp2, resp3, resp4;
		try {
			buffer = new BufferedReader(new FileReader(fileName));
			while ((pregunta = buffer.readLine()) != null) {
				resp1 = buffer.readLine();
				resp2 = buffer.readLine();
				resp3 = buffer.readLine();
				resp4 = buffer.readLine();
				preguntas.add(new Pregunta(pregunta, new ArrayList<>(List.of(resp1, resp2, resp3, resp4))));
			}
		} catch (Exception e) {
			System.err.println("Error al cargar el fichero de preguntas");
			e.printStackTrace();
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					System.err.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * metodo que elige una pregunta de la categoría al azar para mostrarsela al
	 * usuario
	 * 
	 * @return la pregunta aleatoria
	 */
	public Pregunta elegirPreguntaAlAzar() {
		int numAleatorio = (int) Math.floor(Math.random() * preguntas.size());
		return preguntas.get(numAleatorio);
	}
}
