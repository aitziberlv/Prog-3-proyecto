package Ventanas;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana_Carrito extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel titulo;
	private JPanel centro;
	private JPanel abajoIzq;
	private JPanel abajoDer;

	private JLabel producto;
	private JLabel carrito;
	private JLabel codigo;
	private JLabel precio;
	private JLabel talla;
	private JLabel tipo;
	private JLabel cantidad;
	
	private JButton borrar;
	private JButton a�adir; //volver a la tienda y añadir mas productos.
	private JButton pagar;
	private JButton guardar; 
	
	public Ventana_Carrito() {
		inicializarVentana();
	}

	private void inicializarVentana() {
		
		this.setSize(500, 500);
		setLocationRelativeTo(null);
		this.setTitle("DEUSTO OUTLET CARRITO");
		this.setLayout(new GridLayout(3,1));
		
		titulo = new JPanel();
		centro = new JPanel();
		abajoIzq = new JPanel();
		abajoDer = new JPanel();

		
		producto = new JLabel("Productos seleccionados:");
		carrito = new JLabel("CARRITO");
		codigo = new JLabel("Código:");
		precio = new JLabel("Precio:");
		talla = new JLabel("Talla:");
		tipo = new JLabel("Tipo:");
		cantidad = new JLabel("Seleccione cantidad:");

		
		borrar = new JButton("Eliminar producto");
		añadir = new JButton("Añadir nuevos productos");
		pagar = new JButton("Realizar pago");
		guardar = new JButton("Guardar datos");

		
		
		
		titulo.add(carrito);
		centro.add(producto);
		
		
		
		
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		Ventana_Carrito vca =new Ventana_Carrito();
		vca.setVisible(true);
	}
	

}
