package Ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Clasesprincipales.TipoProducto;
import Clasesprincipales.Colorc;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
public class Ventana_Cliente extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel arriba;
	private JPanel centro;
	private JPanel centro_izda;
	private JPanel centro_dcha;
	private JPanel abajo;
	
	private JLabel cliente;
	private JLabel tipo;
	private JLabel color;
	private JLabel precio;
	private JLabel talla;
	
	private JComboBox<Colorc> c;
	private JTextField informacion;
	
	private JSlider preciobarra;
	
	private JComboBox<TipoProducto> tipos;
	private JComboBox<Talla> tallas;
	
	private JButton buscar;
	private JButton anyadir;
	private JButton carrito;
	
	private JTable tablaProductos;
	private DefaultTableModel modeloDatosproductos;
	private List <Producto> Productos;
		
	
	public Ventana_Cliente() {
		inicializarVentana();
	}
	private void inicializarVentana() {
		this.setSize(900, 700);
		this.setTitle("DEUSTO OUTLET COMPRAR");
		this.setLayout(new GridLayout(3,1));
		
		arriba = new JPanel();
		centro = new JPanel();
		centro.setLayout(new GridLayout(1,2));
		centro_izda = new JPanel();
		centro_izda.setLayout(new GridLayout(4,1));
		centro_dcha = new JPanel();
		abajo = new JPanel();
		abajo.setLayout(new GridLayout(2,1));
		
		cliente = new JLabel("CLIENTE");
		tipo = new JLabel("Tipo:");
		color = new JLabel("Color:");
		precio = new JLabel ("Precio:");
		talla = new JLabel("Talla:");
		
		preciobarra = new JSlider(0, 200);
		preciobarra.setPaintTrack(true);
		preciobarra.setPaintTicks(true);
		preciobarra.setPaintLabels(true);
		preciobarra.setMajorTickSpacing(20);
		preciobarra.setMinorTickSpacing(20);
		
		c = new JComboBox<Colorc>();
		for (Colorc co:Colorc.values()) {
			c.addItem(co);
		}
		informacion = new JTextField("Aqui apareceran los productos que estan disponibles con esas caracteristicas. ",16);
		informacion.setEditable(false);
		
		tipos = new JComboBox<TipoProducto>();
		for(TipoProducto tipo : TipoProducto.values()) {
			tipos.addItem(tipo);
		}
		
		tallas = new JComboBox<Talla>();
		for(Talla t : Talla.values()) {
			tallas.addItem(t);
		}
		
		buscar = new JButton("Buscar");
		anyadir = new JButton("Añadir");
		carrito=new JButton("Ver carrito");
		carrito.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana_Carrito vcc =new Ventana_Carrito();
				vcc.setVisible(true);
				setVisible(false);
				
			}
			
		});
		
		//jtable.
		Vector<String> cabeceraProductos = new Vector<String>(Arrays.asList("CODIGO", "NOMBRE", "COLOR", "TALLA", "TIPO", "FRANQUICIA", "PRECIO"));
		this.modeloDatosproductos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraProductos);
		this.tablaProductos = new JTable(this.modeloDatosproductos);
		this.tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
//		for(Producto p : this.Productos) {
//			this.modeloDatosproductos.addRow(new Object[] {
//					p.getCodigo(), p.getNombre(), p.getColor(), p.getTalla(), p.getTipo(), p.getFranquicia(), p.getPrecio()
//			});
//		}
		
		arriba.add(cliente);
		
		centro_izda.add(tipo);
		centro_izda.add(tipos);
		centro_izda.add(color);
		centro_izda.add(c);
		centro_izda.add(precio);
		centro_izda.add(preciobarra);
		centro_izda.add(talla);
		centro_izda.add(tallas);
		centro_dcha.add(buscar);
		centro_dcha.add(carrito);
		centro.add(centro_izda);
		centro.add(centro_dcha);

		abajo.add(informacion);
		abajo.add(tablaProductos);
		abajo.add(anyadir);

		Color color1= new Color(243,242,235);
		this.add(arriba);
		this.add(centro);
		this.add(abajo);
		Color colo1= new Color(255,255,216);
		arriba.setBackground(colo1);
		arriba.setBackground(color1);
		abajo.setBackground(colo1);
		centro.setBackground(colo1);
		centro_izda.setBackground(colo1);
		centro_dcha.setBackground(colo1);
		this.setLocationRelativeTo(null);
		this.setBackground(colo1);
		


	}

	public static void main(String[] args) {
		Ventana_Cliente vc =new Ventana_Cliente();
		vc.setVisible(true);
	}
	
}