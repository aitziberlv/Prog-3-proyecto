package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import BD.BD;
import Clasesprincipales.Producto;
import Clasesprincipales.Usuario;

public class Ventana_Administrador extends JFrame {

	/**
	 * A ESTA VENTANA UNICAMENTE VA A TENER ACCESO LOS ADMINISTRADORES DE LA APLICACIO Y PODRAN VER DATOS DE LOS CLIENTES 
	 * ENTRE ESTOS DATOS QUIEN ES EL CLIENTE QUE MAS HA COMPRADO O CUAL ES EL CLIENTE QUE MENOS HA COMPRADO.
	 */
	private static final long serialVersionUID = 1L;
	private static List<Usuario> lUsuarios;
	private JPanel pnl_titulo;
	private JPanel pnl_centro; 
	private JPanel pnl_centro_iz;
	private JPanel pnl_centro_dcha;
	private JPanel pnl_abajo;
	private JPanel pnl_infor;
	private JLabel titulo;
	private JLabel estadistica1;
	private JLabel estadistica2;
	private JLabel estadistica3;
	private JLabel estadistica;
	private JButton atras;
	private String usuario;
	private JPanel central;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	/**
	 * INICIALIZACION DE LA VENTANA CON EL NOMRBE DEL ADMINISTRADOR 
	 * @param usuario
	 */
	public Ventana_Administrador (String usuario) {
		inicializarVentana();
		this.usuario=usuario;
	}
	public Ventana_Administrador () {
		inicializarVentana();
	}
	
	private void inicializarVentana () {
		this.setSize(990, 500);
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		setLocationRelativeTo(null);
		this.setTitle("Administrador");
		this.setLayout(new GridLayout(4,1));
		central=new JPanel();
		central.setLayout(new GridLayout(3,1));
		pnl_titulo = new JPanel();
		pnl_infor = new JPanel();
		pnl_centro = new JPanel();
		pnl_centro.setLayout(new GridLayout(1,3));
		pnl_centro_iz = new JPanel();
		pnl_centro_iz.setLayout(new GridLayout(1,2));
		pnl_centro_dcha = new JPanel();
		pnl_abajo = new JPanel();
		pnl_abajo.setLayout(new GridLayout(3,2));
		titulo = new JLabel("ADMINISTRADOR");
		estadistica = new JLabel("Estadisticas sobre Deusto Outlet.");
		estadistica1 = new JLabel("Usuario con mas compras: " + getusu_mas());
		estadistica2 = new JLabel("Usuario con menos compras: "+ getusu_menos());
		estadistica3 = new JLabel("Producto mas barato: " + productoMasBarato());
		atras = new JButton("<");
		atras.setForeground(Color.black);
		atras.setBackground(Color.white);
		atras.setPreferredSize(new Dimension(50,0));
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana_IS is = new Ventana_IS();
				is.setVisible(true);
				setVisible(false);
				is.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
				
			}
		});
		Font fuente = new Font("Arial", 1, 20);
	    titulo.setFont(fuente);
		Image img= new ImageIcon("deustoOutlet.jpg.png").getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(140, 140, Image.SCALE_SMOOTH));
		titulo.setIcon(img2);
		pnl_titulo.add(titulo);
		pnl_infor.add(estadistica);
		central.add(p1);
		central.add(p2);
		p2.add(estadistica1);
		p2.add(estadistica2);
		p2.add(estadistica3);
		central.add(p3);
		pnl_centro.add(pnl_centro_iz);
		pnl_centro.add(central);
		pnl_centro.add(pnl_centro_dcha);
		pnl_abajo.add(atras);
		this.add(pnl_titulo);
		this.add(pnl_infor);
		this.add(pnl_centro);
		this.add(pnl_abajo);
		lUsuarios = BD.getUsuario();
		Border blackline=BorderFactory.createLineBorder(Color.black);
		p2.setBorder(blackline);
		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		p3.setBackground(Color.white);
		central.setBackground(Color.white);
		pnl_abajo.setBackground(Color.white);
		pnl_titulo.setBackground(Color.white);
		pnl_centro_iz.setBackground(Color.white);
		pnl_centro_dcha.setBackground(Color.white);
		pnl_infor.setBackground(Color.white);
	}
	

	/**
	 * 
	 * @return EL USUARIO O USUARIOS QUE MAS PEDIDOS HAN HECHO
	 */
