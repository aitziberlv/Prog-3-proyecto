package Clasesprincipales;


public class Empleado extends Persona{
	private double salario;
	
	

	public Empleado(String nombre, String dni, String fechNa, String telefono, String direccion, String apellido,
			double salario) {
		super(nombre, dni, fechNa, telefono, direccion, apellido);
		this.salario = salario;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public int getEdad() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
