package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;

import BD.BD;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;


//en esta ventana vamos a lanzar un hilo mostrando todos los pedidos 

public class Ventana_productosdisponibles extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Pedidos> p=new ArrayList<>();
	
	
//	paneles de la ventana
	private JPanel panelprincipal ;
	private JPanel pnl_btn;
	
//	boton
	private JButton anterior;
	private JButton mostrar_productos;
	
	private BD bd=new BD();
	private JLabelAjustado foto = new JLabelAjustado( null );
	
	private JTextField num;

	public Ventana_productosdisponibles() {
		inizializarventana();
	}
	
	public void inizializarventana() {
		this.setLayout(new GridLayout(3,1));
		pnl_btn = new JPanel();
		panelprincipal=new JPanel();
		num=new JTextField("",15);
		
		anterior = new JButton("<");
		mostrar_productos=new JButton("Mostrar productos");
		pnl_btn.add(anterior);
		anterior.setForeground(Color.BLACK);
		anterior.setBackground(Color.white);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		anterior.setBorder(compound);
		
		pnl_btn.add(num);
		pnl_btn.add(mostrar_productos);
		
		this.add(pnl_btn);
//		ArrayList<Producto> p=new ArrayList<Producto>();
//		Comprapresupuesto(60,p);
//		String url=bd.getURLFOTO(listaPed.get(0).getLista_pedidos().get(0));
//		
//		foto.setPreferredSize( new Dimension( 200, 200 ) );
//		ImageIcon imagen = new ImageIcon( url );
//		foto.setImagen( imagen );
		panelprincipal.add(foto);
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
				//for (Pedidos prod:p) {
				Pedidos prod=listaPed.get(0);
					for(Producto prod2:prod.getLista_pedidos()) {
						
						String url=bd.getURLFOTO(prod2);
						
						foto.setPreferredSize( new Dimension( 200, 200 ) );
						ImageIcon imagen = new ImageIcon( url );
						foto.setImagen( imagen );
//						panelprincipal.add(foto);
//						panelprincipal.add(num);
						foto.repaint();
						try {
							Thread.sleep( 10 );
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					for(Producto prod2:prod.getLista_pedidos()) {
//						String url=bd.getURLFOTO(prod2);
//						
//						foto.setPreferredSize( new Dimension( 200, 200 ) );
//						ImageIcon imagen = new ImageIcon( url );
//						foto.setImagen( imagen );
//						panelprincipal.remove(foto);;
////						foto.repaint();
//						
//					}
//					
				//}
				
				
			}
			
		};
		mostrar_productos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Producto> p=new ArrayList<Producto>();
				Comprapresupuesto(Integer.parseInt(num.getText()),p);
				hiloActual.start();
			}
			
		});
		

		
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
	public static double devolverproductomasbarato() {
		ArrayList<Producto> p=BD.getProductos();
		double min=p.get(0).getPrecio();
		for (Producto prod:p) {
			if (prod.getPrecio()<min) {
				min=prod.getPrecio();
			}
		}
		return min;
	}
	static double prec =devolverproductomasbarato();
	private static ArrayList<Pedidos> listaPed=new ArrayList<Pedidos>();
	//funcin recursiva que calcule todas las compras posibles que se pueden hacer teniendo un presupuesto. 
	public static void Comprapresupuesto( double disponible ,ArrayList<Producto> listaProd ) {
		//en vez de menos o igual que 0 poner menos o igual que el precio del producto que sea mas barato. 
		
		
		if (disponible<prec) {
			//System.out.println((ArrayList<Producto>) listaProd.clone());
			@SuppressWarnings("unchecked")
			Pedidos p = new Pedidos(((ArrayList<Producto>)listaProd.clone()));
			listaPed.add(p);
			
			
		}else {
			//poner con lo de talla para que asi no se repita 
			for(Producto j :BD.buscarProductoTalla(Talla.XS)) {
				if (disponible - j.getPrecio()>0) {
					
					
					listaProd.add(j);
					Comprapresupuesto(disponible-j.getPrecio(),listaProd);
					listaProd.remove(j);
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		Ventana_productosdisponibles vp=new Ventana_productosdisponibles();
		vp.setVisible(true);
		vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vp.setExtendedState(vp.MAXIMIZED_BOTH);
	}
	
}
