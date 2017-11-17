package logical;

import java.util.ArrayList;

public class Paciente extends Persona{

	private ArrayList<String> alergias, enfermedades;
	private ArrayList<Vacuna> vacunas;
	private ArrayList<Consulta> historiaClinica;
	private ArrayList<Consulta> consultas;
	private boolean estado;
	
	public Paciente(String cedula, String nombre, String apellidos, String direccion, String telefono, char sexo,
			String fechaNacimiento, String tipoSangre, ArrayList<String> alergias, ArrayList<String> enfermedades, ArrayList<Vacuna> vacunas,ArrayList<Consulta> historiaClinica) {
		super(cedula, nombre, apellidos, direccion, telefono, sexo, fechaNacimiento, tipoSangre);
		this.alergias = alergias;
		this.enfermedades = enfermedades;
		this.vacunas = vacunas;
		this.historiaClinica = historiaClinica;
		this.consultas = new ArrayList<Consulta>();
		// TODO Auto-generated constructor stub
	}
	
	

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}



	public void setConsultas(ArrayList<Consulta> consultas) {
		this.consultas = consultas;
	}



	public ArrayList<String> getAlergias() {
		return alergias;
	}

	public void setAlergias(ArrayList<String> alergias) {
		this.alergias = alergias;
	}

	public ArrayList<String> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<String> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(ArrayList<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public ArrayList<Consulta> getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(ArrayList<Consulta> historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
