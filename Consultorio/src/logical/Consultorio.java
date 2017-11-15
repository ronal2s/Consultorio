package logical;

import java.util.ArrayList;

public class Consultorio {
	private ArrayList<Paciente> pacientes;
	private ArrayList<Doctor> doctores;
	//private ArrayList<Secretaria> secretaria;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<Cita> citas;
	private static Consultorio consultorio;
	
	private Consultorio()
	{
		pacientes = new ArrayList<Paciente>();
		doctores = new ArrayList<Doctor>();
		enfermedades = new ArrayList<Enfermedad>();
		citas = new ArrayList<Cita>();
	}
	
	public static Consultorio getInstance()
	{
		if(consultorio == null)
		{
			consultorio = new Consultorio();
		}
		return consultorio;
	}
	
}
