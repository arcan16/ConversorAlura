package com.alura.conversor.gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InterfazConversor {

	public static void main(String[] args) {
		MarcoPrincipal raiz= new MarcoPrincipal();
		raiz.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Image icono= Toolkit.getDefaultToolkit().getImage("src/alura.png");
		raiz.setIconImage(icono);
		raiz.setResizable(false);
		raiz.setVisible(true);
	}

}
