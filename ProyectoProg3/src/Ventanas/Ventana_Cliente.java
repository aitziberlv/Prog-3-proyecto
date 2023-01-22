package Ventanas;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import BD.BD;
import Clasesprincipales.TipoProducto;
import Logica.Logica;
import Ventanasexternas.FondoSwing;
import Clasesprincipales.Colorc;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
/**
 * 
 * ESTA VENTANA ES LA PRINCIPAL DE NUESTRO PROYECTO
 *
 */

/**
 * 
 * ESTA VENTANA SE ENCARGA DE MOSTRAR AL CLIENTE LOS PRODUCTOS :TODOS LOS PRODUCTOS QUE HAY Y LE DA LA OPORTUNIDAD AL CLIENTE 
 * DE PODER FILTRARLOS Y PODER CONSEGUIR UNICAMENTE UNOS PRODUCTOS QUE CUMPLAN ALFUNAS DE LAS CARACTERISTICAS QUE QUIERA EL 
 * CLIENTE . TIENE OPCIONES DIFERENTES Y PUEDE VER PRODUCTOS QUE CUMPLAN DIFERENTES FILTROS. TAMBOIEN TIENE LA OPCION DE VER TODOS LOS PRODUCTOS.
 *
 *NUESTRA APLICACION SE CENTRA MAS EN MOSTRAR DIFERENTES PRODUCTOS QUE CUMPLAN CIERTAS CARACTERISTICAS Y QUE SEAN DE DISTINTAS TIENDAS QUE EN LA TIENDA A LA QUE PERTENECEN.
 */

/**
 * 
 *1. MEDIANTE ESTA VENTANA TENEMOS ACCESO A ACCEDER A UNA AYUDA QUE TE MUESTRA DIFERENTES COMBINACIONES DE PRODUCTOS A PARTIR DE EL PRECIO QUE LE INTRODUZCAS
 * 2.TAMBIEN TENEMOS ACCESO A VER TODO EL CARRITO 
 *3. VENTANA EN LA QUE PODEMOS AÑADIR LOS PRODUCTOS QUE QUEREMOS . 
 *
 */

