package com.sofka.challenge.concurso.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sofka.challenge.concurso.controller.Juego;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * metodo que permite iniciar una ventana para el menú principal del juego
 * 
 * @author JaviierCarrillo
 *
 */
public class VentanaMenu extends JFrame {

	private JPanel contentPane;
	private Juego juego;

	/**
	 * Create the frame.
	 */
	public VentanaMenu(Juego juego) {
		this.juego = juego;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int pin = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su PIN de jugador"));
					if (juego.encontrarParticipante(pin) != null) {
						VentanaJuego ventanaJuego = new VentanaJuego(juego, pin);
						ventanaJuego.setVisible(true);
						setVisible(false);
					} else {
						juego.mostrarMensaje("No se encontró este jugador");
					}

				} catch (NumberFormatException ex) {
					juego.mostrarMensaje("El pin debe ser numérico");
				}

			}
		});
		btnJugar.setBounds(42, 73, 156, 23);
		contentPane.add(btnJugar);

		JButton btnRegistro = new JButton("REGISTRARSE");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro registro = new VentanaRegistro(juego);
				registro.setVisible(true);
				setVisible(false);
			}
		});
		btnRegistro.setBounds(42, 107, 156, 23);
		contentPane.add(btnRegistro);

		JButton btnSalir = new JButton("SALIR DEL JUEGO");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				juego.guardarArchivos();
				System.exit(0);
			}
		});
		btnSalir.setBounds(42, 175, 156, 23);
		contentPane.add(btnSalir);

		JButton btnHistorial = new JButton("HISTORIAL");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaResultados resultados = new VentanaResultados(juego);
				resultados.setVisible(true);
				setVisible(false);
			}
		});
		btnHistorial.setBounds(42, 141, 156, 23);
		contentPane.add(btnHistorial);

		JLabel lblTitulo = new JLabel("CONCURSO DE PREGUNTAS");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 230, 23);
		contentPane.add(lblTitulo);
	}
}
