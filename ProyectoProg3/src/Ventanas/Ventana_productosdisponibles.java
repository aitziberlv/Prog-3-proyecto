package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import BD.BD;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;

/**
 * 
 * ESTA VENTANA SE TRATA DE MOSTRAR LA COMBINACION DE UNOS PRODUCTOS A PARTIR DE UN PRECIO MAXIMO QUE ES INTRODUCITO POR EL CLIENTE
 * PARA VER NUEVAS COMBINACIONES HAY QUE DARLE A MOSTRAR NUEVAS COMBINACIONES Y TE VUELVE A  MOSTRAR MAS PEDIDOS
 *
 */

public class Ventana_productosdisponibles extends JFrame{
	/**
	 * TAMBIEN DESDE ESTA VENTANA PODREMOS VOLVER A LA VENTANA CLIENTE 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Pedidos> p=new ArrayList<>();
//	paneles de la ventana
	private JPanel panelprincipal ;
	private JPanel pnl_btn;
	private JPanel pnl_abajo;
//	boton
	private JButton anterior;
	private JButton mostrar_productos;
	private JButton ver_siguente;
	private BD bd=new BD();
	private JLabel foto_;
	private JTextField num;
	private String usu;
	private int index=1;
	public Ventana_productosdisponibles(String usuario, int index) {
		inizializarventana();
		this.usu=usuario;
		this.index=index;
	}
	public Ventana_productosdisponibles() {
		inizializarventana();
	}
	
	public void inizializarventana() {
		this.setLayout(new GridLayout(3,1));
		pnl_btn = new JPanel();
		panelprincipal=new JPanel();
		num=new JTextField("",15);
		foto_=new JLabel();
		anterior = new JButton("<");
		mostrar_productos=new JButton("Mostrar productos");
		pnl_btn.add(anterior);
		anterior.setForeground(Color.BLACK);
		anterior.setBackground(Color.white);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		anterior.setBorder(compound);
		pnl_abajo=new JPanel();
		pnl_btn.add(num);
		pnl_btn.add(mostrar_productos);
		ver_siguente=new JButton("Ver otra combinacion de productos");
		this.add(pnl_btn);
		pnl_btn.setBackground(Color.WHITE);
		ver_siguente.setBackground(Color.lightGray);
		mostrar_productos.setForeground(Color.white);
		mostrar_productos.setBackground(Color.black);
		/**
		 * PARA VOLVER A LA VENTANA ANTERIOR 
		 */
		anterior.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_Cliente vc = new Ventana_Cliente(usu);
				vc.setVisible(true);
				setVisible(false);
				vc.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);

			}
			
		});
		/**
		 * ESTO SE TRATA DE UN HILO QUE VA MOSTRANDO LA FOTO DE CADA PRODUCTO QUE ESTA DENTRO DE UNA DETERMINADA COMBINACION DE PRODUCTOS 
		 * DENTRO DEL PRECIO PUESTO. 
		 */
		Thread hiloActual = new Thread() { 
			public void run() {
				Pedidos prod=listaPed.get(index);
					for(Producto prod2:prod.getLista_pedidos()) {
						String url2=BD.getURLFOTO(prod2);
						Image img= new ImageIcon(url2).getImage();
						ImageIcon img2=new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
						foto_.setIcon( img2 );
						foto_.repaint();
						try {
							Thread.sleep( 2000 );
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}
			
		};
		/**
		 * CUANDO LE DEMOS A ESTE BORON SE EMPEZARA AMOSTRAR LA PRIMERA COMBINACION DE PRODUCTOS
		 */
		mostrar_productos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(num.getText())>=50 &&Integer.parseInt(num.getText())<=300) {
					ArrayList<Producto> p=new ArrayList<Producto>();
					Comprapresupuesto(Integer.parseInt(num.getText()),p);
					
					hiloActual.start();
				}else {
					JOptionPane.showMessageDialog(null, "El numero introducido debe de estar entre 50 y 300.","Error",JOptionPane.ERROR_MESSAGE);
					
				}
				num.setEditable(false);
				mostrar_productos.setVisible(false);
			}
		});
		/**
		 * CUANDO LE DEMOS A ESTE BOTON PODREMOS VER UNA NUEVA COMBINACION
		 */
		ver_siguente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (index+1<=listaPed.size()) {
				Ventana_productosdisponibles vpd=new Ventana_productosdisponibles(usu, index+1);
				vpd.setVisible(true);
				setVisible(false);
				vpd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				vpd.setSize(700, 1000);
				vpd.setLocationRelativeTo(null);
				vpd.num.setText(num.getText());
				vpd.mostrar_productos.doClick();
				num.setEditable(false);
				mostrar_productos.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "No te podemos mostrar nuevas combinaciones de productos","Error",JOptionPane.ERROR_MESSAGE);
					
				}
			}
			
		});
		
		panelprincipal.add(foto_);
		this.add(panelprincipal);
		pnl_abajo.add(ver_siguente);
		ImageIcon icono = new ImageIcon("FotosTiendas/deustoOutlet.jpg.png");
		this.setIconImage(icono.getImage());
		this.setBackground(Color.white);
		panelprincipal.setBackground(Color.white);
		this.add(pnl_abajo);
		pnl_abajo.setBackground(Color.white);
		
	}
	
	/**
	 * FUNCION DE RECURSIVIDAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * ESTA FUNCION CALCULA LA LISTA DE PEDIDOS POSIBLES QUE HAY QUE VALGAN MENOS QUE UN DETERMINADO PRECIO Y QUE 
	 * EN LA LISTA DE PRODUCTOS NO SE REPITA EL PRODUCTO Y APAREZCAN MAS DE UN PRODUCTOS!!!!!!!!!!!!!!!!!!!!!!!!
	 * @return rellena la lista de pedidos con todos pedidos posibles que puede haber . 
	 */
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
	/**
	 * 
	 */
	static double prec =devolverproductomasbarato();
	static ArrayList<Pedidos> listaPed=new ArrayList<Pedidos>();
	
	//funcin recursiva que calcule todas las compras posibles que se pueden hacer teniendo un presupuesto. 
	public static void Comprapresupuesto( double disponible ,ArrayList<Producto> listaProd ) {
		//en vez de menos o igual que 0 poner menos o igual que el precio del producto que sea mas barato. 
		
		if (disponible<prec) {
			
			@SuppressWarnings("unchecked")
			Pedidos p = new Pedidos(((ArrayList<Producto>)listaProd.clone()));
			if (listaProd.size()>1) {
				listaPed.add(p);
			}
			
		}else {
			/**
			 * LA RECURSIVIDAD ESTA HECHA SIEMPRE COMPROBANDO QUE AL AÑADIR UN PRODUCTO ESE PRODUCTO NO ESTE YA EN LA LISTA.
			 */
			//poner con lo de talla para que asi no se repita el mismo producto 
			for(Producto j :BD.buscarProductoTalla(Talla.XS)) {
				if (disponible - j.getPrecio()>0) {
					if (listaProd.size()==0) {
						listaProd.add(j);
						Comprapresupuesto(disponible-j.getPrecio(),listaProd);
						listaProd.remove(j);
					}else {
						boolean b=false;
						for (Producto p:listaProd) {
							if( p.getNombre().equals(j.getNombre())) {
								b=true;
			
							}
						}
						if(b==false) {
							listaProd.add(j);
							Comprapresupuesto(disponible-j.getPrecio(),listaProd);
							listaProd.remove(j);
					}
					
					}
				}
			}
		}
		
		
		
	}
	
	public static void main(String[] args) {
		Ventana_productosdisponibles vp=new Ventana_productosdisponibles();
		vp.setVisible(true);
		vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vp.setSize(700, 1000);
		vp.setLocationRelativeTo(null);
		//vp.setExtendedState(vp.MAXIMIZED_BOTH);
	}
	
}
