package com.sofka.challenge.concurso;

import com.sofka.challenge.concurso.controller.Juego;
import com.sofka.challenge.concurso.gui.VentanaMenu;

/**
 * Clase que se ncarga de ejecutar el juego
 * 
 * @author JaviierCarrillo
 *
 */
public class Aplicacion {

	public static void main(String[] args) {
		Juego juego = new Juego();
		VentanaMenu menu = new VentanaMenu(juego);
		menu.setVisible(true);
	}

}
