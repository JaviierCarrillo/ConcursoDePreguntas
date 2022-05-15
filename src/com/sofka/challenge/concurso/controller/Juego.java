package com.sofka.challenge.concurso.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import com.sofka.challenge.concurso.vo.Categoria;
import com.sofka.challenge.concurso.vo.Participante;

/**
 * Clase que administra toda la información del juego
 * 
 * @author JaviierCarrillo
 *
 */
public class Juego {

	private Categoria categoria1, categoria2, categoria3, categoria4, categoria5;
	private ArrayList<Participante> participantes;

	/**
	 * se cargan las categorias del juego
	 */
	public Juego() {
		categoria1 = new Categoria("resource/categoriaUno.txt");
		categoria2 = new Categoria("resource/categoriaDos.txt");
		categoria3 = new Categoria("resource/categoriaTres.txt");
		categoria4 = new Categoria("resource/categoriaCuatro.txt");
		categoria5 = new Categoria("resource/categoriaCinco.txt");
		participantes = new ArrayList<>();
		cargarArchivos();
	}

	/**
	 * metodo que busca a un participante en la lista a través de su pin de
	 * seguridad
	 * 
	 * @param id es el pin de seguridad
	 * @return
	 */
	public Participante encontrarParticipante(int id) {
		for (Participante participante : participantes) {
			if (participante.getIdParticipante() == id) {
				return participante;
			}
		}
		return null;
	}

	/**
	 * metodo que guarda los datos de los participantes en un archivo .dat
	 */
	public void guardarArchivos() {
		try {
			ObjectOutputStream escribiendoArchivo = new ObjectOutputStream(
					new FileOutputStream("resource/participantes.dat"));
			escribiendoArchivo.writeObject(participantes);
			escribiendoArchivo.close();
		} catch (IOException e) {
			System.err.println("Error al escribir el archivo");
			e.printStackTrace();
		}
	}

	/**
	 * metodo que lee los datos de los participantes desde un archivo .dat
	 */
	@SuppressWarnings("unchecked")
	public void cargarArchivos() {
		try {
			ObjectInputStream leerArchivo = new ObjectInputStream(new FileInputStream("resource/participantes.dat"));
			participantes = (ArrayList<Participante>) leerArchivo.readObject();
		} catch (IOException e) {
			System.err.println("Error al acceder al archivo");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * metodo que muestra un mensaje en pantalla
	 * 
	 * @param mensaje que se quiere mostrar
	 */
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	/**
	 * metodo que ordena a los participantes de mayor a menor según su puntaje
	 */
	public void ordenarParticipantes() {
		Collections.sort(participantes, new Comparator<Participante>() {
			@Override
			public int compare(Participante p1, Participante p2) {
				return Integer.valueOf(p2.getPuntaje()).compareTo(Integer.valueOf(p1.getPuntaje()));
			}
		});
	}

	// Getters and setters
	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(ArrayList<Participante> participantes) {
		this.participantes = participantes;
	}

	public Categoria getCategoria1() {
		return categoria1;
	}

	public Categoria getCategoria2() {
		return categoria2;
	}

	public Categoria getCategoria3() {
		return categoria3;
	}

	public Categoria getCategoria4() {
		return categoria4;
	}

	public Categoria getCategoria5() {
		return categoria5;
	}

}