//	public String getusu_mas() {
//		int n=-1;
//		String usu="";
//		Map<String,Integer> mp=BD.rellenarmapa_admin();
//		for (java.util.Map.Entry<String, Integer> s:mp.entrySet()) {
//			if (s.getValue()>n) {
//				usu=s.getKey();
//				n=s.getValue();
//			}else if (s.getValue()==n) {
//				usu=usu+", "+s.getKey();
//			}
//		}
//		if (n==-1) {
//			return "Ningun usuario ha comprado nada";
//		}else {
//			return usu;
//		}
//	}
//	
	
	//RECURSIVO
	
	public String getusu_mas() {
	    Map<String,Integer> mp = BD.rellenarmapa_admin();
	    String usu = getusu_mas_rec(mp);
	    if (usu.isEmpty()) {
	        return "Ningun usuario ha comprado nada";
	    } else {
	        return usu;
	    }
	}

	private String getusu_mas_rec(Map<String,Integer> mp) {

		if (mp.isEmpty()) {
	        return "";
	    }
	    // obtener el primer elemento del mapa
	    Map.Entry<String, Integer> s = mp.entrySet().iterator().next();
	    String usu = s.getKey();
	    int n = s.getValue();
	    // llamada recursiva con el subconjunto del mapa sin el primer elemento
	    for (Map.Entry<String, Integer> entry : mp.entrySet()) {
	        if (entry.getValue() > n) {
	            usu = entry.getKey();
	            n = entry.getValue();
	        } else if (entry.getValue() == n) {
	            usu = usu + ", " + entry.getKey();
	        }
	    }
	    return usu;
	}

	
	
	/**
	 * EL USUARIO O USUARIOS QUE MENOS PEDIDOS HAN HECHO
	 * @return
	 */
//	public String getusu_menos() {
//		Map<String,Integer> mp=BD.rellenarmapa_admin();
//		String usu=mp.keySet().iterator().next();
//		int n=mp.get(usu);
//		for (java.util.Map.Entry<String, Integer> s:mp.entrySet()) {
//			if (s.getValue()<n) {
//				usu=s.getKey();
//				n=s.getValue();
//			}else if (s.getValue()==n && s.getKey().equals(usu)==false) {
//				usu=usu+", "+s.getKey();
//			}
//		}
//		if (n==-1) {
//			return "Ningun usuario ha comprado nada";
//		}else {
//			return usu;
//		}
//	}
	
	public String getusu_menos() {
	    Map<String,Integer> mp = BD.rellenarmapa_admin();
	    String usu = getusu_menos_rec(mp);
	    if (usu.isEmpty()) {
	        return "Ningun usuario ha comprado nada";
	    } else {
	        return usu;
	    }
	}

	private String getusu_menos_rec(Map<String,Integer> mp) {
		
		if (mp.isEmpty()) {
	        return "";
	    }
	    // obtener el primer elemento del mapa
	    Map.Entry<String, Integer> s = mp.entrySet().iterator().next();
	    String usu = s.getKey();
	    int n = s.getValue();
	    // llamada recursiva con el subconjunto del mapa sin el primer elemento
	    for (Map.Entry<String, Integer> entry : mp.entrySet()) {
	        if (entry.getValue() < n) {
	            usu = entry.getKey();
	            n = entry.getValue();
	        } else if (entry.getValue() == n && entry.getKey().equals(usu)==false) {
	            usu = usu + ", " + entry.getKey();
	        }
	    }
	    return usu;
	}
	
	public int precioMasBarato(ArrayList<Producto> prod, int indice) {
		int minimo = 1000;
		if(indice != prod.size()){
			if(prod.get(indice).getPrecio() < minimo) {
				minimo = Math.min(prod.get(indice).getPrecio(), precioMasBarato(prod, indice + 1));
			}
			
		}
		
		return minimo;		
		
	}
	
	public Producto productoMasBarato() {
		int precio = precioMasBarato(BD.getProductos(), 0);
		Producto p = BD.buscarProductoPrecio(precio);
		return p;
	}
	
	public static void main(String[] args) {
		Ventana_Administrador va =new Ventana_Administrador();
		va.setVisible(true);
		va.setExtendedState(Ventana_Carrito.MAXIMIZED_BOTH);
		
	}
}
