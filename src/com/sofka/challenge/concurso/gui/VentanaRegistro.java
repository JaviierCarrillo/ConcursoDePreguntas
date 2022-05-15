package com.sofka.challenge.concurso.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sofka.challenge.concurso.controller.Juego;
import com.sofka.challenge.concurso.vo.Participante;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * metodo que permite iniciar una ventana para que el usuario se registre en el
 * juego
 * 
 * @author JaviierCarrillo
 *
 */
public class VentanaRegistro extends JFrame {

	private Juego juego;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textPin;

	/**
	 * Create the frame.
	 */
	public VentanaRegistro(Juego juego) {

		this.juego = juego;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 281, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JLabel lblTituloRegistro = new JLabel("REGISTRATE");
		lblTituloRegistro.setForeground(Color.RED);
		lblTituloRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTituloRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloRegistro.setBounds(47, 28, 176, 22);
		contentPane.add(lblTituloRegistro);

		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(10, 61, 99, 22);
		contentPane.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBounds(10, 88, 147, 29);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblPin = new JLabel("<html><body>Elige un PIN de seguridad (solo dígitos)</body></html>");
		lblPin.setBounds(10, 140, 229, 29);
		contentPane.add(lblPin);

		textPin = new JTextField();
		textPin.setBounds(10, 173, 147, 29);
		contentPane.add(textPin);
		textPin.setColumns(10);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textNombre.getText().isEmpty() || textPin.getText().isEmpty()) {
					juego.mostrarMensaje("Por favor complete los campos del formulario");
				} else {
					String nombre = textNombre.getText();
					try {
						int pin = Integer.parseInt(textPin.getText());
						if (juego.encontrarParticipante(pin) == null) {
							juego.getParticipantes().add(new Participante(pin, nombre));
							juego.mostrarMensaje("Se agregó con éxito");
							textNombre.setText("");
							textPin.setText("");
						} else {
							juego.mostrarMensaje("El participante ya se encuentra agregado");
						}
					} catch (NumberFormatException ex) {
						juego.mostrarMensaje("El pin debe ser numérico");
						// ex.printStackTrace();
					}
				}
			}
		});
		btnGuardar.setBounds(10, 238, 119, 23);
		contentPane.add(btnGuardar);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenu menu = new VentanaMenu(juego);
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnSalir.setBounds(152, 238, 119, 23);
		contentPane.add(btnSalir);
	}
}
