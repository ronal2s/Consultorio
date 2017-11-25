package logical;

public class Empleado extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cargo;
	private String clave;

	public Empleado(String cedula, String nombre, String apellidos, String direccion,String estadoCivil, String telefono, String movil, char sexo,
			String fechaNacimiento, String tipoSangre, int edad, String cargo,String clave) {
		super(cedula, nombre, apellidos, direccion, estadoCivil, telefono,movil,  sexo, fechaNacimiento, tipoSangre, edad);
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
