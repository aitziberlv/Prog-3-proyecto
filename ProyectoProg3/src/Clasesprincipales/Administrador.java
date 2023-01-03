package Clasesprincipales;

public class Administrador extends Persona{
	private Habilidad hab;
	
	public Administrador(String contraseña, String usuario, String nombre, String fechNa, String telefono,
			String direccion, String apellido, Habilidad hab) {
		super(contraseña, usuario, nombre, fechNa, telefono, direccion, apellido);
		this.hab = hab;
	}

	public Administrador(String contraseña, String usuario, String nombre, String fechNa, String telefono,
			String direccion, String apellido) {
		super(contraseña, usuario, nombre, fechNa, telefono, direccion, apellido);
		// TODO Auto-generated constructor stub
	}

	public Habilidad getHab() {
		return hab;
	}

	public void setHab(Habilidad hab) {
		this.hab = hab;
	}

	@Override
	public String toString() {
		return "Administrador [hab=" + hab + "]";
	}

	@Override
	public String getbienveido() {
		// TODO Auto-generated method stub
		return "Administrador:" + this.getNombre()+" " + this.getApellido()+ " de la cuenta de DeustoOutlet";
	}
	
}
