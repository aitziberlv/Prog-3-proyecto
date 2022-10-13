package Ventanas;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean registro = false;
	//inicio sesion 
	private JTextField usuario;
	private JTextField contrasena;
	private JButton btusuario_contrasena;
	private JLabel usuariol;
	private JLabel contrasenal;
	private JPanel pnusuario;
	private JPanel pncontrasena;
	private JPanel abajo;
	
	
	//registro 
	public Ventana_IS() {
		inizializar();
	}
	public void inizializar() {
		usuario=new JTextField("",16);
		contrasena=new JTextField("",16);
		btusuario_contrasena=new JButton("Aceptar");
		pnusuario=new JPanel();
		pncontrasena=new JPanel();
		usuariol=new JLabel("Usuario");
		contrasenal=new JLabel("Contraseña");
		abajo=new JPanel();
		
//		btusuario_contrasena.addActionListener((ActionListener) new ActionListener() {

//			@Override
//			public void actionPerformed(ActionEvent e) {
//				lg.lectura("Propietarios.dat");
//				if (lg.iniciar_sesion2(usuario.getText(),contrasena.getText())==true){
//					Inicio_musica im=new Inicio_musica();
//					
//				}else {
//					ventana_Rg vr=new ventana_Rg();
//				}
//						
//			}		
//		});
//		
//        this.addWindowListener(new WindowAdapter() {
//			
//			public void windowClosed(WindowEvent e) {
//				super.windowClosed(e);
//				System.exit(0);
//				
//			}
//		});
		
		abajo.add(btusuario_contrasena);
		pncontrasena.add(contrasena);
		pncontrasena.add(contrasenal);
		pnusuario.add(usuario);
		pnusuario.add(usuariol);
		this.setLayout(new GridLayout(3,1));
		this.add(pnusuario);
		this.add(pncontrasena);
		this.add(abajo);
		this.setSize(300,200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	public static void main(String[] args) {
		Ventana_IS vs =new Ventana_IS();
	}
}


