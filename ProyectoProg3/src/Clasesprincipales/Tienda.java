package Clasesprincipales;

import java.util.ArrayList;

public class Tienda {
	private String codigo ="";
	private String nombre ="";
	private Franquicia franquicia=Franquicia.BERSHKA;
	private ArrayList<Producto> compras = new ArrayList<Producto>();
	
	
	public Tienda(String codigo, String nombre, Franquicia franquicia, int empleado, ArrayList<Empleado> empleados,
			ArrayList<Producto> compras) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.franquicia = franquicia;
		this.compras = compras;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		return "Tienda [codigo=" + codigo + ", nombre=" + nombre + ", franquicia=" + franquicia + ", compras=" + compras
				+ "]";
	}

	
	
	
	
	

}
