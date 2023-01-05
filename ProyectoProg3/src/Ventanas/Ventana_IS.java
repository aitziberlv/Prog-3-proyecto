package Ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BD.BD;
import Clasesprincipales.Usuario;
import Logica.Logica;
import Ventanasexternas.FondoSwing;

public class Ventana_IS extends JFrame{

	/**
	 * Esta ventana es la que vamos a utilizar para el inicio y registro de sesion
	 * Cuando le demos a registro automaticamente se pondran visibles unos objetos que antes no lo estaban.
	 * Al darle a inicio de sesion entraremos automaticamente en la aplicacion. 
	 */
	
	private static final long serialVersionUID = 1L;
	Logica lg=new Logica();
	
	public static JTextField usuario;
	private JPasswordField contrasena;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField telefono;
	public static JTextField dni;
	private JTextField fechaNa;
	private JTextField direccion;
	
	private JLabel usuariol;
	private JLabel contrasenal;
	private JLabel dnil;
	private JLabel nombrel;
	private JLabel apellidol;
	private JLabel telefonol;
	private JLabel fechaNal;
	private JLabel direccionl;
	
	private JPanel pnusuario;
	private JPanel pncontrasena;
	private JPanel pndni;
	private JPanel pntelefono;
	private JPanel pnombre;
	private JPanel pnapellido;
	private JPanel pnfecha;
	private JPanel pndireccion;
	private JPanel abajo;
	
	private JButton registro;
	private JButton btusuario_contrasena;
	
	private JPanel panel_arriba;
	private JLabel panel_arribal;
	private JButton anterior;
	
	private JPanel panel_general;
	
	private boolean registroB = false;
	
	public Ventana_IS() {
		inicializar();
	}
	
	
	public void inicializar() {
		
		//Inicializamos componentes:
		
		/**
		 * primero podemos ver todos los Jtext  field de los cueles usuario y contrasela unicamente pertenecen a el inicio de sesion 
		 * Al registro pertenecen todos ellos
		 */
		
		this.setTitle("DEUSTO OUTLET INICIAR SESIÓN");
		this.setSize(900,700);
		this.setLocationRelativeTo(null); //centrar la ventana.
		
		
		usuario=new JTextField("",16);
		contrasena=new JPasswordField("",16);
		nombre=new JTextField("",16);
		apellido=new JTextField("",16);
		telefono=new JTextField("",16);
		dni=new JTextField("",16);
		//String dni = "\\d{8}";
		fechaNa=new JTextField("",16);
		//fechaNa = new HintTextField ("dd/mm/yyyy");
		//String fechaNa = "\\d{1,2}/\\d{1,2}/\\d{4}"
		direccion=new JTextField("",16);
		
		
		/**
		 * Paneles-> los que vamos a utilizar para el unicio de sesion o para registrarse y los que vamos a utilizar para poner los botones de inicio y registro 
		 */
		pnusuario=new JPanel();
		pncontrasena=new JPanel();
		pnombre=new JPanel();
		pnapellido=new JPanel();
		pndni=new JPanel();
		pntelefono=new JPanel();
		pnfecha = new JPanel();
		pndireccion = new JPanel();

		abajo=new JPanel();
		panel_general=new JPanel();
		
		/**
		 *  para indicar que hay que poner en cada textfield
		 */
		usuariol=new JLabel("Usuario");
		contrasenal=new JLabel("Contraseña");
		nombrel=new JLabel("Nombre");
		apellidol=new JLabel("Apellido");
		telefonol=new JLabel("Telefono");
		dnil=new JLabel("Dni");
		fechaNal=new JLabel("Fecha de nacimiento (DD/MM/AAAA)");
		direccionl=new JLabel("Direccion");		
		
		/**
		 * Boton de iniciodesesion-> con esto en caso de que el ususario este registrado inicia sesion automaticamente. 
		 */
		btusuario_contrasena=new JButton("Iniciar sesión");
		btusuario_contrasena.setForeground(Color.black);
		btusuario_contrasena.setBackground(Color.white);
		
		registro=new JButton("Registrarse");
		registro.setForeground(Color.black);
		registro.setBackground(Color.white);
		
		
		/**
		 * Los que tienen que ver con el panel superior
		 * este panel va a servirnos para ir atras y para ver el nombre de la ventana en la que nos encontramos
		 */
		panel_arriba=new JPanel();
		panel_arribal=new JLabel("Iniciar Sesion");
		anterior=new JButton("<");
		
//---------------------------------------------------------------------------------------------------------------------------------------------		
		/**
		 * Mediante este boton iniciaremos sesion para eso tendremos que comprobar que se trata de unos datos correctos
		 */
		btusuario_contrasena.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana_Cliente vc = new Ventana_Cliente(usuario.getText());
				if(BD.buscarUsuarioNombre(usuario.getText()) == null) {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado.","Error",JOptionPane.ERROR_MESSAGE);

					vc.setVisible(false);
				}
				else {
					if(BD.buscarUsuarioNombre(usuario.getText()).getContraseña().equals(contrasena.getText())) {
						setVisible(false);
						vc.setVisible(true);
						vc.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);

					}
					else {
						JOptionPane.showMessageDialog(null, "Contaseña incorrecta. Inserte de nuevo la contraseña.","Error",JOptionPane.ERROR_MESSAGE);
						vc.setVisible(false);
						vc.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);

					}

				}				
				
