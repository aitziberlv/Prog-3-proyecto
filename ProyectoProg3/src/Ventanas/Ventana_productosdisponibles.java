package Ventanas;

import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Clasesprincipales.Pedidos;
//en esta ventana vamos a lanzar un hilo mostrando todos los pedidos
import Clasesprincipales.Producto;


public class Ventana_productosdisponibles extends JFrame{
	private ArrayList<Pedidos> p=new ArrayList<>();

	public Ventana_productosdisponibles(ArrayList<Pedidos> p) throws HeadlessException {
		super();
		this.p = p; 
	}
	private JPanel panelprincipal ;
	public void inizializarventana() {
		panelprincipal=new JPanel();
		//Aqui es donde pondremos el hilo: 
		for (Pedidos prod:p) {
			for(Producto prod2:prod.getLista_pedidos()) {
				
			}
		}
		
		
		this.add(panelprincipal);
	}
}
