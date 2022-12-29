package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;

import BD.BD;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;


//en esta ventana vamos a lanzar un hilo mostrando todos los pedidos 

public class Ventana_productosdisponibles extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Pedidos> p=new ArrayList<>();
	
	private JPanel arriba;
	private JPanel pnl_btn;
	
	private JButton anterior;
	

	public Ventana_productosdisponibles(ArrayList<Pedidos> p) throws HeadlessException {
		super();
		this.p = p; 
	}
	private JPanel panelprincipal ;
	private BD bd=new BD();
	private JLabelAjustado foto = new JLabelAjustado( null );
	public void inizializarventana() {
		pnl_btn = new JPanel();
		panelprincipal=new JPanel();
		
		anterior = new JButton("<");
		pnl_btn.add(anterior);
		anterior.setForeground(Color.BLACK);
		anterior.setBackground(Color.white);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		anterior.setBorder(compound);
		
		this.add(pnl_btn);
		
		anterior.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_Cliente vc = new Ventana_Cliente();
				vc.setVisible(true);
				setVisible(false);
				vc.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);

				
			}
			
		});
		
		//Aqui es donde pondremos el hilo: 
		Thread hiloActual = new Thread() {  // ...porque solo lo usamos aquí
			public void run() {
				for (Pedidos prod:p) {
					for(Producto prod2:prod.getLista_pedidos()) {
						String url=bd.getURLFOTO(prod2);
						
						foto.setPreferredSize( new Dimension( 200, 200 ) );
						ImageIcon imagen = new ImageIcon( url );
						foto.setImagen( imagen );
						panelprincipal.add(foto);
//						foto.repaint();
						try {
							Thread.sleep( 10 );
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					for(Producto prod2:prod.getLista_pedidos()) {
						String url=bd.getURLFOTO(prod2);
						
						foto.setPreferredSize( new Dimension( 200, 200 ) );
						ImageIcon imagen = new ImageIcon( url );
						foto.setImagen( imagen );
						panelprincipal.remove(foto);;
//						foto.repaint();
						
					}
					
				}
				
				
			}
			
		};hiloActual.start();
		
		this.add(panelprincipal);
		
		ImageIcon icono = new ImageIcon("C:\\Users\\anetx\\git\\Prog-3-proyecto\\ProyectoProg3\\Fotos\\deustoOutlet.jpg.png");
		this.setIconImage(icono.getImage());	
		
	}
	private static class JLabelAjustado extends JLabel {
		private ImageIcon imagen; 
		private int tamanodex;
		private int tamanodey;
		public JLabelAjustado( ImageIcon imagen ) {
			setImagen( imagen );
		}
		public void setImagen( ImageIcon imagen ) {
			this.imagen = imagen;
			if (imagen==null) {
				tamanodex = 0;
				tamanodey = 0;
			} else {
				this.tamanodex = imagen.getIconWidth();
				this.tamanodey = imagen.getIconHeight();
			}
		}
		
	
	}
}
