package Ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana_IS extends JFrame{

	/**@author aiitz
	 * Esta ventana es la que vamos a utilizar para el inicio y registro de sesion
	 * Cuando le demos a registro automaticamente se pondran visibles unos objetos que antes no lo estaban.
	 * Al darle a inicio de sesion entraremos automaticamente en la aplicacion. 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private JTextField usuario;
	private JTextField contrasena;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField telefono;
	private JTextField correo;
	
	
	private JLabel usuariol;
	private JLabel contrasenal;
	private JLabel emaill;
	private JLabel nombrel;
	private JLabel apellidol;
	private JLabel telefonol;
	
	private JPanel pnusuario;
	private JPanel pncontrasena;
	private JPanel pncorreo;
	private JPanel pntelefono;
	private JPanel pnombre;
	private JPanel pnapellido;
	private JPanel abajo;
	
	private JButton registro;
	private JButton btusuario_contrasena;
	
	private JPanel panel_arriba;
	private JLabel panel_arribal;
	private JButton anterior;
	
	private JPanel panel_general;
	public Ventana_IS() {
		inizializar();
	}
	public void inizializar() {
		/**@author aiitz
		 * primero podemos ver todos los Jtext  field de los cueles usuario y contrasela unicamente pertenecen a el inicio de sesion 
		 * Al registro pertenecen todos ellos
		 */
		usuario=new JTextField("",16);
		contrasena=new JTextField("",16);
		nombre=new JTextField("",16);
		apellido=new JTextField("",16);
		telefono=new JTextField("",16);
		correo=new JTextField("",16);
		
		/**@author aiitz
		 * Paneles-> los que vamos a utilizar para el unicio de sesion o para registrarse y los que vamos a utilizar para poner los botones de inicio y registro 
		 */
		pnusuario=new JPanel();
		pncontrasena=new JPanel();
		pnombre=new JPanel();
		pnapellido=new JPanel();
		pncorreo=new JPanel();
		pntelefono=new JPanel();

		abajo=new JPanel();
		panel_general=new JPanel();
		
		/**@author aiitz
		 *  para indicar que hay que poner en cada textfield
		 */
		usuariol=new JLabel("Usuario");
		contrasenal=new JLabel("Contraseña");
		nombrel=new JLabel("Nombre");
		apellidol=new JLabel("Apellido");
		telefonol=new JLabel("Telefono");
		emaill=new JLabel("Correo");
		
		
		/**@author aiitz
		 * Boton de iniciodesesion-> con esto en caso de que el ususario este registrado inicia sesion automaticamente. 
		 */
		btusuario_contrasena=new JButton("Inicio de sesion");
		registro=new JButton("Registro");
		
		/**@author aiitz
		 * Los que tienen que ver con el panel superior
		 * este panel va a servirnos para ir atras y para ver el nombre de la ventana en la que nos encontramos
		 */
		panel_arriba=new JPanel();
		panel_arribal=new JLabel("Iniciar Sesion");
		anterior=new JButton("<");
		
//---------------------------------------------------------------------------------------------------------------------------------------------		
		/**@author aiitz
		 * Mediante este boton iniciaremos sesion para eso tendremos que comprobar que se trata de unos datos correctos
		 */
		btusuario_contrasena.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		/**@author aiitz
		 * Mediante este boton nos registraremos para eso tendremos que comprobar que se trata de unos datos correctos.
		 */
		
		registro.addActionListener((ActionListener)new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				pnombre.setVisible(true);
				pnapellido.setVisible(true);
				pncorreo.setVisible(true);
				pntelefono.setVisible(true);
				btusuario_contrasena.setVisible(false);
				anterior.setVisible(true);
				panel_arribal.setText("Registro");
			}
			
		});
		
		anterior.addActionListener((ActionListener)new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnombre.setVisible(false);
				pnapellido.setVisible(false);
				pncorreo.setVisible(false);
				pntelefono.setVisible(false);
				btusuario_contrasena.setVisible(true);
				anterior.setVisible(false);
				panel_arribal.setText("Iniciar Sesion");
				
			}
			
		});
//----------------------------------------------------------------------------------------------------------------------------------------------		
        this.addWindowListener(new WindowAdapter() {
			
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				System.exit(0);
				
			}
		});
//--------------------------------------------------------------------------------------------------------------------------------------------		
		
        panel_general.setLayout(new GridLayout(8,1));
		abajo.setLayout(new GridLayout(2,1));
        abajo.add(btusuario_contrasena);
		abajo.add(registro);
		panel_arriba.add(anterior);
		panel_arriba.add(panel_arribal);
		
		panel_general.add(panel_arriba);
		pncontrasena.add(contrasena);
		pncontrasena.add(contrasenal);
		pnusuario.add(usuario);
		pnusuario.add(usuariol);
		panel_general.add(pnusuario);
		pncorreo.add(correo);
		pncorreo.add(emaill);
		pnombre.add(nombre);
		pnombre.add(nombrel);
		pntelefono.add(telefono);
		pntelefono.add(telefonol);
		pnapellido.add(apellido);
		pnapellido.add(apellidol);
		panel_general.add(pnombre);
		panel_general.add(pnapellido);
		panel_general.add(pncorreo);
		panel_general.add(pntelefono);
		pnombre.setVisible(false);
		pnapellido.setVisible(false);
		pncorreo.setVisible(false);
		pntelefono.setVisible(false);
		panel_general.add(pncontrasena);
		panel_general.add(abajo);
		this.setSize(500,700);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.add(panel_general);
		Color color2= new Color(242,235,243);
		pnusuario.setBackground(color2);
		pnombre.setBackground(color2);
		pncontrasena.setBackground(color2);
		pncorreo.setBackground(color2);
		pnapellido.setBackground(color2);
		pntelefono.setBackground(color2);
		Color color1= new Color(243,242,235);
		abajo.setBackground(color1);
		panel_general.setBackground(color2);
		
		panel_arriba.setBackground(color1);
		anterior.setVisible(false);
	}
	public static void main(String[] args) {
		Ventana_IS vs =new Ventana_IS();
	}
}


