package Clasesprincipales;

import java.io.Serializable;

public class Administrador extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * duracion en meses: con esto sabemos cuanto tiempo lleva ese administrador siendo administrador en la aplicacion 
	 */
	private double duracion;
	
	
	public Administrador(String contraseña, String usuario, String nombre, String dni, String fechNa, String telefono,
			String direccion, String apellido, double duracion) {
		super(contraseña, usuario, nombre, dni, fechNa, telefono, direccion, apellido);
		this.duracion=duracion;
	}

	public Administrador(String contraseña, String usuario, String nombre, String dni, String fechNa, String telefono,
			String direccion, String apellido) {
		super(contraseña, usuario, nombre, dni, fechNa, telefono, direccion, apellido);
		// TODO Auto-generated constructor stub
	}

	

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Administrador: " + this.getNombre()+" " + this.getApellido();
	}

	@Override
	public String getbienveido() {
		// TODO Auto-generated method stub
		return "Administrador: " + this.getNombre()+" " + this.getApellido()+ " de la cuenta de DeustoOutlet";
	}
	
}
