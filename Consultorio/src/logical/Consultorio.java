package logical;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Consultorio {
	private ArrayList<Paciente> pacientes;
	private ArrayList<Doctor> doctores;
	private ArrayList<Empleado> empleados;
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
	
	//Creando empleado nuevo
	//Realmente simplemente se podía poner crearEmpleado(Empleado empleado) y ya, lo hice así largo para luego en la parte visual tener que escribir menos código, creo
	public void crearEmpleado(String cedula, String nombre, String apellidos, String direccion, String telefono, char sexo, String fechaNacimiento, String tipoSangre, String cargo)
	{
		Empleado empleado = new Empleado(cedula, nombre, apellidos, direccion, telefono, sexo, fechaNacimiento, tipoSangre, cargo);
		empleados.add(empleado);
	}
	//Agregar doctor
	public void crearDoctor(String cedula, String nombre, String apellidos, String direccion, String telefono, char sexo, String fechaNacimiento, String tipoSangre, String especialidad)
	{
		Doctor doctor = new Doctor(cedula, nombre, apellidos, direccion, telefono, sexo, fechaNacimiento, tipoSangre, especialidad);
		doctores.add(doctor);
	}
	//Agregar enfermedad
	public void crearEnfermedad(String nombre, String tipo, ArrayList<String> caracteristicas)
	{
		Enfermedad enfermedad = new Enfermedad(nombre, tipo, caracteristicas);
		enfermedades.add(enfermedad);
	}
	//Crear consulta para el paciente
	public void crearConsulta(String fecha, Paciente paciente, Doctor doctor, ArrayList<String> sintomas, String anamnesis,
			String exploracion, String diagnostico, String tratamiento, String enfermedad, Cita cita)
	{
		Consulta consulta = new Consulta(fecha, paciente, doctor, sintomas, anamnesis, exploracion, diagnostico, tratamiento, enfermedad, cita);
		//Buscar el paciente en nuestra lista
		int posPaciente = buscarPaciente(paciente.getCedula());
		if(posPaciente != -1)
		{
			ArrayList<Consulta> consultasPaciente = pacientes.get(posPaciente).getConsultas();
			consultasPaciente.add(consulta);
			pacientes.get(posPaciente).setConsultas(consultasPaciente);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Paciente no existe");
		}
		
	}
	//Crear cita
	public void crearCita(Paciente paciente, String descripcion, String sala, Doctor doctor, String tipo, String fecha,
			String hora, double duracion, String nota)
	{
		int posPaciente = buscarPaciente(paciente.getCedula());
		if(posPaciente != -1)
		{
			Cita cita = new Cita(pacientes.get(posPaciente), descripcion, sala, doctor, tipo, fecha, hora, duracion, nota);
			citas.add(cita);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Paciente no existe");
		}
	}
	//Buscar paciente
	public int buscarPaciente(String cedula)
	{
		int posicion = -1;
		int i=0;
		//A este while en algún momento habrá que ponerle un try catch	s
		while(i<pacientes.size() || posicion == -1)
		{
			if(pacientes.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				posicion = i;
			}
			i++;
		}
		return posicion;
	}
	//Buscar Doctor
	public int buscarDoctor(String cedula)
	{
		int posicion = -1;
		int i=0;
		//A este while en algún momento habrá que ponerle un try catch	s
		while(i<doctores.size() || posicion == -1)
		{
			if(doctores.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				posicion = i;
			}
			i++;
		}
		return posicion;
	}
	//Buscar Empleado
	public int buscarEmpleado(String cedula)
	{
		int posicion = -1;
		int i=0;
		//A este while en algún momento habrá que ponerle un try catch	s
		while(i<empleados.size() || posicion == -1)
		{
			if(empleados.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				posicion = i;
			}
			i++;
		}
		return posicion;
	}
	
	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public ArrayList<Doctor> getDoctores() {
		return doctores;
	}

	public void setDoctores(ArrayList<Doctor> doctores) {
		this.doctores = doctores;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public ArrayList<Cita> getCitas() {
		return citas;
	}

	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}

	public static Consultorio getConsultorio() {
		return consultorio;
	}

	public static void setConsultorio(Consultorio consultorio) {
		Consultorio.consultorio = consultorio;
	}
	
}
