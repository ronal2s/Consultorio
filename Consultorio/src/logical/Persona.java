package logical;

public abstract class Persona {
	protected String cedula, nombre, apellidos, direccion, telefono;
	protected char sexo;
	protected String fechaNacimiento;
	protected String tipoSangre;
	
	public Persona(String cedula, String nombre, String apellidos, String direccion, String telefono, char sexo,
			String fechaNacimiento, String tipoSangre) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoSangre = tipoSangre;
	}

	
	public String getTipoSangre() {
		return tipoSangre;
	}


	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}


	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
}
