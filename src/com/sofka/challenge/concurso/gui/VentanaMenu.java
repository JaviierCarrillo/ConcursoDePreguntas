package com.sofka.challenge.concurso.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sofka.challenge.concurso.controller.Juego;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenu extends JFrame {

	private JPanel contentPane;
	private Juego juego;

	/**
	 * Create the frame.
	 */
	public VentanaMenu(Juego juego) {
		this.juego = juego;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int pin = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su PIN de jugador"));
					if(juego.encontrarParticipante(pin) != null) {
						VentanaJuego ventanaJuego = new VentanaJuego(juego, pin);
						ventanaJuego.setVisible(true);
						setVisible(false);
					}else {
						juego.mostrarMensaje("No estás registrado, por favor registrate primero");
					}
					
				}catch(NumberFormatException ex) {
					juego.mostrarMensaje("El pin debe ser un numero entero");
				}
				
			}
		});
		btnJugar.setBounds(123, 95, 156, 23);
		contentPane.add(btnJugar);
		
		JButton btnRegistro = new JButton("REGISTRARSE");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro registro = new VentanaRegistro(juego);
				registro.setVisible(true);
				setVisible(false);
			}
		});
		btnRegistro.setBounds(123, 43, 156, 23);
		contentPane.add(btnRegistro);
		
		JButton btnSalir = new JButton("SALIR DEL JUEGO");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(255, 227, 169, 23);
		contentPane.add(btnSalir);
	}
}
