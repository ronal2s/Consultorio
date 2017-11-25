package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Paciente extends Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alergias, antecedentes, observaciones;
	private ArrayList<Vacuna> vacunas;
	private ArrayList<Consulta> historiaClinica;
	private ArrayList<Consulta> consultas;
	//private boolean estado; la quito porque el profe dijo que para la clinica un paciente nunca está de alta o de baja, algo así dijo
	
	public Paciente(String cedula, String nombre, String apellidos, String direccion,String estadoCivil, String telefono, String movil, char sexo,
			String fechaNacimiento, String tipoSangre, int edad,  String alergias, String antecedentes, String observaciones) {
		super(cedula, nombre, apellidos, direccion, estadoCivil, telefono, movil, sexo, fechaNacimiento, tipoSangre, edad);
		this.alergias = alergias;
		this.antecedentes = antecedentes;
		this.vacunas = new ArrayList<Vacuna>();
		this.historiaClinica = new ArrayList<Consulta>();
		this.consultas = new ArrayList<Consulta>();
		this.observaciones = observaciones;
		// TODO Auto-generated constructor stub
	}
	
	

	public String getAntecedentes() {
		return antecedentes;
	}



	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}



	public void setConsultas(ArrayList<Consulta> consultas) {
		this.consultas = consultas;
	}



	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	/*public String getEnfermedades() {
		return antecedentes;
	}

	public void setEnfermedades(String enfermedades) {
		this.antecedentes = enfermedades;
	}*/

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
		
}
