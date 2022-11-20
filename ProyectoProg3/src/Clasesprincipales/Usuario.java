package Clasesprincipales;

import java.util.ArrayList;

public class Usuario extends Persona {
	
	
	private String contraseña="";
	private String usuario="";
	private ArrayList<Pedidos> pedidos_hechos=new ArrayList<>();

	

	public Usuario(String nombre, String dni, String fechNa, String telefono, String direccion, String apellido,
			String contraseña, String usuario) {
		super(nombre, dni, fechNa, telefono, direccion, apellido);
		this.contraseña = contraseña;
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	@Override
	public int getEdad() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
