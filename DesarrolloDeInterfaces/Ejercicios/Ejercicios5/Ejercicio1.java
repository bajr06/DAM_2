package Ejercicios5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Ejercicio1 {
	private JFrame frame;
	private JLayeredPane layeredPane;
	private JPanel panel_0;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton boton_0;
	private JButton boton_1;
	private JButton boton_2;
	private JButton boton_3;
	private JButton boton_4;
	private JButton boton_5;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio1 window = new Ejercicio1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ejercicio1() {
		initialize();
	}

	private void initialize() {
		Dimension monitor = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) monitor.getHeight();
		int width = (int) monitor.getWidth();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		// frame.setLocationRelativeTo(null); // Para que la pantalla esté centrada (referente al marco de la ventana).

		// (Resolucion/2) - (miVentana/2)
		frame.setLocation((width/2) - (frame.getWidth()/2), (height/2) - (frame.getHeight()/2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 434, 260);
		frame.getContentPane().add(layeredPane);

		panel_0 = new JPanel();
		panel_0.setLayout(null);
		panel_0.setBackground(new Color(255, 128, 64));
		panel_0.setBounds(0, 0, 434, 260);
		layeredPane.add(panel_0);

		boton_0 = new JButton("Cambiar a Verde");
		boton_0.setBounds(283, 226, 141, 23);
		boton_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_0.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		panel_0.add(boton_0);

		boton_1 = new JButton("Cambiar a Rojo");
		boton_1.setBounds(10, 226, 129, 23);
		boton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_0.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		panel_0.add(boton_1);


		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 0));
		panel_1.setBounds(0, 0, 434, 260);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		layeredPane.add(panel_1);

		boton_2 = new JButton("Cambiar a Rojo");
		boton_2.setBounds(296, 226, 128, 23);
		boton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		panel_1.add(boton_2);

		boton_3 = new JButton("Cambiar a Naranja");
		boton_3.setBounds(10, 226, 139, 23);
		boton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_0.setVisible(true);
			}
		});
		panel_1.add(boton_3);


		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 0));
		panel_2.setBounds(0, 0, 434, 260);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		layeredPane.add(panel_2);

		boton_4 = new JButton("Cambiar a Verde");
		boton_4.setBounds(10, 226, 127, 23);
		boton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		panel_2.add(boton_4);

		boton_5 = new JButton("Cambiar a Naranja");
		boton_5.setBounds(284, 226, 140, 23);
		boton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				panel_0.setVisible(true);
			}
		});
		panel_2.add(boton_5);
	}
}
