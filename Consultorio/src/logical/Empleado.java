package logical;

public class Empleado extends Persona{

	private String cargo;
	private String clave;

	public Empleado(String cedula, String nombre, String apellidos, String direccion, String telefono, char sexo,
			String fechaNacimiento, String tipoSangre, String cargo,String clave) {
		super(cedula, nombre, apellidos, direccion, telefono, sexo, fechaNacimiento, tipoSangre);
		// TODO Auto-generated constructor stub
		this.cargo = cargo;
		this.clave = clave;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
