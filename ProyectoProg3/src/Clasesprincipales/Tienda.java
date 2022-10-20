package Clasesprincipales;

import java.util.ArrayList;

public class Tienda {
	private String codigo ="";
	private String nombre ="";
	private Franquicia franquicia=Franquicia.BERSHKA;
	private int empleado;
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	private ArrayList<Compra> compras = new ArrayList<Compra>();
	
	
	public Tienda(String codigo, String nombre, Franquicia franquicia, int empleado, ArrayList<Empleado> empleados,
			ArrayList<Compra> compras) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.franquicia = franquicia;
		this.empleado = empleado;
		this.empleados = empleados;
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
	public int getEmpleado() {
		return empleado;
	}
	public void setEmpleado(int empleado) {
		this.empleado = empleado;
	}
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}
	public ArrayList<Compra> getCompras() {
		return compras;
	}
	public void setCompras(ArrayList<Compra> compras) {
		this.compras = compras;
	}
	@Override
	public String toString() {
		return "Tienda [codigo=" + codigo + ", nombre=" + nombre + ", franquicia=" + franquicia + ", empleado="
				+ empleado + ", empleados=" + empleados + "]";
	}
	
	
	
	

}
