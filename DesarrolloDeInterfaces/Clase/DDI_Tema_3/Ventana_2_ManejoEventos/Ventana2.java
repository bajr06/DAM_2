package Ventana_2_ManejoEventos;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ventana2 {
	private JFrame frame;
	private JPanel panel;
	private JButton boton;
	private JButton boton_1;
	private int contador = 0;
	private JTextField campoTexto;
	private JLabel etiqueta;

	private JPanel panel_1;
	private JButton boton_2;
	private JButton boton_3;

	private JPanel panel_2;
	private JLabel etiqueta_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana2 window = new Ventana2();
					window.frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventana2() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(1920 / 2 - 225, 1080 / 2 - 150, 450, 300); // Tamaño y posicionamiento que queremos que tenga.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// CardLayout: lo que haces es que veamos las ventanas
		// superpuestas una encima de otra como si fueran cartas,
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(new Color(64, 0, 64));
		panel.setForeground(new Color(0, 128, 0));
		panel.setLayout(null);
		frame.getContentPane().add(panel, "name_11849613275200");

		campoTexto = new JTextField();
		campoTexto.setEditable(false);
		campoTexto.setBounds(240, 115, 86, 20);
		campoTexto.setColumns(10);
		panel.add(campoTexto);

		boton = new JButton("Click");
		boton.setBounds(95, 112, 89, 23);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador++;

				// Hacemos un casting para que pase de int a String
				campoTexto.setText(Integer.toString(contador));
			}
		});
		panel.add(boton);

		etiqueta = new JLabel("Número de clicks");
		etiqueta.setForeground(new Color(255, 255, 255));
		etiqueta.setBackground(new Color(255, 128, 128));
		etiqueta.setBounds(237, 73, 89, 14);
		panel.add(etiqueta);

		boton_1 = new JButton("Next");
		boton_1.setBounds(335, 227, 89, 23);
		boton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		panel.add(boton_1);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setForeground(new Color(64, 0, 128));
		panel_1.setLayout(null);
		frame.getContentPane().add(panel_1, "name_11901265226500");

		boton_2 = new JButton("Return");
		boton_2.setBounds(10, 227, 89, 23);
		boton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel.setVisible(true);
			}
		});
		panel_1.add(boton_2);

		boton_3 = new JButton("Finish");
		boton_3.setBounds(335, 227, 89, 23);
		boton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		panel_1.add(boton_3);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 0));
		panel_2.setLayout(null);
		frame.getContentPane().add(panel_2, "name_12093207752000");

		etiqueta_1 = new JLabel("GRACIAS");
		etiqueta_1.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		etiqueta_1.setBounds(101, 76, 234, 103);
		panel_2.add(etiqueta_1);
	}
}
