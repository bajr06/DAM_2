package Ventana_1_Creacion;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

public class Ventana1 {
	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JRadioButtonMenuItem rbdtnmntmNewRadioItem;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana1 window = new Ventana1();
					window.frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventana1() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 0));
		frame.setBounds(100, 100, 480, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(99, 28, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(34, 23, 65, 31);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("Aceptar");
		btnNewButton.setToolTipText("Sans");
		btnNewButton.setBounds(354, 256, 89, 23);
		frame.getContentPane().add(btnNewButton);

		rbdtnmntmNewRadioItem = new JRadioButtonMenuItem("New Radio Item");
		rbdtnmntmNewRadioItem.setBounds(212, 253, 133, 26);
		frame.getContentPane().add(rbdtnmntmNewRadioItem);

		passwordField = new JPasswordField();
		passwordField.setBounds(241, 130, 31, 20);
		frame.getContentPane().add(passwordField);
	}
}
