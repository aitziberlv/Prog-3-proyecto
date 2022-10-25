package Clasesprincipales;

import java.util.ArrayList;

public class Pedidos {
	private ArrayList<Compra> lista_pedidos=new ArrayList<>();
	public Pedidos(ArrayList<Compra> lista_pedidos) {
		super();
		this.lista_pedidos = lista_pedidos;
	}
	
	public ArrayList<Compra> getLista_pedidos() {
		return lista_pedidos;
	}

	public void setLista_pedidos(ArrayList<Compra> lista_pedidos) {
		this.lista_pedidos = lista_pedidos;
	}

	@Override
	public String toString() {
		return "Pedidos [lista_pedidos=" + lista_pedidos + "]";
	}

	

}
