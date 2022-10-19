
public abstract class Persona {
	protected String nombre;
	protected String dni;
	protected String fechNa;
	protected String telefono;
	protected String direccion;
	
	
	public Persona(String nombre, String dni, String fechNa) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.fechNa = fechNa;
	}
	
	public abstract int getEdad();
}
