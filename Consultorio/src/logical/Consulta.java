package logical;

import java.util.ArrayList;

public class Consulta {
	private String fecha;
	private Paciente paciente;
	private Doctor doctor;
	private ArrayList<String> sintomas;
	private String anamnesis;
	private String exploracion;
	private String diagnostico;
	private String tratamiento;
	private String enfermedad;
	private Cita cita;
	
	public Consulta(String fecha, Paciente paciente, Doctor doctor, ArrayList<String> sintomas, String anamnesis,
			String exploracion, String diagnostico, String tratamiento, String enfermedad, Cita cita) {
		super();
		this.fecha = fecha;
		this.paciente = paciente;
		this.doctor = doctor;
		this.sintomas = sintomas;
		this.anamnesis = anamnesis;
		this.exploracion = exploracion;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.enfermedad = enfermedad;
		this.cita = cita;
	}
	
	public void addSintoma(String s) {
		sintomas.add(s);
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public ArrayList<String> getSintomas() {
		return sintomas;
	}

	public void setSintomas(ArrayList<String> sintomas) {
		this.sintomas = sintomas;
	}

	public String getAnamnesis() {
		return anamnesis;
	}

	public void setAnamnesis(String anamnesis) {
		this.anamnesis = anamnesis;
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
