package com.sofka.challenge.concurso.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sofka.challenge.concurso.controller.Juego;
import com.sofka.challenge.concurso.vo.Participante;
import com.sofka.challenge.concurso.vo.Pregunta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * clase que crea una ventana donde se va a cargar la información de las
 * preguntas y respuestas
 * 
 * @author JaviierCarrillo
 *
 */
public class VentanaJuego extends JFrame {

	private JPanel contentPane;
	private Juego juego;
	private int categoria;
	private int puntaje;
	private Participante participante;
	private String respuesta; // Respuesta correcta de la pregunta
	private ArrayList<String> respuestas; // posibles respuestas de la pregunta
	private JLabel lblCategoria;
	private JLabel lblPregunta;
	private JRadioButton rdbtnOpc1, rdbtnOpc2, rdbtnOpc3, rdbtnOpc4;
	private ButtonGroup grupoPreguntas;
	private JLabel lblPuntaje;

	/**
	 * Create the frame.
	 */
	public VentanaJuego(Juego juego, int pin) {
		this.participante = juego.encontrarParticipante(pin);
		this.juego = juego;
		categoria = 1;
		puntaje = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		lblCategoria = new JLabel("Categoria: " + categoria);
		lblCategoria.setBounds(379, 11, 112, 14);
		contentPane.add(lblCategoria);

		grupoPreguntas = new ButtonGroup();

		rdbtnOpc1 = new JRadioButton("Opcion 1");
		rdbtnOpc1.setBounds(6, 113, 485, 23);
		grupoPreguntas.add(rdbtnOpc1);
		contentPane.add(rdbtnOpc1);

		rdbtnOpc2 = new JRadioButton("Opcion 2");
		rdbtnOpc2.setBounds(6, 139, 485, 23);
		grupoPreguntas.add(rdbtnOpc2);
		contentPane.add(rdbtnOpc2);

		rdbtnOpc3 = new JRadioButton("Opcion 3");
		rdbtnOpc3.setBounds(6, 165, 485, 23);
		grupoPreguntas.add(rdbtnOpc3);
		contentPane.add(rdbtnOpc3);

		rdbtnOpc4 = new JRadioButton("Opcion 4");
		rdbtnOpc4.setBounds(6, 197, 485, 23);
		grupoPreguntas.add(rdbtnOpc4);
		contentPane.add(rdbtnOpc4);

		lblPregunta = new JLabel("Pregunta");
		lblPregunta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPregunta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPregunta.setBounds(6, 36, 485, 52);
		contentPane.add(lblPregunta);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnOpc1.isSelected()) {
					verificarRespuesta(0);
				} else if (rdbtnOpc2.isSelected()) {
					verificarRespuesta(1);
				} else if (rdbtnOpc3.isSelected()) {
					verificarRespuesta(2);
				} else if (rdbtnOpc4.isSelected()) {
					verificarRespuesta(3);
				} else {
					juego.mostrarMensaje("Por favor elige una respuesta");
				}
			}
		});
		btnAceptar.setBounds(65, 273, 112, 23);
		contentPane.add(btnAceptar);

		JButton btnRetirada = new JButton("RETIRARSE");
		btnRetirada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				juego.mostrarMensaje(participante.getNombre() + " Su puntaje fue: " + puntaje);
				volverAlMenu();
			}
		});
		btnRetirada.setBounds(314, 273, 112, 23);
		contentPane.add(btnRetirada);

		lblPuntaje = new JLabel(participante.getNombre() + " tu puntaje es: " + puntaje);
		lblPuntaje.setBounds(6, 11, 253, 14);
		contentPane.add(lblPuntaje);
		cargarPregunta(categoria);
	}

	/**
	 * metodo que carga una pregunta a la ventana según su categoría
	 * 
	 * @param categoria de la pregunta
	 */
	public void cargarPregunta(int categoria) {
		Pregunta pregunta = null;

		if (categoria == 1) {
			pregunta = juego.getCategoria1().elegirPreguntaAlAzar();
		} else if (categoria == 2) {
			pregunta = juego.getCategoria2().elegirPreguntaAlAzar();
		} else if (categoria == 3) {
			pregunta = juego.getCategoria3().elegirPreguntaAlAzar();
		} else if (categoria == 4) {
			pregunta = juego.getCategoria4().elegirPreguntaAlAzar();
		} else if (categoria == 5) {
			pregunta = juego.getCategoria5().elegirPreguntaAlAzar();
		}
		cargarTextosVentana(pregunta);

	}

	/**
	 * metodo que carga la información de la pregunta y sus respuestas a la interfaz
	 * gráfica
	 * 
	 * @param pregunta que se va a mostrar al usuario
	 */
	public void cargarTextosVentana(Pregunta pregunta) {
		lblCategoria.setText("Categoria: " + categoria);
		lblPuntaje.setText(participante.getNombre() + " tu puntaje es: " + participante.getPuntaje());
		lblPregunta.setText("<html><body>" + pregunta.getPregunta() + "</body></html>");
		respuesta = pregunta.getRespuestas().get(0);
		respuestas = new ArrayList<>(pregunta.getRespuestas());
		Collections.shuffle(respuestas);
		rdbtnOpc1.setText(respuestas.get(0));
		rdbtnOpc2.setText(respuestas.get(1));
		rdbtnOpc3.setText(respuestas.get(2));
		rdbtnOpc4.setText(respuestas.get(3));
	}

	/**
	 * metodo que verifica si la respuesta elegida por el usuario es la correcta
	 * 
	 * @param r indice de la pregunta elegida con la que se va a verificar
	 */
	public void verificarRespuesta(int r) {
		if (respuestas.get(r).equals(respuesta)) {
			juego.mostrarMensaje("¡Respuesta Correcta!");
			// por cada nivel se multiplica por 5 las ganancias
			puntaje += (categoria * 5);

			participante.setPuntaje(puntaje);

			if (categoria == 5) {
				juego.mostrarMensaje("¡Felicidades, ganaste el juego!");
				volverAlMenu();
				return;
			} else {
				categoria++;
				cargarPregunta(categoria);
				grupoPreguntas.clearSelection();
			}
		} else {
			juego.mostrarMensaje("Respuesta Incorrecta");
			juego.mostrarMensaje(participante.getNombre() + " Perdiste todos tus puntos :(");
			participante.setPuntaje(0);
			volverAlMenu();
		}
	}

	/**
	 * metodo que permite regresar al menú principal
	 */
	public void volverAlMenu() {
		VentanaMenu menu = new VentanaMenu(juego);
		menu.setVisible(true);
		setVisible(false);
	}

}
