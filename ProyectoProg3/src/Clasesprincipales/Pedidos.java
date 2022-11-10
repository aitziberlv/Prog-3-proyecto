package Clasesprincipales;

import java.util.ArrayList;

public class Pedidos {
	
	private int codigo;
	public static int cont = 0;
	private ArrayList<Producto> lista_pedidos = new ArrayList<>();
	
	public Pedidos(ArrayList<Producto> lista_pedidos, int codigo) {
		super();
		this.lista_pedidos = lista_pedidos;
		this.codigo = cont++;
	}
	
	public ArrayList<Producto> getLista_pedidos() {
		return lista_pedidos;
	}

	public void setLista_pedidos(ArrayList<Producto> lista_pedidos) {
		this.lista_pedidos = lista_pedidos;
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Pedidos [codigo=" + codigo + ", lista_pedidos=" + lista_pedidos + "]";
	}


}
