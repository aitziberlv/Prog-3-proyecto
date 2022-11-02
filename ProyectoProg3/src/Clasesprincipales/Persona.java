package Clasesprincipales;


public abstract class Persona {
	protected String nombre;
	protected String dni;
	protected String fechNa;
	protected String telefono;
	protected String direccion;
	protected String apellido;
	
	
	
	public Persona(String nombre, String dni, String fechNa, String telefono, String direccion, String apellido) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.fechNa = fechNa;
		this.telefono = telefono;
		this.direccion = direccion;
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFechNa() {
		return fechNa;
	}

	public void setFechNa(String fechNa) {
		this.fechNa = fechNa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", dni=" + dni + ", fechNa=" + fechNa + ", telefono=" + telefono
				+ ", direccion=" + direccion + "]";
	}

	public abstract int getEdad();
}
