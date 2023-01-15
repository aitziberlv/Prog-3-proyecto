package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BD.BD;
import Clasesprincipales.Usuario;

public class Ventana_Administrador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static List<Usuario> lUsuarios;
	private JPanel pnl_titulo;
	private JPanel pnl_centro; 
	private JPanel pnl_centro_iz;
	private JPanel pnl_centro_dcha;
	private JPanel pnl_abajo;
	
	private JLabel titulo;
	private JLabel estadistica1;
	private JLabel estadistica2;
	
	private JButton atras;
	
	public Ventana_Administrador () {
		inicializarVentana();
	}
	
	private void inicializarVentana () {
		this.setSize(900, 700);
		setLocationRelativeTo(null);
		this.setTitle("Administrador");
		this.setLayout(new GridLayout(3,1));
		
		pnl_titulo = new JPanel();
		pnl_centro = new JPanel();
		pnl_centro.setLayout(new GridLayout(1,2));
		pnl_centro_iz = new JPanel();
		pnl_centro_iz.setLayout(new GridLayout(1,2));
		pnl_centro_dcha = new JPanel();
		pnl_abajo = new JPanel();
		pnl_abajo.setLayout(new GridLayout(3,2));
		
		titulo = new JLabel("ADMINISTRADOR");
		estadistica1 = new JLabel("Usuario que mas ha comprado: ");
		estadistica2 = new JLabel("Usuario que menos ha comprado: ");
		
		atras = new JButton("<");
		atras.setForeground(Color.black);
		atras.setBackground(Color.white);
		atras.setPreferredSize(new Dimension(32,0));
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Font fuente = new Font("Arial", 1, 20);
	    titulo.setFont(fuente);
	    
		Image img= new ImageIcon("deustoOutlet.jpg.png").getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(140, 140, Image.SCALE_SMOOTH));
		titulo.setIcon(img2);
		
		pnl_titulo.add(titulo);
		pnl_centro_iz.add(estadistica1);
		pnl_centro_dcha.add(estadistica2);
		pnl_centro.add(pnl_centro_iz);
		pnl_centro.add(pnl_centro_dcha);
		pnl_abajo.add(atras);
		
		this.add(pnl_titulo);
		this.add(pnl_centro);
		this.add(pnl_abajo);
		
		lUsuarios = BD.getUsuario();
		
	}
	
	public static List<Usuario> UsarioMasComprar () {
		
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return lUsuarios;
		
	}
	
	public static List<Usuario> UsarioMenosComprar () {
		
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return lUsuarios;
		
	}
	
	public static void main(String[] args) {
		Ventana_Administrador va =new Ventana_Administrador();
		va.setVisible(true);
		va.setExtendedState(Ventana_Carrito.MAXIMIZED_BOTH);
		
	}
}
