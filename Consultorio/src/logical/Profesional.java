package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Profesional extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String especialidad;
	private String clave;
	private ArrayList<Cita> citas;
	
	public Profesional(String cedula, String nombre, String apellidos, String direccion,String estadoCivil, String telefono, String movil, char sexo,
			String fechaNacimiento, String tipoSangre, int edad, String especialidad, String clave) {
		super(cedula, nombre, apellidos, direccion,estadoCivil,  telefono, movil, sexo, fechaNacimiento, tipoSangre, edad);
		this.especialidad = especialidad;
		this.citas = new ArrayList<Cita>();
		this.clave = clave;
		//Creo el objeto ArrayList acá porque al crear un doctor se supon que el no tiene ninguna cita aún
	}

	
	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public ArrayList<Cita> getCitas() {
		return citas;
	}

	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}
	
	
}
