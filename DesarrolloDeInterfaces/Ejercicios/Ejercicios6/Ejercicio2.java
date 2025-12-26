package Ejercicios6;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Ejercicio2 {
	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Timer tiempo; // Temporizador que gestiona varios hilos a la vez, disparando un evento cada cierto tiempo (configurable por nosotros). NO ES UN DELAY.
	private int i = 10;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio2 window = new Ejercicio2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ejercicio2() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblNewLabel = new JLabel("¡FELIZ PUENTE!");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 255, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(121, 37, 195, 45);
		lblNewLabel.setVisible(false);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(209, 120, 52, 39);
		frame.getContentPane().add(lblNewLabel_1);

		btnNewButton = new JButton("INICIAR");
		btnNewButton.setBounds(52, 120, 89, 45);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tiempo.start();
				lblNewLabel_1.setText(String.valueOf(i));
			}
		});
		frame.getContentPane().add(btnNewButton);

		btnNewButton_1 = new JButton("SALIR");
		btnNewButton_1.setBounds(335, 227, 89, 23);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();;
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnNewButton_1);

		tiempo = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				lblNewLabel_1.setText(String.valueOf(i));

				if(i == 0) {
					tiempo.stop();
					lblNewLabel.setVisible(true);
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(true);
				}
			}
		});
	}
}