//				lg.lectura("Usuarios.dat");
//				if(lg.iniciar_sesion(usuario.getText(), contrasena.getText())) {
//					vc.setVisible(true);
//					setVisible(false);
//					
//				}else {
//					JOptionPane.showMessageDialog(null, "Usuario no encontrado.","Error",JOptionPane.ERROR_MESSAGE);
//				}	
				
			}
			
		});
		
		/**
		 * Mediante este boton nos registraremos para eso tendremos que comprobar que se trata de unos datos correctos.
		 */
		
		registro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!registroB) {
				
				pnombre.setVisible(true);
				pnapellido.setVisible(true);
				pndni.setVisible(true);
				pntelefono.setVisible(true);
				pnfecha.setVisible(true);
				pndireccion.setVisible(true);
				btusuario_contrasena.setVisible(false);
				anterior.setVisible(true);
				panel_arribal.setText("Registro");
				
				registroB = true;
				}else  {
					if(usuario.getText().length() == 0 || contrasena.getText().length() == 0 || nombre.getText().length() == 0 || dni.getText().length() == 0 || fechaNa.getText().length() == 0 || telefono.getText().length() == 0 || direccion.getText().length() == 0 || apellido.getText().length() == 0 ) {
						JOptionPane.showMessageDialog(null, "Algun campo no ha sido introducido. Por favor rellene todos los datos","Error",JOptionPane.ERROR_MESSAGE);
					}else {
						if(BD.buscarUsuarioNombre(usuario.getText()) != null) {
							JOptionPane.showMessageDialog(null, "Usuario existente. Inserte otro nombre de usuario","Error",JOptionPane.ERROR_MESSAGE);
						}
						else {
							Usuario u = new Usuario(nombre.getText(), dni.getText(), fechaNa.getText(), telefono.getText(), direccion.getText(), apellido.getText(), contrasena.getText(), usuario.getText());
							BD.InsertarUsuario(u);
							setVisible(false);
							JOptionPane.showMessageDialog(null, "Usuario insertado correctamente. Ya puede iniciar sesión.");
							Ventana_IS vi = new Ventana_IS();
							vi.setVisible(true);
							vi.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
						}
					}
					
					
					
//					if(lg.registrarte(usuario.getText())) {
//						if (usuario.getText()!=" ") {
//							lg.registrar(usuario.getText(),dni.getText(),nombre.getText(),apellido.getText(),telefono.getText(),contrasena.getText());
//							vc.setVisible(true);
//							setVisible(false);
//						}else {
//							JOptionPane.showMessageDialog(null, "Nombre de usuario vacio.","Error",JOptionPane.ERROR_MESSAGE);
//						}
//						
//						
//					}else {
//						JOptionPane.showMessageDialog(null, "Usuario existente.","Error",JOptionPane.ERROR_MESSAGE);
//					}	
					
				}			
				
			}
			
		});
		
		anterior.addActionListener((ActionListener)new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnombre.setVisible(false);
				pnapellido.setVisible(false);
				pndni.setVisible(false);
				pntelefono.setVisible(false);
				pnfecha.setVisible(false);
				pndireccion.setVisible(false);
				btusuario_contrasena.setVisible(true);
				anterior.setVisible(false);
				panel_arribal.setText("Iniciar Sesion");
				registroB=false;
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
		
        panel_general.setLayout(new GridLayout(10,1));
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
		panel_general.add(pncontrasena);
		pndni.add(dni);
		pndni.add(dnil);
		pnombre.add(nombre);
		pnombre.add(nombrel);
		pntelefono.add(telefono);
		pntelefono.add(telefonol);
		pnapellido.add(apellido);
		pnapellido.add(apellidol);
		pnfecha.add(fechaNa);
		pnfecha.add(fechaNal);
		pndireccion.add(direccion);
		pndireccion.add(direccionl);
		panel_general.add(pnombre);
		panel_general.add(pnapellido);
		panel_general.add(pndni);
		panel_general.add(pntelefono);
		panel_general.add(pnfecha);
		panel_general.add(pndireccion);
		pnombre.setVisible(false);
		pnapellido.setVisible(false);
		pndni.setVisible(false);
		pntelefono.setVisible(false);
		pnfecha.setVisible(false);
		pndireccion.setVisible(false);
		panel_general.add(abajo);
		this.setSize(500,700);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.add(panel_general);
		Color color2= new Color(242,235,243);
		pnusuario.setBackground(Color.white);
		pnombre.setBackground(Color.white);
		pncontrasena.setBackground(Color.white);
		pndni.setBackground(Color.white);
		pnapellido.setBackground(Color.white);
		pntelefono.setBackground(Color.white);
		pnfecha.setBackground(Color.white);
		pndireccion.setBackground(Color.white);
		Color color1= new Color(243,242,235);
		abajo.setBackground(color1);
		panel_general.setBackground(Color.white);
		abajo.setBackground(Color.white);
		panel_arriba.setBackground(color1);
		anterior.setVisible(false);
		
		ImageIcon icono = new ImageIcon("FotosTiendas/deustoOutlet.jpg.png");
		this.setIconImage(icono.getImage());	
		
		try {
	        FondoSwing fondo = new FondoSwing(ImageIO.read(new File("FotosTiendas/do2.png")));
	       // JPanel panel = (JPanel) this.getContentPane();
	        panel_arriba.setBorder(fondo);
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	public static void main(String[] args) {
		Ventana_IS vs =new Ventana_IS();
		//vs.setSize(900,700);
		vs.setLocationRelativeTo(null);
		vs.setExtendedState(Ventana_IS.MAXIMIZED_BOTH);
	}
}


