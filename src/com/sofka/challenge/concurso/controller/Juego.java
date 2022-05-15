package com.sofka.challenge.concurso.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.sofka.challenge.concurso.vo.Categoria;
import com.sofka.challenge.concurso.vo.Participante;

/**
 * Clase que administra toda la información del juego
 * @author javie
 *
 */
public class Juego {

	private Categoria categoria1, categoria2, categoria3, categoria4, categoria5;
    private ArrayList<Participante> participantes;

    /**
     * se cargan las categorias del juego
     */
    public Juego(){
        categoria1  = new Categoria("resource/categoriaUno.txt");
        categoria2 = new Categoria("resource/categoriaDos.txt");
        categoria3 = new Categoria("resource/categoriaTres.txt");
        categoria4 = new Categoria("resource/categoriaCuatro.txt");
        categoria5 = new Categoria("resource/categoriaCinco.txt");
        participantes = new ArrayList<>();
    }
    
    public Participante encontrarParticipante(int id) {
    	for (Participante participante : participantes) {
			if(participante.getIdParticipante() == id) {
				return participante;
			}
		}
    	return null;
    }

	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
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
