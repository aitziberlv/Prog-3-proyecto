package Clasesprincipales;

import java.util.ArrayList;

public class Usuario extends Persona {
	
	
	private String contraseña="";
	private ArrayList<Pedidos> pedidos_hechos=new ArrayList<>();

	public Usuario(String nombre, String dni, String fechNa, String contraseña, ArrayList<Pedidos> pedidos_hechos) {
		super(nombre, dni, fechNa);
		this.contraseña = contraseña;
		this.pedidos_hechos = pedidos_hechos;
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