public class Ventana_Cliente extends JFrame{
	private static final long serialVersionUID = 1L;
	private static List<Producto> productosComprados ;
	public static int pagar;;
	private List<Producto> usando;
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
	private JPanel bañadir;
	private JLabel cliente;
	private JLabel tipo;
	private JLabel color;
	private JLabel precio;
	private JLabel talla;
	private JLabel valordebarra_l;
	private JComboBox<Colorc> c;
	private JLabel informacion;
	private JPanel vl2;
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
	private JLabel info_arriba;
	private JTable tablaProductos;
	private DefaultTableModel modeloDatosproductos = new DefaultTableModel();
	private JScrollPane scrolTabla;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JPanel t1;
	private JPanel t2;
	private JPanel t3;
	private JPanel t4;
	private JLabel lFoto;
	private JLabel lFoto_tienda;
	private JPanel t5;
	private JScrollPane panelDeslizable ;	
	private String usuario;
	/**
	 *INICIALIZACION DE LA VENTANA.
	 * @param usuario
	 * @throws HeadlessException
	 */
	public Ventana_Cliente(String usuario) throws HeadlessException {
		super();
		this.usuario = usuario;
		inicializarVentana();
	}

	
	private void inicializarVentana() {
		/**
		 * INIZIALIZACION DE TODOS LOS COMPONENTES DE LA VENTANA. 
		 */
		this.setSize(900, 700);
		//this.setTitle(" "+ BD.buscarUsuarioNombre(usuario).getbienveido());
		this.setLayout(new GridLayout(3,1));
		panelDeslizable=new JScrollPane(); 
		arriba = new JPanel(new GridLayout(1,1));
		arriba1=new JPanel(new FlowLayout());
		boton_r=new JPanel();
		arriba2=new JPanel();
		arriba3=new JPanel();
		lFoto=new JLabel();
	    lFoto_tienda=new JLabel();
		info_arriba=new JLabel("Gracias a esta aplicación podrás empezar a buscar productos de varias tiendas con las caracteristicas que deseas");
		b1=new JButton("Mostrar todo");
		b2=new JButton("Mostrar todo de un tipo determinado");
		b3=new JButton("Mostrar todo de un color determinado");
		b4=new JButton("Mostrar todo de una talla  determinada");
		centro = new JPanel(new GridLayout(1,3));
		botonrecursividad=new JButton("Ayuda para hacer mi compra");
		centro.setLayout(new GridLayout(1,2));
		centro_izda = new JPanel();
		centro_izda.setLayout(new GridLayout(5,1));
		centro_dcha = new JPanel();
		abajo = new JPanel();
		abajo.setLayout(new GridLayout(3,1));
		vl2=new JPanel();
		valordebarra= new JPanel ();
		arriba_texto_recursividad=new JPanel();
		panelDeslizable.setViewportView(labelRecursividad);
		bañadir = new JPanel();
		cliente = new JLabel("");
		Font fuente = new Font("Arial", 5, 50);
		t1=new JPanel();
		t2=new JPanel();
		t3=new JPanel();
		t4=new JPanel();
		t5=new JPanel();
		cliente = new JLabel("DEUSTO OUTLET");
		Font fuentee = new Font("Arial", 1, 20);
	    cliente.setFont(fuente); 
		tipo = new JLabel("Tipo:");
		color = new JLabel("Color:");
		precio = new JLabel ("Precio máximo:");
		talla = new JLabel("Talla:");
		valordebarra_l =new JLabel("Seleciona el precio máximo");
		valordebarra_l.setForeground(Color.DARK_GRAY);
		preciobarra = new JSlider(0, 150);
		preciobarra.setPaintTrack(true);
		preciobarra.setPaintTicks(true);
		preciobarra.setPaintLabels(true);
		preciobarra.setMajorTickSpacing(30);
		preciobarra.setMinorTickSpacing(30);
		valordebarra_l2=new JLabel();
		scrolTabla = new JScrollPane();
		/*
		 * INICIALIZAR LOS COMPONENTES DEL FILTRO DE PRODUCTOS
		 */
		c = new JComboBox<Colorc>();
		for (Colorc co:Colorc.values()) {
			c.addItem(co);
		}
		informacion = new JLabel("Abajo puede ver los productos que tienen estas caracteristicas ");
		tipos = new JComboBox<TipoProducto>();
		for(TipoProducto tipo : TipoProducto.values()) {
			tipos.addItem(tipo);	
		}
		tallas = new JComboBox<Talla>();
		for(Talla t : Talla.values()) {
			tallas.addItem(t);
		}
		productosComprados=new ArrayList<>();
		pagar=0;
		//ArrayList<Producto> panterior =BD.getlistaProductosCarritoAnterior(BD.getDNIusuario(usuario));
		// if ((Logica.lectura_p(usuario+".dat")==null)==false) {
			 ArrayList<Producto> panterior =(ArrayList<Producto>) Logica.lectura_p(usuario+".dat").getLista_pedidos();
				for (Producto o:panterior) {
					productosComprados.add(o);
					pagar += o.getPrecio();
							}
		// }
		
		//---------------------------------------
		buscar = new JButton("Buscar");
		buscar.setForeground(Color.black);
		buscar.setBackground(Color.white);
		anyadir = new JButton("Añadir");
		anyadir.setForeground(Color.black);
		anyadir.setBackground(Color.white);
		carrito=new JButton("Ver carrito");
		carrito.setForeground(Color.black);
		carrito.setBackground(Color.white);
		Image img= new ImageIcon("deustoOutlet.jpg.png").getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(140, 140, Image.SCALE_SMOOTH));
		cliente.setIcon(img2);
		//JTable de los productos con las características especificadas por el usuario
		Vector<String> cabeceraProductos = new Vector<String>(Arrays.asList("CODIGO", "NOMBRE", "PRECIO", "COLOR", "TALLA", "TIPO"));
		this.modeloDatosproductos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraProductos);
		this.tablaProductos = new JTable(this.modeloDatosproductos);
		this.tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrolTabla.setViewportView(tablaProductos);
		for(Producto p : BD.getProductos()) {
			modeloDatosproductos.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio(), p.getColor(), p.getTalla(), p.getTipo()});
			
		}
		usando=BD.getProductos();
		//tamaño 
		tablaProductos.setModel( modeloDatosproductos );
		tablaProductos.getColumnModel().getColumn(0).setMinWidth(210);
		tablaProductos.getColumnModel().getColumn(0).setMaxWidth(210);
		tablaProductos.getColumnModel().getColumn(1).setMinWidth(300);
		tablaProductos.getColumnModel().getColumn(1).setMaxWidth(300);
		tablaProductos.getColumnModel().getColumn(2).setMinWidth(210);
		tablaProductos.getColumnModel().getColumn(2).setMaxWidth(210);
		tablaProductos.getColumnModel().getColumn(3).setMinWidth(250);
		tablaProductos.getColumnModel().getColumn(3).setMaxWidth(250);		
		tablaProductos.getColumnModel().getColumn(4).setMinWidth(250);
		tablaProductos.getColumnModel().getColumn(4).setMaxWidth(250);
		/**
		 * EVENTOS DE LA VENTANA
		 */
		/**
		 * AL PULSAR EN EL BOTON DE CARRITO AUTOMATICAMENTE LES LLEVARA A LA VENTANA DE CARRITO
		 */
		carrito.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana_Carrito vcc =new Ventana_Carrito(usuario);
				vcc.setVisible(true);
				setVisible(false);
				vcc.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
				
			}
			
		});
		/**
		 * //Renderes: Para pintar la JTable. CAMBIAR LA VISUALIZACION DE LA TABLA.
		 */
		tablaProductos.setDefaultRenderer( Object.class, new DefaultTableCellRenderer() {
		Font fuente = new Font( "Arial", Font.PLAIN, 13);
		Font fuenteS = new Font("Arial",Font.BOLD,13);
		
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

				Component etiqueta = super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
				
				etiqueta.setFont(fuente);
				JLabel l = (JLabel) etiqueta;
				l.setHorizontalAlignment(JLabel.CENTER);
				
				if (isSelected){
				    etiqueta.setBackground (new Color(135, 206,235));
				    etiqueta.setFont(fuenteS);
				}else {
				    etiqueta.setBackground (new Color(224,255,255));
				}
			
				return etiqueta;
		}
		
	});
		/**
		 * AL SELECIONAR UN PRODUCTO DE LA TABLA DE PRODUCTOS SE VISULIZARA LA FOTO DE ESTE PRODUCTO 
		 */
		tablaProductos.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tablaProductos.getSelectedRow();
				Producto p =usando.get(i);
				int codigo=BD.getcodigoTienda(p);
				String url_tienda=BD.getURLFOTO_tienda(codigo);
				String url=BD.getURLFOTO(p);
				Image img= new ImageIcon(url).getImage();
				ImageIcon img2=new ImageIcon(img.getScaledInstance(220, 220, Image.SCALE_SMOOTH));
				lFoto.setIcon( img2 );
				Image img222= new ImageIcon(url_tienda).getImage();
				ImageIcon img22=new ImageIcon(img222.getScaledInstance(220, 220, Image.SCALE_SMOOTH));
				lFoto_tienda.setIcon( img22 );
			}
		});
		/**
		 * ESTE ES UNO DE LOS BOTONES QUE TIENE ESTA VENTANA Y ESTE BOTON LO QUE HACE ES BUSCAR UN PRODUCTO
		 * QUE CUMPLA ABSOLUTAMENTE TODAS LAS CARACTERISTICAS QUE TIENES MARCADA EN CADA UNO DE LOS FILTROS
		 */
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloDatosproductos.setRowCount(0);

				for(Producto p : BD.buscarProductoCaracteristicas(TipoProducto.valueOf(tipos.getSelectedItem().toString()) , Colorc.valueOf(c.getSelectedItem().toString()), preciobarra.getValue(),Talla.valueOf(tallas.getSelectedItem().toString()) )) {
					if(BD.cantidadProductos(p.getTipo(), p.getColor(), p.getPrecio(), p.getTalla()) != 0) {
						modeloDatosproductos.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio(), p.getColor(), p.getTalla(), p.getTipo()});
					}
				}
				usando=BD.buscarProductoCaracteristicas(TipoProducto.valueOf(tipos.getSelectedItem().toString()) , Colorc.valueOf(c.getSelectedItem().toString()), preciobarra.getValue(),Talla.valueOf(tallas.getSelectedItem().toString()) );
			}
		});
		/**
		 * ESTE ES OTRO DE LOS BOTONES QUE LO QUE HACE ES BUSCAR ABSOLUTAMENTE TODOS LOS PRODUCTOS
		 */
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloDatosproductos.setRowCount(0);
				for(Producto p : BD.getProductos()) {
					if(BD.cantidadProductos(p.getTipo(), p.getColor(), p.getPrecio(), p.getTalla()) != 0) {
						modeloDatosproductos.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio(), p.getColor(), p.getTalla(), p.getTipo()});
					}
				}
				usando=BD.getProductos();

				
			}
		});
		/**
		 * ESTE ES OTRO DE LOS BOTONES QUE LO QUE HACE ES BUSCAR TODOS LOS PRODUCTOS DEL TIPO MARCADO
		 */
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloDatosproductos.setRowCount(0); 
				
				for(Producto p : BD.buscarProductoTipo(TipoProducto.valueOf(tipos.getSelectedItem().toString()))) {
					if(BD.cantidadProductos(p.getTipo(), p.getColor(), p.getPrecio(), p.getTalla()) != 0) {
						modeloDatosproductos.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio(), p.getColor(), p.getTalla(), p.getTipo()});
					}
				}	
				usando=BD.buscarProductoTipo(TipoProducto.valueOf(tipos.getSelectedItem().toString()));

				
			}
		});
		/**
		 * ESTE ES UN BOTON QUE PULSANDOLO EN LA JTABLE SE VISUALIZARAN TODOS LOS PRODUCTOS DE UN DETERMINADO COLOR
		 */
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloDatosproductos.setRowCount(0);
				for(Producto p : BD.buscarProductoColor(Colorc.valueOf(c.getSelectedItem().toString()))) {
					if(BD.cantidadProductos(p.getTipo(), p.getColor(), p.getPrecio(), p.getTalla()) != 0) {
						modeloDatosproductos.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio(), p.getColor(), p.getTalla(), p.getTipo()});
					}
				}	
				usando=BD.buscarProductoColor(Colorc.valueOf(c.getSelectedItem().toString()));
			}
		});
		/**
		 * ESTE ES UN BOTON QUE PULSANDOLO TE MOSTRA TODOS LOS PRODUCTOS DE LA TALLA SELECCIONADA
		 */
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloDatosproductos.setRowCount(0);
				for(Producto p : BD.buscarProductoTalla(Talla.valueOf(tallas.getSelectedItem().toString()))) {
					if(BD.cantidadProductos(p.getTipo(), p.getColor(), p.getPrecio(), p.getTalla()) != 0) {
						modeloDatosproductos.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio(), p.getColor(), p.getTalla(), p.getTipo()});
					}
				}	
				usando=BD.buscarProductoTalla(Talla.valueOf(tallas.getSelectedItem().toString()));				
			}
		});
		/**
		 * CUANDO CAMBIAMOS EL PRECIO DE BARRA CAMBIAMOS EL VALOR DE ESTE QUE ESTA PUESTO ARRIBA
		 */
		preciobarra.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				valordebarra_l.setText(preciobarra.getValue()+"");
			}			
		});
		/**
		 * PULSANDO ESTE BOTON NOS LLEVARA A OTRA VENTANA
		 * NOS LLEVARA A UNA VENTANA QUE NOS MUESTRA DIFERENTES TIPOS DE COMBINACIONES DE PRODUCTOS QUE CUMPLAN UN PRECIO
		 */
		botonrecursividad.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana_productosdisponibles vd=new Ventana_productosdisponibles(usuario,0);
				vd.setVisible(true);
				setVisible(false);
				vd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				vd.setSize(700, 1000);
				vd.setLocationRelativeTo(null);
				//vd.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
				
			}
			
		});
		/**
		 * BOTON MEDIANTE EL CUAL AÑADIREMOS UN PRODUCTO A LA LISTA
		 */
		anyadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Producto p = new Producto((int)modeloDatosproductos.getValueAt(tablaProductos.getSelectedRow(), 0), (String)modeloDatosproductos.getValueAt(tablaProductos.getSelectedRow(), 1), (int)modeloDatosproductos.getValueAt(tablaProductos.getSelectedRow(), 2), (Colorc)modeloDatosproductos.getValueAt(tablaProductos.getSelectedRow(), 3), (Talla)modeloDatosproductos.getValueAt(tablaProductos.getSelectedRow(), 4), (TipoProducto)modeloDatosproductos.getValueAt(tablaProductos.getSelectedRow(), 5));
				productosComprados.add(p);
				int precio = (int) modeloDatosproductos.getValueAt(tablaProductos.getSelectedRow(), 2);
				pagar += p.getPrecio();
				

			}
		});
		
		arriba2.add(cliente);
		arriba1.add(botonrecursividad);
		arriba.add(arriba1);
		t2.add(tipo);
		centro_izda.add(t2);
		centro_izda.add(tipos);
		t3.add(color);
		centro_izda.add(t3);
		centro_izda.add(c);
		vl2.add(valordebarra_l2);
		centro_izda.add(vl2);
		centro_izda.add(valordebarra);
		t4.add(precio);
		centro_izda.add(t4);
		centro_izda.add(preciobarra);
		valordebarra.add(valordebarra_l);
		t1.add(talla);
		centro_izda.add(t1);
		centro_izda.add(tallas);
		centro_dcha.add(buscar);
		centro_dcha.add(b1);
		centro_dcha.add(b2);
		centro_dcha.add(b3);
		centro_dcha.add(b4);
		centro_dcha.add(carrito);
		centro_dcha.add(lFoto);
		centro_dcha.add(lFoto_tienda);
		b1.setBackground(Color.white);
		b2.setBackground(Color.white);
		b3.setBackground(Color.white);
		b4.setBackground(Color.white);
		centro.add(centro_izda);
		centro.add(centro_dcha);
		t5.add(informacion);
		abajo.add(t5);
		bañadir.add(anyadir);
		abajo.add(scrolTabla);
		abajo.add(bañadir);
		anyadir.setBackground(Color.white);
		Color color1= new Color(243,242,235);
		Color colori= new Color(224,228,204);
		Color colorj= new Color(228,251,243);
		this.add(arriba);
		this.add(centro);
		this.add(abajo);
		vl2.setBackground(colori);
		Color colo1= new Color(255,255,216);
		arriba.setBackground(colo1);
		valordebarra.setBackground(colori);
		arriba.setBackground(color1);
		abajo.setBackground(colo1);
		centro.setBackground(colo1);
		centro_izda.setBackground(colo1);
		centro_dcha.setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setBackground(colo1);
		Border b;
		b = BorderFactory.createLineBorder(Color.black,2);
		Border b2;
		b2 = BorderFactory.createLineBorder(Color.black);
		t1.setBorder(b2);
		informacion.setFont(fuentee);
		t1.setBackground(Color.white);
		t2.setBorder(b2);
		t2.setBackground(Color.white);
		t3.setBorder(b2);
		t3.setBackground(Color.white);
		t4.setBorder(b2);
		t4.setBackground(Color.white);
		abajo.setBorder(b2);
		centro_izda.setBorder(b);
		botonrecursividad.setBackground(Color.white);
		t5.setBackground(colorj);
		ImageIcon icono = new ImageIcon("FotosTiendas/deustoOutlet.jpg.png");
		this.setIconImage(icono.getImage());	
		
		try {
	        FondoSwing fondo = new FondoSwing(ImageIO.read(new File("Fotosproductos/fondo_ropa_arriba.jpg")));
	        arriba1.setBorder(fondo);
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
		try {
	        FondoSwing fondo = new FondoSwing(ImageIO.read(new File("FotosTiendas/deustoOutlet.jpg.png")));
	       centro_dcha.setBorder(fondo);
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
		
	}
	
	public static List<Producto> getCarrito(){
		return productosComprados;
	}
	public static int getPago() {
		return pagar;
	}
	
	
	
	
}