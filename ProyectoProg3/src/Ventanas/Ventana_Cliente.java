package Ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import BD.BD;
import Clasesprincipales.TipoProducto;
import Clasesprincipales.Colorc;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
public class Ventana_Cliente extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel arriba;
	private JPanel arriba1;
	private JPanel arriba2;
	private JPanel arriba3;
	private JPanel centro;
	private JPanel arriba_texto_recursividad;
	private JPanel centro_izda;
	private JPanel centro_dcha;
	private JPanel abajo;
	private JPanel valordebarra;
	private JLabel cliente;
	private JLabel tipo;
	private JLabel color;
	private JLabel precio;
	private JLabel talla;
	private JLabel valordebarra_l;
	private JComboBox<Colorc> c;
	private JTextField informacion;
	private JLabel valordebarra_l2;
	private JSlider preciobarra;
	private JButton botonrecursividad;
	private JComboBox<TipoProducto> tipos;
	private JComboBox<Talla> tallas;
	private JLabel labelRecursividad ;
	private JButton buscar;
	private JButton anyadir;
	private JButton carrito;
	private JPanel boton_r ;
	private JTable tablaProductos;
	private DefaultTableModel modeloDatosproductos = new DefaultTableModel();
	private JScrollPane scrolTabla;
	
	private JScrollPane panelDeslizable ;	
	
	public Ventana_Cliente() {
		inicializarVentana();
	}
	private void inicializarVentana() {
		this.setSize(900, 700);
		this.setTitle("DEUSTO OUTLET COMPRAR");
		this.setLayout(new GridLayout(3,1));
		panelDeslizable=new JScrollPane(); 
		arriba = new JPanel(new GridLayout(3,1));
		arriba1=new JPanel();
		boton_r=new JPanel();
		arriba2=new JPanel();
		arriba3=new JPanel(new GridLayout(1,3));
		labelRecursividad = new JLabel("Si tienes un presupuesto \n y no sabes que productos te podrias comprar  con dicho presupuesto nuestra aplicacion te ayuda a ello mostrandote toda la lista de productos que podrias comprarte con ese presupuesto ");
		centro = new JPanel(new GridLayout(1,3));
		botonrecursividad=new JButton("Mostrar productos");
		centro.setLayout(new GridLayout(1,2));
		centro_izda = new JPanel();
		centro_izda.setLayout(new GridLayout(5,1));
		centro_dcha = new JPanel();
		abajo = new JPanel();
		abajo.setLayout(new GridLayout(2,1));
		valordebarra= new JPanel ();
		arriba_texto_recursividad=new JPanel();
		panelDeslizable.setViewportView(labelRecursividad);
		cliente = new JLabel("CLIENTE");
		Font fuente = new Font("Arial", 5, 70);

		cliente = new JLabel("DEUSTO OUTLET");
		Font fuentee = new Font("Arial", 1, 20);

	    cliente.setFont(fuente); 
		tipo = new JLabel("Tipo:");
		color = new JLabel("Color:");
		precio = new JLabel ("Precio mínimo:");
		talla = new JLabel("Talla:");
		valordebarra_l =new JLabel("Seleciona el precio maximo");
		valordebarra_l.setForeground(Color.DARK_GRAY);
		preciobarra = new JSlider(0, 200);
		preciobarra.setPaintTrack(true);
		preciobarra.setPaintTicks(true);
		preciobarra.setPaintLabels(true);
		preciobarra.setMajorTickSpacing(40);
		preciobarra.setMinorTickSpacing(40);
		valordebarra_l2=new JLabel();
		scrolTabla = new JScrollPane();
		c = new JComboBox<Colorc>();
		c.addItem(null);
		for (Colorc co:Colorc.values()) {
			c.addItem(co);
		}
		informacion = new JTextField("Productos con esas caracteristicas: ",16);
		informacion.setEditable(false);
		
		tipos = new JComboBox<TipoProducto>();
		tipos.addItem(null);
		for(TipoProducto tipo : TipoProducto.values()) {
			tipos.addItem(tipo);
			
		}
		
		tallas = new JComboBox<Talla>();
		tallas.addItem(null);
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
		
		
		//JTable de los productos con las características especificadas por el usuario
		Vector<String> cabeceraProductos = new Vector<String>(Arrays.asList("CODIGO", "NOMBRE", "PRECIO", "COLOR", "TALLA", "TIPO"));
		this.modeloDatosproductos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraProductos);
		this.tablaProductos = new JTable(this.modeloDatosproductos);
		this.tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrolTabla.setViewportView(tablaProductos);
		
		for(Producto p : BD.getProductos()) {
			modeloDatosproductos.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio(), p.getColor(), p.getTalla(), p.getTipo()});
		}
		
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i=modeloDatosproductos.getRowCount()-1; i >= 0; i--){
				      System.out.println("i "+i); 
				      modeloDatosproductos.removeRow(i );
				   } 
				
				for(Producto p : BD.buscarProductoCaracteristicas(TipoProducto.valueOf(tipos.getSelectedItem().toString()) , Colorc.valueOf(c.getSelectedItem().toString()), preciobarra.getValue(),Talla.valueOf(tallas.getSelectedItem().toString()) )) {
					modeloDatosproductos.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio(), p.getColor(), p.getTalla(), p.getTipo()});
				}
				
				
			}
		});
		
		
		//Añadir el valor del precio
		preciobarra.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				valordebarra_l.setText(preciobarra.getValue()+"");
			}			
		});
		
		
		
//		for(Producto p : this.Productos) {
//			this.modeloDatosproductos.addRow(new Object[] {
//					p.getCodigo(), p.getNombre(), p.getColor(), p.getTalla(), p.getTipo(), p.getFranquicia(), p.getPrecio()
//			});
//		}
		
		//añadir tres paneles para que quede centrado. 
		arriba2.add(cliente);
		arriba_texto_recursividad.add(panelDeslizable);
		
		arriba3.add(arriba_texto_recursividad);
		boton_r.add(botonrecursividad);
		arriba3.add(boton_r);
		arriba.add(arriba1);
		arriba.add(arriba2);
		arriba.add(arriba3);
		// lo de abajo si queremos que quede centrado hacemos lo mismo 
		centro_izda.add(tipo);
		centro_izda.add(tipos);
		centro_izda.add(color);
		centro_izda.add(c);
		centro_izda.add(valordebarra_l2);
		centro_izda.add(valordebarra);
		centro_izda.add(precio, BorderLayout.CENTER);
		centro_izda.add(preciobarra);
		valordebarra.add(valordebarra_l);
		
		centro_izda.add(talla);
		centro_izda.add(tallas);
		centro_dcha.add(buscar);
		centro_dcha.add(carrito);
		centro.add(centro_izda);
		centro.add(centro_dcha);

		abajo.add(informacion);
		abajo.add(scrolTabla);
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