package Clasesprincipales;

import java.io.Serializable;

public class Producto implements Informacion,Serializable{
	private int codigo = 0;
	private String nombre = "";
	private int precio = 0;
	private Colorc color = Colorc.BLANCO;
	private Talla talla = Talla.S;
	private TipoProducto tipo = TipoProducto.FALDA;
	private String url="";
	
	public Producto(String nombre, int precio, Colorc color, Talla talla, TipoProducto tipo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.color = color;
		this.talla = talla;
		this.tipo = tipo;
	}
	
	public Producto(int codigo, String nombre, int precio, Colorc color, Talla talla, TipoProducto tipo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.color = color;
		this.talla = talla;
		this.tipo = tipo;
	}
	

	public Producto(int codigo, String url) {
		super();
		this.codigo = codigo;
		this.url = url;
	}


	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Colorc getColor() {
		return color;
	}
	public void setColor(Colorc color) {
		this.color = color;
	}
	public Talla getTalla() {
		return talla;
	}
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	public TipoProducto getTipo() {
		return tipo;
	}
	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return nombre + ", " + tipo + ", " + color + ", " + talla + ", " + precio;
	}


	@Override
	public String conseguirInfo() {
		// TODO Auto-generated method stub
		return "Este Producto podría ser de 5 tallas difernetes o en vez de este color tener otro. ";
	}
	
	

}
