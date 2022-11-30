package Ventanas;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana_Portada extends JFrame{

	/**
	 * 
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
		this.setLayout(new GridLayout(3,1));
		
		titulo = new JPanel();
		abajo = new JPanel();
		
		lblTitulo = new JLabel("BIENVENID@ A DEUSTO OUTLET");
		lblTitulo.setFont(new Font("Serif", Font.PLAIN, 50));
		
	//	ImageIcon imagen = new ImageIcon(getClass().getResource("Imagenes/dibujo.jpg"));
		
		comenzar = new JButton("Comenzar");
		
		comenzar.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_IS vi = new Ventana_IS();
				vi.setVisible(true);
				setVisible(false);
				
			}
			
		});
		
		
		titulo.add(lblTitulo);
		
		abajo.add(comenzar);
		
		this.add(titulo);
		this.add(abajo);
		
		ImageIcon icono = new ImageIcon("/ProyectoProg3/Fotos/strad.jpg");
		this.setIconImage(icono.getImage());	
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		Ventana_Portada vpo =new Ventana_Portada();
		vpo.setVisible(true);
		vpo.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
		
	}
	

}

