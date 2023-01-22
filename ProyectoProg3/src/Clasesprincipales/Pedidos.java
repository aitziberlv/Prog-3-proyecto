package Clasesprincipales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedidos implements Serializable{
	
	private int codigo;
	private List<Producto> lista_pedidos = new ArrayList<>();
	
	public Pedidos(List<Producto> lista_pedidos, int codigo) {
		super();
		this.lista_pedidos = lista_pedidos;
		this.codigo = codigo;
		
	}
	public Pedidos(ArrayList<Producto> lista_pedidos) {
		super();
		this.lista_pedidos = lista_pedidos;
		
		
	}
	public List<Producto> getLista_pedidos() {
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
