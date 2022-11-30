	package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Clasesprincipales.Producto;

public class Ventana_Carrito extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel titulo;
	private JPanel centro;
	private JPanel centro_iz;
	private JPanel centro_dcha;
	private JPanel abajo1;
	private JPanel abajoIzq;
	private JPanel abajoDer;
	private JPanel abajo;
	private JPanel lista;
	private JPanel frase;

	private JLabel producto;
	private JLabel carrito;
	private JLabel precio;
	private JLabel cantidad;
	
	private JButton borrar;
	private JButton añadir; //volver a la tienda y añadir mas productos.
	private JButton pagar;
	private JButton guardar; 
	
	private DefaultListModel<String> mSelec;
	private JList<String> lSelec;
	
	public Ventana_Carrito() {
		inicializarVentana();
	}

	private void inicializarVentana() {
		
		this.setSize(900, 700);
		setLocationRelativeTo(null);
		this.setTitle("DEUSTO OUTLET CARRITO");
		this.setLayout(new GridLayout(3,1));
		
		titulo = new JPanel();
		centro = new JPanel();
		centro.setLayout(new GridLayout(1,2));
		centro_dcha = new JPanel();
		centro_iz = new JPanel();
		centro_iz.setLayout(new GridLayout(1,2));
		lista = new JPanel();
		frase = new JPanel();
		abajo1 = new JPanel();
		abajoIzq = new JPanel();
		abajoDer = new JPanel();
		abajo = new JPanel();
		abajo.setLayout(new GridLayout(3,2));

		
		mSelec = new DefaultListModel<String>();
		lSelec = new JList<String>();
		lSelec.setModel(mSelec);
		
		for( int i=0; i<Ventana_Cliente.getCarrito().size(); i++) {
			mSelec.addElement(Ventana_Cliente.getCarrito().get(i).getNombre()+ ", " + Ventana_Cliente.getCarrito().get(i).getPrecio() + ", " + Ventana_Cliente.getCarrito().get(i).getTalla() + ", " + Ventana_Cliente.getCarrito().get(i).getColor() );
		}
		
		
		producto = new JLabel("Productos seleccionados:");
		carrito = new JLabel("CARRITO");
		precio = new JLabel("Total a pagar: " + Ventana_Cliente.getPago());
		//cantidad = new JLabel("Seleccione cantidad:");

		
		borrar = new JButton("Eliminar producto");
		añadir = new JButton("Añadir nuevos productos");
		pagar = new JButton("Realizar pago");
		guardar = new JButton("Guardar datos");

		
		borrar.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//if no hay producto seleccionado:
				if(lSelec.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione el producto que desea eliminar del carrito.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					mSelec.removeElement(lSelec.getSelectedValue());
				}
				//si selecciona un producto: se elimina del pedido.
				
			}
			
		});
		
		añadir.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_Cliente vc = new Ventana_Cliente();
				vc.setVisible(true);
				setVisible(false);

				
			}
			
		});
		
		pagar.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_Pagar vp = new Ventana_Pagar();
				vp.setVisible(true);
				setVisible(false);

				
			}
			
		});
		
		guardar.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog( null, "Su compra ha sido guardada con éxito.");

			}
			
		});
		
		
		titulo.add(carrito);
		frase.add(producto);
		lista.add(lSelec, BorderLayout.NORTH);
		centro_dcha.add(precio);
		centro_iz.add(frase);
		centro_iz.add(lista);
		centro.add(centro_iz);
		centro.add(centro_dcha);
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
		vca.setExtendedState(Ventana_Carrito.MAXIMIZED_BOTH);
		
	}
	

}
