package logical;

public class Empleado extends Persona{

	private String cargo;
	
	public Empleado(String cedula, String nombre, String apellidos, String direccion, String telefono, char sexo,
			String fechaNacimiento, String tipoSangre, String cargo) {
		super(cedula, nombre, apellidos, direccion, telefono, sexo, fechaNacimiento, tipoSangre);
		// TODO Auto-generated constructor stub
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
