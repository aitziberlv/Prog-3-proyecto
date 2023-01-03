package Clasesprincipales;

import java.util.ArrayList;

public class Usuario extends Persona {
	
	
	
	private ArrayList<Pedidos> pedidos_hechos=new ArrayList<>();

	

	

	public Usuario(String contraseña, String usuario, String nombre, String fechNa, String telefono, String direccion,
			String apellido, ArrayList<Pedidos> pedidos_hechos) {
		super(contraseña, usuario, nombre, fechNa, telefono, direccion, apellido);
		this.pedidos_hechos = pedidos_hechos;
	}





	public ArrayList<Pedidos> getPedidos_hechos() {
		return pedidos_hechos;
	}





	public void setPedidos_hechos(ArrayList<Pedidos> pedidos_hechos) {
		this.pedidos_hechos = pedidos_hechos;
	}





	@Override
	public String toString() {
		return "Usuario [pedidos_hechos=" + pedidos_hechos + "]";
	}





	@Override
	public String getbienveido() {
		// TODO Auto-generated method stub
		return "Bienvenido a la aplicacion, esta es la cuenta de " + this.getUsuario();
	}




//
//	@Override
//	public int getEdad() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
	
	

}
