package com.sofka.challenge.concurso.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sofka.challenge.concurso.controller.Juego;
import com.sofka.challenge.concurso.vo.Participante;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * metodo que crea una ventana donde se muestras los participantes de juego en
 * una tabla con sus respsctivos puntajes
 * 
 * @author JaviierCarrillo
 *
 */
public class VentanaResultados extends JFrame {

	private Juego juego;
	private JPanel contentPane;
	private JTable tableParticipantes;
	private JButton btnSalir;
	private JLabel lblTitulo;
	private DefaultTableModel model;
	private JButton btnEliminar;

	/**
	 * Create the frame.
	 */
	public VentanaResultados(Juego juego) {
		this.juego = juego;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 44, 342, 187);
		contentPane.add(scrollPane);

		tableParticipantes = new JTable();
		model = new DefaultTableModel();
		tableParticipantes.setModel(model);
		model.addColumn("NOMBRE");
		model.addColumn("PUNTAJE");

		tableParticipantes.getColumnModel().getColumn(1).setPreferredWidth(15);
		scrollPane.setViewportView(tableParticipantes);

		btnSalir = new JButton("VOLVER");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenu menu = new VentanaMenu(juego);
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnSalir.setBounds(219, 242, 158, 23);
		contentPane.add(btnSalir);

		lblTitulo = new JLabel("HISTORIAL DE PARTICIPANTES");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(35, 11, 342, 22);
		contentPane.add(lblTitulo);

		btnEliminar = new JButton("ELIMINAR REGISTRO");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pin = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el PIN del jugador a borrar"));
					Participante participante = juego.encontrarParticipante(pin);
					if (participante != null) {
						juego.getParticipantes().remove(participante);
						juego.mostrarMensaje("Se borraron los datos de: " + participante.getNombre());
						llenarTabla();
					} else {
						juego.mostrarMensaje("No se pudo borrar el participante");
					}
				} catch (NumberFormatException ex) {
					juego.mostrarMensaje("El dato debe ser numérico");
				}
			}
		});
		btnEliminar.setBounds(35, 242, 163, 23);
		contentPane.add(btnEliminar);

		llenarTabla();
	}

	/**
	 * metodo que llema la tabla con los participantes registrados
	 */
	public void llenarTabla() {
		juego.ordenarParticipantes();
		ArrayList<Participante> participantes = juego.getParticipantes();
		model.setRowCount(0);
		model.setColumnCount(2);
		for (Participante participante : participantes) {
			Object[] fila = new Object[2];
			fila[0] = participante.getNombre();
			fila[1] = participante.getPuntaje();
			model.addRow(fila);
		}
	}
}
