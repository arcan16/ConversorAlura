package com.alura.conversor.gui;

import java.math.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.alura.conversor.logica.*;

public class InterfazPestagna extends JPanel {
	public static String pestagnaActual = "Divisas";

	private JComboBox ComboOrigen;

	private JComboBox ComboDestino;

	private String ComboAnterior;

	private JLabel labelResultado = new JLabel("");
	private JTextField cajaResultado = new JTextField("");

	private static String[] divisas = { "MXN-Peso Mexicano", "USD-Dolares Americanos", "EUR-Euro",
			"GBP-Libra Esterlina", "YPJ-Yen Japonés", "KRW-Won Sur-Coreano" };

	private static String[] grados = { "Celcius", "Farenheit", "Kelvin" };

	private JTextField IngresoValor;

	private Image imagenConversion;
	private Image imagenGit;
	private Image imagenLinkedin;

	public void setComboAnterior(String Anterior) {
		ComboAnterior = Anterior;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			// Cargamos la imagen del conversor
			this.imagenConversion = ImageIO.read(new File("src/logo2.jpg"));
			this.imagenConversion = this.imagenConversion.getScaledInstance(50, 40, Image.SCALE_DEFAULT);

			// Cargamos la imagen del likendin
			this.imagenLinkedin = ImageIO.read(new File("src/linkedin.png"));
			this.imagenLinkedin = this.imagenLinkedin.getScaledInstance(40, 40, Image.SCALE_DEFAULT);

			// Cargamos la imagen del GitHub
			this.imagenGit = ImageIO.read(new File("src/github.png"));
			this.imagenGit = this.imagenGit.getScaledInstance(40, 40, Image.SCALE_DEFAULT);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("La imagen no se ha encontrado");
			e.printStackTrace();
		}
		// Colocamos las imagenees
		g.drawImage(this.imagenConversion, 330, 195, null);
		g.drawImage(this.imagenGit, 315, 325, null);
		g.drawImage(this.imagenLinkedin, 360, 325, null);
	}

	public InterfazPestagna(String titulo, String tCampo) {
		this.setLayout(null);
		// Colores de la aplicacion
		this.setBackground(Color.white);

		// Titulo Contenido de la pestaña
		JLabel TituloContenido = new JLabel(titulo);
		TituloContenido.setFont(new Font("Serif", Font.PLAIN, 24));
		TituloContenido.setSize(TituloContenido.getPreferredSize());
		TituloContenido.setLocation(10, 5);
		add(TituloContenido);

		// Etiqueta del campo
		JLabel TextoCampo = new JLabel(tCampo);
		TextoCampo.setFont(new Font("Serif", Font.PLAIN, 20));
		TextoCampo.setSize(TextoCampo.getPreferredSize());
		TextoCampo.setLocation(320, 60);
		add(TextoCampo);

		// Caja de texto para ingresar el monto
		IngresoValor = new JTextField("", 20);
		IngresoValor.setBounds(225, 95, 250, 30);
		add(IngresoValor);

		// Validacion
		Validacion validar = new Validacion();
		IngresoValor.addKeyListener(validar);

		// Etiqueta origen
		JLabel TextoOrigen = new JLabel("Origen:");
		TextoOrigen.setFont(new Font("Serif", Font.PLAIN, 20));
		TextoOrigen.setSize(TextoOrigen.getPreferredSize());
		TextoOrigen.setLocation(150, 170);
		add(TextoOrigen);

		// Etiqueta destino
		JLabel TextoDestino = new JLabel("Destino:");
		TextoDestino.setFont(new Font("Serif", Font.PLAIN, 20));
		TextoDestino.setSize(TextoDestino.getPreferredSize());
		TextoDestino.setLocation(480, 170);
		add(TextoDestino);

		// ComboBox de origen
		ComboOrigen = new JComboBox();
		ComboOrigen.setSize(200, 30);
		ComboOrigen.setLocation(90, 200);
		ComboOrigen.setBackground(new Color(158, 219, 255));
		add(ComboOrigen);

		// ComboBox de Destino
		ComboDestino = new JComboBox();
		ComboDestino.setSize(200, 30);
		ComboDestino.setLocation(420, 200);
		ComboDestino.setBackground(new Color(158, 255, 219));
		add(ComboDestino);

		JButton btnNewButton = new JButton("Restablecer");
		btnNewButton.setBounds(290, 350, 120, 25);
		// add(btnNewButton);

		// Label resultado
		labelResultado = new JLabel("Resultado");
		labelResultado.setFont(new Font("Serif", Font.PLAIN, 24));
		labelResultado.setSize(200, 50);
		labelResultado.setLocation(300, 240);
		add(labelResultado);

		// Caja resultado
		cajaResultado = new JTextField("");
		cajaResultado.setHorizontalAlignment(SwingConstants.CENTER);
		cajaResultado.setFont(new Font("Serif", Font.PLAIN, 24));
		cajaResultado.setSize(200, 30);
		cajaResultado.setEditable(false);
		cajaResultado.setLocation(245, 280);
		add(cajaResultado);

		// Etiqueta destino
		JLabel poweradeBy = new JLabel("Powerade by: Omar");
		poweradeBy.setFont(new Font("Serif", Font.PLAIN, 20));
		poweradeBy.setSize(poweradeBy.getPreferredSize());
		poweradeBy.setLocation(280, 365);
		add(poweradeBy);

		setComboOrigen();

		ComboOrigenSelect seleccionOrigen = new ComboOrigenSelect();
		ComboOrigen.addActionListener(seleccionOrigen);

		ComboDestinoSelect seleccionDestino = new ComboDestinoSelect();
		ComboDestino.addActionListener(seleccionDestino);

		ConversionIngreso ConvertListener = new ConversionIngreso();
		IngresoValor.getDocument().addDocumentListener(ConvertListener);
		
		
	}
	public void reset() {
		IngresoValor.setText("");
		cajaResultado.setText("");
		ComboDestino.removeAllItems();
	}
	public void setComboOrigen() {
		System.out.println(pestagnaActual);
		if (pestagnaActual == "Divisas") {
			ComboOrigen.removeAllItems();
			for (String divisa : divisas) {
				ComboOrigen.addItem(divisa);
				ComboDestino.addItem(divisa);
				ComboAnterior = (String) ComboOrigen.getSelectedItem();
				ComboDestino.removeItem(ComboOrigen.getSelectedItem());
				if(ComboDestino.getSelectedItem()==null)ComboDestino.removeItem(ComboDestino.getSelectedItem());
			}
		} else if (pestagnaActual == "Temperatura") {
			ComboOrigen.removeAllItems();
			ComboDestino.removeAllItems();
			for (String grado : grados) {
				ComboOrigen.addItem(grado);
				ComboDestino.addItem(grado);
				ComboAnterior = (String) ComboOrigen.getSelectedItem();
				ComboDestino.removeItem(ComboOrigen.getSelectedItem());
			}
			ComboDestino.removeItemAt(0);
		}
	}

	// Modifica el ComboDestino cuando el ComboOrigen es seleccionado
	public void setComboDestino() {
		if (pestagnaActual == "Divisas") {
			System.out.println("Seleccion anterior " + ComboAnterior);
			if (ComboAnterior != ComboOrigen.getSelectedItem()) {
				ComboDestino.removeItem(ComboOrigen.getSelectedItem());
				ComboDestino.addItem(ComboAnterior);
				ComboAnterior = (String) ComboOrigen.getSelectedItem();
			}
		} else if (pestagnaActual == "Temperatura") {
			System.out.println("Seleccion anterior " + ComboAnterior);
			if (ComboAnterior != ComboOrigen.getSelectedItem()) {
				ComboDestino.removeItem(ComboOrigen.getSelectedItem());
				ComboDestino.addItem(ComboAnterior);
				ComboAnterior = (String) ComboOrigen.getSelectedItem();
			}
		}

	}

	// Validacion para el ingreso de informacion en la caja de texto
	private class Validacion extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyChar();
			boolean numero = key >= 48 && key <= 57 || key == 46 || key == 10 || key == 8;
			int punto = 0;
			String valorIngresado = IngresoValor.getText().trim();
			// System.out.println(key);
			for (int i = 0; i < valorIngresado.length(); i++) {
				if (valorIngresado.charAt(i) == '.') {
					punto++;
				}
			}

			if (punto == 1 && e.getKeyChar() == '.') {
				e.consume();
			}

			if (!(numero)) {
				JOptionPane.showMessageDialog(null, "Solo se aceptan valores numericos", "Error",
						JOptionPane.ERROR_MESSAGE);
				e.consume();
			}
		}
	}

	// Listener para la seleccion de combo origen
	private class ComboOrigenSelect implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Conversor conversor = new Conversor();
			setComboDestino();
			if (IngresoValor.getText().length() > 0) {
				if (pestagnaActual == "Divisas") {
					setResultado(conversor.ConvertirDivisa((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				} else if (pestagnaActual == "Temperatura") {
					setResultado(conversor.ConvertirGrados((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				}
			}
		}
	}

	public void setResultado(String r) {
		System.out.println("Llegandoo");
		cajaResultado.setText(r);
	}

	public void setResultado(BigDecimal r) {
		System.out.println("Llegandoo");
		cajaResultado.setText(r.toString());
	}

	/**
	 * Controla las acciones a realizar al seleccionar el ComboDestino
	 * 
	 * @author arcan
	 *
	 */
	private class ComboDestinoSelect implements ActionListener {
		Conversor conversor = new Conversor();

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (IngresoValor.getText().length() > 0) {
				if (pestagnaActual == "Divisas") {
					setResultado(conversor.ConvertirDivisa((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				} else if (pestagnaActual == "Temperatura") {
					setResultado(conversor.ConvertirGrados((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				}
			}
		}
	}

	private class ConversionIngreso implements DocumentListener {
		private Conversor conversor = new Conversor();

		public void setResultado(String r) {
			System.out.println("Llegandoo");
			cajaResultado.setText(r);
		}

		public void setResultado(BigDecimal r) {
			System.out.println("Llegandoo");
			cajaResultado.setText(r.toString());
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			if (IngresoValor.getText().length() == 1 && IngresoValor.getText().charAt(0) == '.') {

			} else if (IngresoValor.getText().length() > 0) {
				if (pestagnaActual == "Divisas") {
					setResultado(conversor.ConvertirDivisa((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				} else if (pestagnaActual == "Temperatura") {
					setResultado(conversor.ConvertirGrados((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				}
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			if (IngresoValor.getText().length() == 1 && IngresoValor.getText().charAt(0) == '.') {

			} else if (IngresoValor.getText().length() > 0) {
				if (pestagnaActual == "Divisas") {
					setResultado(conversor.ConvertirDivisa((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				} else if (pestagnaActual == "Temperatura") {
					setResultado(conversor.ConvertirGrados((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				}
			}
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub

			if (IngresoValor.getText().length() == 1 && IngresoValor.getText().charAt(0) == '.') {

			} else if (IngresoValor.getText().length() > 0) {
				if (pestagnaActual == "Divisas") {
					setResultado(conversor.ConvertirDivisa((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				} else if (pestagnaActual == "Temperatura") {
					setResultado(conversor.ConvertirGrados((String) ComboOrigen.getSelectedItem(),
							(String) ComboDestino.getSelectedItem(), IngresoValor.getText()));
				}
			}
		}
	}
}
