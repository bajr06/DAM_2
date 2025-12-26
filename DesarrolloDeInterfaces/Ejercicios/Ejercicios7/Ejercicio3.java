package Ejercicios7;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ejercicio3 {
	private JFrame frame;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JProgressBar progressBar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private Timer tiempo;
	private int i = 10;
	private int j = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio3 window = new Ejercicio3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ejercicio3() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 1000, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("INICIAR");
		btnNewButton.setBounds(44, 44, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tiempo.start();
				lblNewLabel.setText(String.valueOf(i));
				lblNewLabel_1.setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewButton);

		progressBar = new JProgressBar();
		progressBar.setBounds(100, 140, 240, 31);
		progressBar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(progressBar.getValue() ==  100) {
					String msg = "¡ENHORABUENA!\n Vamos que no queda nada...\n ¡ÁNIMO!";

					JOptionPane.showMessageDialog(null, msg, "AL RECREO", 1);
				}
			}
		});
		frame.getContentPane().add(progressBar);

		btnNewButton_1 = new JButton("SALIR");
		btnNewButton_1.setBounds(335, 227, 89, 23);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnNewButton_1);

		lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(0, 255, 0));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(335, 45, 46, 46);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Cargando café...");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(110, 174, 104, 14);
		lblNewLabel_1.setVisible(false);
		frame.getContentPane().add(lblNewLabel_1);

		tiempo = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				lblNewLabel.setText(String.valueOf(i));

				j += 10;
				lblNewLabel_1.setLocation(110 + j, 174);

				if(i == 0) {
					tiempo.stop();
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(true);
					lblNewLabel_1.setText("¡TERMINADO!");
				}

				progressBar.setValue(j);
			}
		});
	}
}
