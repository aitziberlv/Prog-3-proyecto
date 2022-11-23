package Clasesprincipales;

import java.util.ArrayList;

public class Tienda {
	private int codigo =1;
	
	private Franquicia franquicia=Franquicia.BERSHKA;
	private ArrayList<Producto> compras = new ArrayList<Producto>();
	
	
	public Tienda(int codigo, Franquicia franquicia, ArrayList<Producto> compras) {
		super();
		this.codigo = codigo;
		
		this.franquicia = franquicia;
		this.compras = compras;
	}
	public Tienda(int codigo, Franquicia franquicia) {
		super();
		this.codigo = codigo;
		
		this.franquicia = franquicia;
		
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Franquicia getFranquicia() {
		return franquicia;
	}
	public void setFranquicia(Franquicia franquicia) {
		this.franquicia = franquicia;
	}
	
	public ArrayList<Producto> getCompras() {
		return compras;
	}
	public void setCompras(ArrayList<Producto> compras) {
		this.compras = compras;
	}
	@Override
	public String toString() {
		return "Tienda [codigo=" + codigo +  ", franquicia=" + franquicia + ", compras=" + compras
				+ "]";
	}

	
	
	
	
	

}
