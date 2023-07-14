package com.alura.conversor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MarcoPrincipal extends JFrame {

	public MarcoPrincipal() {
		this.setTitle("Alura Challenge 2");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		// this.getSource().getParent().setBackground(backgroundColor);
		Image icono = Toolkit.getDefaultToolkit().getImage("icono.ico");
		this.setIconImage(icono);

		// Creamos y agregamos la etiqueta del titulo para la aplicacion
		JLabel header = new JLabel("Conversor", JLabel.CENTER);
		header.setFont(new Font("Serif", Font.BOLD, 34));
		header.setForeground(Color.white);
		
		add(header, BorderLayout.NORTH);

		// Creamos el contenedor de pestañas
		JTabbedPane panelPestagnas = new JTabbedPane();

		// Creacion de paneles que iran dentro de las pestañas
		InterfazPestagna CDivisas = new InterfazPestagna("Vamos a convertir Divisas", "Monto");
		InterfazPestagna CVolumen = new InterfazPestagna("Vamos a convertir Volumen", "Valor");
		InterfazPestagna CTemp = new InterfazPestagna("Vamos a convertir Temperatura", "Grados");

		// Agregando los paneles a las pestañas y colocando descripcion
		panelPestagnas.addTab("Divisas", CDivisas);
		panelPestagnas.addTab("Temperatura", CTemp);
		// panelPestagnas.addTab("Volumen", CVolumen);

		// Agregamos un listener que cambie los valores de los JComboBox
		// cuando cambiemos de pestaña
		panelPestagnas.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (panelPestagnas.getSelectedComponent() == CDivisas) {
					InterfazPestagna.pestagnaActual = "Divisas";
					CDivisas.reset();
					CDivisas.setComboOrigen();
					// InterfazPestagna.setComboOrigen();
				} else if (panelPestagnas.getSelectedComponent() == CTemp) {
					InterfazPestagna.pestagnaActual = "Temperatura";
					CTemp.reset();
					CTemp.setComboOrigen();
					// InterfazPestagna.setComboOrigen();
				} else if (panelPestagnas.getSelectedComponent() == CVolumen) {
					InterfazPestagna.pestagnaActual = "Volumen";
					System.out.println("Volumen");
					CDivisas.reset();
				}
			}
		});
		// Agregamos el panel a nuestro marco y definimos su posicion
		getContentPane().add(panelPestagnas, BorderLayout.CENTER);
		this.getContentPane().setBackground(new Color(24, 24, 153));
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			    int confirmed = JOptionPane.showConfirmDialog(null, 
			        "Estas seguro de querer salir del programa?", "Saliendo del programa",
			        JOptionPane.YES_NO_OPTION);

			    if (confirmed == JOptionPane.YES_OPTION) {
			      dispose();
			    }
			  }
		});
		
	}
}
