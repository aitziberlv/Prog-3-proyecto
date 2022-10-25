package Ventanas;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


	public class Ventana_Registro extends JFrame{
		
		private static final long serialVersionUID = 1L;
		private boolean registro = false;
		//Definir 
		private JPanel center;
		private JPanel logo;
		private JPanel botonera;
		
		private JLabel usuario;
		private JLabel contrasena;
		
		private JTextField textUsuario;
		private JTextField textContrasena;
		
		private JButton entrar;
		private JButton salir;
		private JButton registrarse;
		
		//Oculto 
		private JPanel datosPersonales;
		
		private JLabel nombre;
		private JLabel contrasenaR;
		private JLabel apellido;
		private JLabel direccion;
		private JLabel gmail;
		
		//private JLabel cliente;
		private JLabel dni;
		private JLabel numeroTarjeta;

		
		private JTextField textNombre;
		private JTextField textContra;
		private JTextField textApellido;
		private JTextField textDireccion;
		private JTextField textGmail;
		
		private JTextField textDni;
		private JTextField textNumeroTarjeta;
		
		
		public Ventana_Registro() {
			
			
			//Definir tamano ventana
			setSize(400, 400);
			getContentPane().setLayout(new GridLayout(3,1));
			Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		      int height = pantalla.height;
		      int width = pantalla.width;
		      setSize(width/2, height/2);		

		      setLocationRelativeTo(null);
		     
		    //Inicializar ventana
		   center = new JPanel();
		   center.setLayout(new GridLayout(3,2));
		   datosPersonales = new JPanel();
		   datosPersonales.setLayout(new GridLayout(9,2));
		   logo = new JPanel();
		   botonera = new JPanel();
		   
		   usuario = new JLabel("Usuario: ");
		   contrasena = new JLabel("Contrase�a: ");
		   
		   textUsuario = new JTextField(25);
		   textContrasena = new JTextField(25);
		   entrar = new JButton("Entrar");
		   salir = new JButton("Salir");
		   registrarse = new JButton("Registrarse");
		   
		     
		}
		
}
