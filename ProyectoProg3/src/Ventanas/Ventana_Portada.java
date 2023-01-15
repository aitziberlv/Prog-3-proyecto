package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Ventanasexternas.FondoSwing;

public class Ventana_Portada extends JFrame{

	/**
	 * ESTA VENTANA SE TRATA DE LA PORTADA, LO PRIMERO QUE SE VA A VER AL ENTRAR EN LA APLICACION
	 */
	private static final long serialVersionUID = 1L;

	private JPanel titulo;
	private JPanel abajo;
	
	private JLabel lblTitulo;
	
	private JButton comenzar;
	
	public Ventana_Portada() {
		inicializarVentana();
	}

	private void inicializarVentana() {
		
		this.setSize(900, 700);
		setLocationRelativeTo(null);
		this.setTitle("DEUSTO OUTLET");
		this.setLayout(new BorderLayout());
		
		titulo = new JPanel();
		abajo = new JPanel();
		
		lblTitulo = new JLabel("BIENVENID@ A ");
		lblTitulo.setFont(new Font("Serif", Font.PLAIN, 50));
		
	//	ImageIcon imagen = new ImageIcon(getClass().getResource("Imagenes/dibujo.jpg"));
		
		comenzar = new JButton("Comenzar");
		comenzar.setForeground(Color.black);
		comenzar.setBackground(Color.white);
		
		comenzar.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_IS vi = new Ventana_IS();
				vi.setVisible(true);
				setVisible(false);
				vi.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
				
			}
			
		});
		
		
		//titulo.add(lblTitulo);
		
		//abajo.add(comenzar);
		
		this.add(lblTitulo,BorderLayout.NORTH);
		this.add(comenzar,BorderLayout.SOUTH);
		
		ImageIcon icono = new ImageIcon("FotosTiendas/deustoOutlet.jpg.png");
		this.setIconImage(icono.getImage());	
		try {
	        FondoSwing fondo = new FondoSwing(ImageIO.read(new File("FotosTiendas/deustoOutlet.jpg.png")));
	        JPanel panel = (JPanel) this.getContentPane();
	        panel.setBorder(fondo);
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
		
	}
	
	
	
	
	public static void main(String[] args) {
		Ventana_Portada vpo =new Ventana_Portada();
		vpo.setVisible(true);
		vpo.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
		
	}
	

}

