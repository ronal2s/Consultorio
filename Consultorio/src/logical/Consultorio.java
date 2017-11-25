package logical;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Consultorio {
	private ArrayList<Paciente> pacientes;
	private ArrayList<Doctor> doctores;
	private ArrayList<Empleado> empleados;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<Cita> citas;
	private static Consultorio consultorio;
	private ArrayList<Vacuna> vacuna;
	private ArrayList<Usuario> usuario;
	
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
	
	public void guardarDatos() throws IOException
	{
		FileOutputStream archivo = new FileOutputStream("Pacientes.dat");
		ObjectOutputStream pacienteObject = new ObjectOutputStream(archivo);
		//Guardando pacientes
		pacienteObject.writeInt(pacientes.size());
		for (Paciente p : pacientes) {
			pacienteObject.writeObject(p);
		}
		archivo.close();
		
	}
	
	public void cargarDatos() throws IOException, ClassNotFoundException
	{
		try
		{
			FileInputStream archivoPacientes = new FileInputStream("Pacientes.dat");
			ObjectInputStream pacienteObject = new ObjectInputStream(archivoPacientes);
			int n = pacienteObject.readInt();
			for (int i = 0; i < n; i++) 
			{
				pacientes.add((Paciente) pacienteObject.readObject());
			}
			System.out.println("Hay " + pacientes.size() + " pacientes.");
			archivoPacientes.close();
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Crear paciente
	public void crearPaciente(String cedula, String nombre, String apellidos, String direccion, String telefono, String estadoCivil,String movil, char sexo,
			String fechaNacimiento, String tipoSangre, int edad, String alergias, String antecedentes, String observaciones)
	{
		Paciente paciente = new Paciente(cedula, nombre, apellidos, direccion, estadoCivil, telefono, movil, sexo, fechaNacimiento, tipoSangre, edad, alergias, antecedentes, observaciones);
		pacientes.add(paciente);
		System.out.println("Nombre: " + nombre );
		System.out.println("Apellido: " + apellidos);
		System.out.println("Direccion: " + direccion);
		System.out.println("Telefono: " + telefono);
		System.out.println("Estado civil: " + estadoCivil);
		System.out.println("Movil: " + movil);
		System.out.println("FechaNac: " + fechaNacimiento);
		System.out.println("TipoSangre: " + tipoSangre);
		System.out.println("Alergias: " + alergias);
		System.out.println("Antecedentes: " + antecedentes);
		System.out.println("Observaciones: " + paciente.getObservaciones());
	}
	//Creando empleado nuevo
	//Realmente simplemente se podía poner crearEmpleado(Empleado empleado) y ya, lo hice así largo para luego en la parte visual tener que escribir menos código, creo
	public void crearEmpleado(String cedula, String nombre, String apellidos, String direccion, String estadoCivil,String telefono, String movil, char sexo, 
			String fechaNacimiento, String tipoSangre, int edad, String cargo, String clave)
	{
		Empleado empleado = new Empleado(cedula, nombre, apellidos, direccion, estadoCivil,telefono, movil,sexo, fechaNacimiento, tipoSangre, edad, cargo, clave);
		empleados.add(empleado);
	}
	//Agregar doctor
	public void crearProfesional(String cedula, String nombre, String apellidos, String direccion, String estadoCivil,String telefono, String movil, char sexo, 
			String fechaNacimiento, String tipoSangre, int edad, String especialidad, String clave)
	{
		Doctor doctor = new Doctor(cedula, nombre, apellidos, direccion, estadoCivil, telefono, movil, sexo, fechaNacimiento, tipoSangre, edad, especialidad, clave);
		doctores.add(doctor);
	}
	//Agregar enfermedad
	public void crearEnfermedad(String nombre, String tipo, ArrayList<String> caracteristicas)
	{
		Enfermedad enfermedad = new Enfermedad(nombre, tipo, caracteristicas);
		enfermedades.add(enfermedad);
	}
	
	//Crear nueva vacuna
	public void crearVacuna ( String tipo, ArrayList<String> lista) {
		Vacuna vacuna = new Vacuna ( tipo, lista );
		this.vacuna.add(vacuna);
	}
	
	//Asignar lista de vacunas a Paciente
	public void vacunaPaciente ( ArrayList<Vacuna> v, int ind ) {
		pacientes.get(ind).setVacunas(v);
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
			String hora, double duracion, String nota){
		
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
		
		while(i<pacientes.size())
		{
			//try
			//{
				if(pacientes.get(i).getCedula().equalsIgnoreCase(cedula))
				{
					posicion = i;
				}
				i++;

			//}catch(Exception e)
			//{
				//System.out.println("Busqueda de paciente: null");
				//break;
			//}

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
	
	public void sustituirPaciente(int posPacienteDesactualizado, Paciente pacienteActualizado)
	{
		pacientes.get(posPacienteDesactualizado).setCedula(pacienteActualizado.getCedula());
		pacientes.get(posPacienteDesactualizado).setNombre(pacienteActualizado.getNombre());
		pacientes.get(posPacienteDesactualizado).setApellidos(pacienteActualizado.getApellidos());
		pacientes.get(posPacienteDesactualizado).setDireccion(pacienteActualizado.getDireccion());
		pacientes.get(posPacienteDesactualizado).setTelefono(pacienteActualizado.getTelefono());
		pacientes.get(posPacienteDesactualizado).setMovil(pacienteActualizado.getMovil());
		pacientes.get(posPacienteDesactualizado).setAlergias(pacienteActualizado.getAlergias());
		pacientes.get(posPacienteDesactualizado).setAntecedentes(pacienteActualizado.getAntecedentes());
		pacientes.get(posPacienteDesactualizado).setObservaciones(pacienteActualizado.getObservaciones());
		pacientes.get(posPacienteDesactualizado).setEdad(pacienteActualizado.getEdad());
		pacientes.get(posPacienteDesactualizado).setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
		pacientes.get(posPacienteDesactualizado).setSexo(pacienteActualizado.getSexo());
		pacientes.get(posPacienteDesactualizado).setEstadoCivil(pacienteActualizado.getEstadoCivil());
		pacientes.get(posPacienteDesactualizado).setTipoSangre(pacienteActualizado.getTipoSangre());
		
		
	}
	
	
	
	public ArrayList<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(ArrayList<Usuario> usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Vacuna> getVacunas() {
		return vacuna;
	}

	public void setVacunas(ArrayList<Vacuna> vacunas) {
		this.vacuna = vacunas;
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
