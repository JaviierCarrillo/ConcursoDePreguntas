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

	/**
	 * Create the frame.
	 */
	public VentanaResultados(Juego juego) {
		this.juego = juego;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 309);
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
		model.addColumn("Nombre");
		model.addColumn("Puntaje");

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
		btnSalir.setBounds(309, 242, 115, 23);
		contentPane.add(btnSalir);

		lblTitulo = new JLabel("HISTORIAL DE PARTICIPANTES");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(35, 11, 342, 22);
		contentPane.add(lblTitulo);

		llenarTabla();
	}

	/**
	 * metodo que llema la tabla con los participantes registrados
	 */
	public void llenarTabla() {
		ArrayList<Participante> participantes = juego.getParticipantes();
		for (Participante participante : participantes) {
			Object[] fila = new Object[2];
			fila[0] = participante.getNombre();
			fila[1] = participante.getPuntaje();
			model.addRow(fila);
		}
	}
}
