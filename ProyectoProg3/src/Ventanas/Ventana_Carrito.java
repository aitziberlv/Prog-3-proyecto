	package Ventanas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JPanel abajo1;
	private JPanel abajoIzq;
	private JPanel abajoDer;
	private JPanel abajo;

	private JLabel producto;
	private JLabel carrito;
	private JLabel codigo;
	private JLabel precio;
	private JLabel talla;
	private JLabel tipo;
	private JLabel cantidad;
	
	private JButton borrar;
	private JButton añadir; //volver a la tienda y añadir mas productos.
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
		abajo1 = new JPanel();
		abajoIzq = new JPanel();
		abajoDer = new JPanel();
		abajo = new JPanel();

		
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

		
		añadir.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_Cliente vc = new Ventana_Cliente();
				vc.setVisible(true);
				


				
			}
			
		});
		
		titulo.add(carrito);
		centro.add(producto);
		
		abajo1.add(añadir);
		abajo1.add(borrar);
		abajoIzq.add(guardar);
		abajoDer.add(pagar);
		abajo.add(abajo1);
		abajo.add(abajoIzq);
		abajo.add(abajoDer);
		
		
		
		
		this.add(titulo);
		this.add(centro);
		this.add(abajo);
		
	}
	
	
	
	
	public static void main(String[] args) {
		Ventana_Carrito vca =new Ventana_Carrito();
		vca.setVisible(true);
		
	}
	

}
