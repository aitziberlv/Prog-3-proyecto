	package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import BD.BD;
import Clasesprincipales.Colorc;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
import Clasesprincipales.TipoProducto;
import Clasesprincipales.Usuario;
import Ventanasexternas.FondoSwing;

public class Ventana_Carrito extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Producto> usando;
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
	private JLabel lFoto;
	private JLabel producto;
	private JLabel carrito;
	private JLabel precio;
	private JLabel cantidad;
	
	private JButton borrar;
	private JButton añadir; //volver a la tienda y añadir mas productos.
	private JButton pagar;
	private JButton guardar; 
	private String usuario;
	private JPanel v;
	private DefaultListModel<Producto> mSelec;
	private JList<Producto> lSelec;
	private JScrollPane scrollista;
	ArrayList<Producto> pr=(ArrayList<Producto>) Ventana_Cliente.getCarrito();
	public Ventana_Carrito(String usuario) throws HeadlessException {
		super();
		this.usuario = usuario;
		inicializarVentana();
		
	}

	public Ventana_Carrito() {
		inicializarVentana();
	}

	private void inicializarVentana() {
		
		this.setSize(900, 700);
		setLocationRelativeTo(null);
		this.setTitle("DEUSTO OUTLET CARRITO");
		this.setLayout(new GridLayout(3,1));
		v=new JPanel();
		lFoto=new JLabel();
		titulo = new JPanel();
		centro = new JPanel();
		centro.setLayout(new GridLayout(1,2));
		centro_dcha = new JPanel();
		centro_iz = new JPanel();
		centro_iz.setLayout(new GridLayout(1,2));
		lista = new JPanel();
		frase = new JPanel();
		frase.setLayout(new BorderLayout());
		abajo1 = new JPanel();
		abajoIzq = new JPanel();
		abajoDer = new JPanel();
		abajo = new JPanel();
		abajo.setLayout(new GridLayout(3,2));

		scrollista=new JScrollPane(lSelec); 
		mSelec = new DefaultListModel<Producto>();
		lSelec = new JList<Producto>();
		
        scrollista.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollista.setViewportView(lSelec);
		lSelec.setModel(mSelec);
		
		
		for( int i=0; i<Ventana_Cliente.getCarrito().size(); i++) {
			mSelec.addElement(Ventana_Cliente.getCarrito().get(i));
			
		}
		
		usando=Ventana_Cliente.getCarrito();
		
		producto = new JLabel("Productos seleccionados:");
		carrito = new JLabel("CARRITO");
		precio = new JLabel("Total a pagar: " + Ventana_Cliente.getPago());
		

		
		borrar = new JButton("Eliminar producto");
		borrar.setForeground(Color.black);
		borrar.setBackground(Color.white);
		añadir = new JButton("Añadir nuevos productos");
		añadir.setForeground(Color.black);
		añadir.setBackground(Color.white);
		pagar = new JButton("Realizar pago");
		pagar.setForeground(Color.black);
		pagar.setBackground(Color.white);
		guardar = new JButton("Guardar datos");
		guardar.setForeground(Color.black);
		guardar.setBackground(Color.white);

		
		Border b;
		b = BorderFactory.createLineBorder(Color.black,2);
		centro.setBorder(b);
		borrar.addActionListener(new ActionListener( ){
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//if no hay producto seleccionado:
				if(lSelec.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione el producto que desea eliminar del carrito.","Error",JOptionPane.ERROR_MESSAGE);
				
				}
				else {
					//double precioOld = Integer.parseInt(lSelec.getSelectedValue());
					Ventana_Cliente.getCarrito().remove(lSelec.getSelectedValue());
					precio.setText("Total a pagar: " + (Ventana_Cliente.getPago() - lSelec.getSelectedValue().getPrecio()));
					Ventana_Cliente.pagar = Ventana_Cliente.pagar - lSelec.getSelectedValue().getPrecio();
					mSelec.removeElement(lSelec.getSelectedValue());					
					pr=(ArrayList<Producto>) Ventana_Cliente.getCarrito();
				}
				lFoto.setIcon(null);
				//si selecciona un producto: se elimina del pedido.
				
			}
			
		});
		
		lSelec.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=lSelec.getSelectedIndex();
				Producto p =usando.get(i);
				String url=BD.getURLFOTO(p);
				Image img= new ImageIcon(url).getImage();
				ImageIcon img2=new ImageIcon(img.getScaledInstance(220, 220, Image.SCALE_SMOOTH));
				lFoto.setIcon( img2 );
			}

		
			
		});
		
		añadir.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_Cliente vc = new Ventana_Cliente(usuario);
				vc.setVisible(true);
				setVisible(false);
				vc.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);

				
			}
			
		});
		
		pagar.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ventana_Pagar vp = new Ventana_Pagar();
				vp.setVisible(true);
				setVisible(false);
				vp.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);

				
			}
			
		});
		
		guardar.addActionListener(new ActionListener( ){

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String fecha = df.format(new Date());
				
				ArrayList<Producto> lp = new ArrayList<>();
				for(int indice = 0; indice < mSelec.getSize();indice++){
					lp.add(mSelec.getElementAt(indice));					
				}
				BD.InsertarPedido(BD.getDNIusuario(usuario), "NO finalizado", fecha, lp);
		
				JOptionPane.showMessageDialog( null, "Su compra ha sido guardada con éxito.");
				
			}
			
		});
		
		scrollista.setViewportView(lSelec);
		
		titulo.add(carrito);
		frase.add(producto, BorderLayout.CENTER);
		frase.add(v,BorderLayout.WEST);
		lista.add(lSelec, BorderLayout.NORTH);
		centro_dcha.add(precio);
		centro_dcha.add(lFoto);
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
		Font fuenteS = new Font("Arial",Font.BOLD,25);
		producto.setFont(fuenteS);
		Color color1= new Color(243,242,235);
		Color colori= new Color(224,228,204);
		Color colorj= new Color(228,251,243);
		frase.setBackground(colorj);
		centro_dcha.setBackground(colorj);
		v.setBackground(colorj);
		abajo1.setBackground(Color.WHITE);
		abajoIzq.setBackground(Color.WHITE);
		abajo.setBorder(b);
		//abajoDer.setBackground(Color.white);
		carrito.setFont(fuenteS);
		lista.setBackground(colorj);
		ImageIcon icono = new ImageIcon("FotosTiendas/deustoOutlet.jpg.png");
		this.setIconImage(icono.getImage());	
		try {
	        FondoSwing fondo = new FondoSwing(ImageIO.read(new File("FotosTiendas/cf.png")));
	        //JPanel panel = (JPanel) this.getContentPane();
	       titulo.setBorder(fondo);
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
		try {
	        FondoSwing fondo = new FondoSwing(ImageIO.read(new File("FotosTiendas/do2.png")));
	        //JPanel panel = (JPanel) this.getContentPane();
	       abajoDer.setBorder(fondo);
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	
	public static void main(String[] args) {
		Ventana_Carrito vca =new Ventana_Carrito();
		vca.setVisible(true);
		vca.setExtendedState(Ventana_Carrito.MAXIMIZED_BOTH);
		
	}
	

}
