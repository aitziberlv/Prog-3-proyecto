package Ventanas;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	public Ventana_productosdisponibles(ArrayList<Pedidos> p) throws HeadlessException {
		super();
		this.p = p; 
	}
	private JPanel panelprincipal ;
	private BD bd=new BD();
	private JLabelAjustado foto = new JLabelAjustado( null );
	public void inizializarventana() {
		panelprincipal=new JPanel();
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
