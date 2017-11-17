package logical;

import java.util.ArrayList;

public class Doctor extends Persona {
	private String especialidad;
	private ArrayList<Cita> citas;
	
	public Doctor(String cedula, String nombre, String apellidos, String direccion, String telefono, char sexo,
			String fechaNacimiento, String tipoSangre,String especialidad) {
		super(cedula, nombre, apellidos, direccion, telefono, sexo, fechaNacimiento, tipoSangre);
		this.especialidad = especialidad;
		this.citas = new ArrayList<Cita>();
		//Creo el objeto ArrayList acá porque al crear un doctor se supon que el no tiene ninguna cita aún
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
