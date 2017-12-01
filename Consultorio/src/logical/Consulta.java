package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Consulta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha;
	private Paciente paciente;
	private Profesional doctor;
	private String sintomas;
	//private String anamnesis;
	private String exploracion;
	private String diagnostico;
	private String tratamiento;
	private String enfermedad;
	private Cita cita;
	
	public Consulta(String fecha, Paciente paciente, Profesional doctor, String sintomas,
			String exploracion, String diagnostico, String tratamiento, String enfermedad, Cita cita) {
		super();
		this.fecha = fecha;
		this.paciente = paciente;
		this.doctor = doctor;
		this.sintomas = sintomas;
		//this.anamnesis = anamnesis;
		this.exploracion = exploracion;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.enfermedad = enfermedad;
		this.cita = cita;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Profesional getDoctor() {
		return doctor;
	}

	public void setDoctor(Profesional doctor) {
		this.doctor = doctor;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}



	public String getExploracion() {
		return exploracion;
	}

	public void setExploracion(String exploracion) {
		this.exploracion = exploracion;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}
	
	
	
	
}
