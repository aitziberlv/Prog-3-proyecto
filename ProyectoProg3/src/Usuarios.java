import java.util.ArrayList;

public class Usuarios {
	private String nombre="";
	private String contraseña="";
	private ArrayList<Pedidos> pedidos_hechos=new ArrayList<>();
	/**
	 * @author aiitz
	 * Ahi lo que va a aparecer es una lista de los pedidos que ya has realizado.
	 */
	private ArrayList<Compras> nuevo_pedido= new ArrayList<>();
	/**
	 * @author aiitz
	 * Esto se trataria de una lista de el pedido nuevo que queremos realizar.
	 */
	public Usuarios(String nombre, String contraseña, ArrayList<Pedidos> pedidos_hechos,
			ArrayList<Compras> nuevo_pedido) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.pedidos_hechos = pedidos_hechos;
		this.nuevo_pedido = nuevo_pedido;
	}
	
	public Usuarios() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public ArrayList<Pedidos> getPedidos_hechos() {
		return pedidos_hechos;
	}

	public void setPedidos_hechos(ArrayList<Pedidos> pedidos_hechos) {
		this.pedidos_hechos = pedidos_hechos;
	}

	public ArrayList<Compras> getNuevo_pedido() {
		return nuevo_pedido;
	}

	public void setNuevo_pedido(ArrayList<Compras> nuevo_pedido) {
		this.nuevo_pedido = nuevo_pedido;
	}

	@Override
	public String toString() {
		return "Usuarios [nombre=" + nombre + ", contraseña=" + contraseña + ", pedidos_hechos=" + pedidos_hechos
				+ ", nuevo_pedido=" + nuevo_pedido + "]";
	}
	
	
	

}
