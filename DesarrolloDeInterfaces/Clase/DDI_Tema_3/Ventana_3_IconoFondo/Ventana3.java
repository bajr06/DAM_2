package Ventana_3_IconoFondo;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana3 {
	private JFrame frame;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana3 window = new Ventana3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventana3() {
		initialize();
	}

	private Component buscarImagen() {
		BufferedImage fondo = null;

		try {
			fondo = ImageIO.read(new File("DesarrolloDeInterfaces/Clase/DDI_Tema_3/Ventana_3_IconoFondo/Galio.jpg"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

		Image foto = fondo;
		JPanel panelConFondo = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(foto, 0, 0, 814, 589, null);
			}
		};

		return panelConFondo;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana3.class.getResource("Pantheon_Perfil.jpg")));
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		frame.getContentPane().add(buscarImagen());
	}
}
