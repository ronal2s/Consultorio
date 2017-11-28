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
	private ArrayList<Profesional> profesionales;
	private ArrayList<Empleado> empleados;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<Cita> citas;
	private static Consultorio consultorio;
	private ArrayList<Vacuna> vacuna;
	private ArrayList<Usuario> usuario;
	
	private Consultorio()
	{
		pacientes = new ArrayList<Paciente>();
		profesionales = new ArrayList<Profesional>();
		empleados = new ArrayList<Empleado>();
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
		FileOutputStream archivoPaciente = new FileOutputStream("Pacientes.dat");
		FileOutputStream archivoProfesional = new FileOutputStream("Profesionales.dat");
		FileOutputStream archivoEmpleado = new FileOutputStream("Empleados.dat");
		FileOutputStream archivoCitas = new FileOutputStream("Citas.dat");
		ObjectOutputStream pacienteObject = new ObjectOutputStream(archivoPaciente);
		ObjectOutputStream profesionalObject = new ObjectOutputStream(archivoProfesional);
		ObjectOutputStream empleadoObject = new ObjectOutputStream(archivoEmpleado);
		ObjectOutputStream citasObject = new ObjectOutputStream(archivoCitas);

		//Guardando pacientes
		pacienteObject.writeInt(pacientes.size());
		for (Paciente p : pacientes) {
			pacienteObject.writeObject(p);
		}
		//Guardando profesionlaes
		profesionalObject.writeInt(profesionales.size());
		for (Profesional d : profesionales) {
			profesionalObject.writeObject(d);
		}
		//Guardando empleados
		empleadoObject.writeInt(empleados.size());
		for (Empleado p : empleados) {
			empleadoObject.writeObject(p);
		}
		//Guardando citas
		citasObject.writeInt(citas.size());
		for (Cita c : citas) {
			citasObject.writeObject(c);
		}
		archivoCitas.close();
		archivoEmpleado.close();
		archivoPaciente.close();
		archivoProfesional.close();
		
	}
	
	public void cargarDatos() throws IOException, ClassNotFoundException
	{

			FileInputStream archivoPacientes = new FileInputStream("Pacientes.dat");
			FileInputStream archivoProfesionales = new FileInputStream("Profesionales.dat");
			FileInputStream archivoEmpleados = new FileInputStream("Empleados.dat");
			FileInputStream archivoCitas = new FileInputStream("Citas.dat");
			ObjectInputStream pacienteObject = new ObjectInputStream(archivoPacientes);
			ObjectInputStream profesionalObject = new ObjectInputStream(archivoProfesionales);
			ObjectInputStream empleadoObject = new ObjectInputStream(archivoEmpleados);
			ObjectInputStream citasObject = new ObjectInputStream(archivoCitas);
			int n = -1;
			try
			{
				//Cargando pacientes
				n = pacienteObject.readInt();
				for (int i = 0; i < n; i++) 
				{
					pacientes.add((Paciente) pacienteObject.readObject());
				}
				System.out.println("Hay " + pacientes.size() + " pacientes.");
			}catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				//Cargando profesionales
				n = profesionalObject.readInt();
				for (int i = 0; i < n; i++) {
					profesionales.add((Profesional) profesionalObject.readObject());
				}
				System.out.println("Hay " + pacientes.size() + " profesionales.");
				}catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
			//Cargando empleados
			n = empleadoObject.readInt();
			for (int i = 0; i < n; i++) {
				empleados.add((Empleado) empleadoObject.readObject());
			}
			System.out.println("Hay " + empleados.size() + " empleados.");
			}catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				//Cargando citas
				n = citasObject.readInt();
				for (int i = 0; i < n; i++) {
					citas.add((Cita) citasObject.readObject());
				}
				System.out.println("Hay " + citas.size() + " citas.");
				}catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
			archivoCitas.close();
			archivoEmpleados.close();
			archivoPacientes.close();
			archivoProfesionales.close();

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
		System.out.println("Nombre: " + nombre );
		System.out.println("Apellido: " + apellidos);
		System.out.println("Direccion: " + direccion);
		System.out.println("Telefono: " + telefono);
		System.out.println("Estado civil: " + estadoCivil);
		System.out.println("Movil: " + movil);
		System.out.println("FechaNac: " + fechaNacimiento);
		System.out.println("TipoSangre: " + tipoSangre);
		System.out.println("Cargo: " + empleado.getCargo());
	}
	//Agregar doctor
	public void crearProfesional(String cedula, String nombre, String apellidos, String direccion, String estadoCivil,String telefono, String movil, char sexo, 
			String fechaNacimiento, String tipoSangre, int edad, String especialidad, String clave)
	{
		Profesional doctor = new Profesional(cedula, nombre, apellidos, direccion, estadoCivil, telefono, movil, sexo, fechaNacimiento, tipoSangre, edad, especialidad, clave);
		profesionales.add(doctor);
		System.out.println("Nombre: " + nombre );
		System.out.println("Apellido: " + apellidos);
		System.out.println("Direccion: " + direccion);
		System.out.println("Telefono: " + telefono);
		System.out.println("Estado civil: " + estadoCivil);
		System.out.println("Movil: " + movil);
		System.out.println("FechaNac: " + fechaNacimiento);
		System.out.println("TipoSangre: " + tipoSangre);
		System.out.println("Especialidad: " + especialidad);

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
	public void crearConsulta(String fecha, Paciente paciente, Profesional doctor, ArrayList<String> sintomas, String anamnesis,
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
	public void crearCita(Paciente paciente, String descripcion, String sala, Profesional doctor, String tipo, String fecha, String duracion, String hora,String nota){
		
		int posPaciente = buscarPaciente(paciente.getCedula());
		if(posPaciente != -1)
		{
			Cita cita = new Cita(pacientes.get(posPaciente), descripcion, sala, doctor, tipo, fecha, duracion, hora,nota);
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
				if(pacientes.get(i).getCedula().equalsIgnoreCase(cedula))
				{
					posicion = i;
				}
				i++;
		}
		return posicion;
	}
	//Buscar Doctor
	public int buscarProfesional(String cedula)
	{
		int posicion = -1;
		int i=0;
		//A este while en algún momento habrá que ponerle un try catch	s
		while(i<profesionales.size() || posicion == -1)
		{
			if(profesionales.get(i).getCedula().equalsIgnoreCase(cedula))
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
	
	public void sustituirProfesional(int posProfesionalDesactualizado, Profesional profesionalActualizado)
	{
		profesionales.get(posProfesionalDesactualizado).setCedula(profesionalActualizado.getCedula());
		profesionales.get(posProfesionalDesactualizado).setNombre(profesionalActualizado.getNombre());
		profesionales.get(posProfesionalDesactualizado).setApellidos(profesionalActualizado.getApellidos());
		profesionales.get(posProfesionalDesactualizado).setDireccion(profesionalActualizado.getDireccion());
		profesionales.get(posProfesionalDesactualizado).setTelefono(profesionalActualizado.getTelefono());
		profesionales.get(posProfesionalDesactualizado).setMovil(profesionalActualizado.getMovil());
		profesionales.get(posProfesionalDesactualizado).setEdad(profesionalActualizado.getEdad());
		profesionales.get(posProfesionalDesactualizado).setFechaNacimiento(profesionalActualizado.getFechaNacimiento());
		profesionales.get(posProfesionalDesactualizado).setSexo(profesionalActualizado.getSexo());
		profesionales.get(posProfesionalDesactualizado).setEstadoCivil(profesionalActualizado.getEstadoCivil());
		profesionales.get(posProfesionalDesactualizado).setTipoSangre(profesionalActualizado.getTipoSangre());
		profesionales.get(posProfesionalDesactualizado).setEspecialidad(profesionalActualizado.getEspecialidad());
		profesionales.get(posProfesionalDesactualizado).setCitas(profesionalActualizado.getCitas());
		profesionales.get(posProfesionalDesactualizado).setClave(profesionalActualizado.getClave());
	}
	
	public void sustituirEmpleado(int posEmpleadoDesactualizado, Empleado empleadoActualizado)
	{
		empleados.get(posEmpleadoDesactualizado).setCedula(empleadoActualizado.getCedula());
		empleados.get(posEmpleadoDesactualizado).setNombre(empleadoActualizado.getNombre());
		empleados.get(posEmpleadoDesactualizado).setApellidos(empleadoActualizado.getApellidos());
		empleados.get(posEmpleadoDesactualizado).setDireccion(empleadoActualizado.getDireccion());
		empleados.get(posEmpleadoDesactualizado).setTelefono(empleadoActualizado.getTelefono());
		empleados.get(posEmpleadoDesactualizado).setMovil(empleadoActualizado.getMovil());
		empleados.get(posEmpleadoDesactualizado).setEdad(empleadoActualizado.getEdad());
		empleados.get(posEmpleadoDesactualizado).setFechaNacimiento(empleadoActualizado.getFechaNacimiento());
		empleados.get(posEmpleadoDesactualizado).setSexo(empleadoActualizado.getSexo());
		empleados.get(posEmpleadoDesactualizado).setEstadoCivil(empleadoActualizado.getEstadoCivil());
		empleados.get(posEmpleadoDesactualizado).setTipoSangre(empleadoActualizado.getTipoSangre());
		empleados.get(posEmpleadoDesactualizado).setCargo(empleadoActualizado.getCargo());
		empleados.get(posEmpleadoDesactualizado).setClave(empleadoActualizado.getClave());	
	}
	
	public void sustituirCita(int posCitaDesactualizado, Cita citaActualizado)
	{
		//Falta también sustituir la cita del Doctor
				int n=0,m=0;
				boolean modificado = true;
				Profesional p = null;
				while(n<profesionales.size() || !modificado)
				{
					p = profesionales.get(n);
					while (m< p.getCitas().size()) {
						if(p.getCitas().get(m).getDescripcion().equalsIgnoreCase(citas.get(posCitaDesactualizado).getDescripcion()))
						{
							if(p.getCitas().get(m).getHora().equalsIgnoreCase(citas.get(posCitaDesactualizado).getHora()))
							{
								//A ver, aquí pude solamente poner que si la CitaActual del doctor tiene la misma hora que la cita desactualizada 
								//entonces esa es la cita, pero puede que haya querido modificar esa cita porque chocaba la hora con otra
								//Así que mejor debería hacer más condiciones, pero eso se hará luego, por ahora así está bien
								profesionales.get(n).getCitas().get(m).setDescripcion(citaActualizado.getDescripcion());
								profesionales.get(n).getCitas().get(m).setDuracion(citaActualizado.getDuracion());
								profesionales.get(n).getCitas().get(m).setFecha(citaActualizado.getFecha());
								profesionales.get(n).getCitas().get(m).setHora(citaActualizado.getHora());
								profesionales.get(n).getCitas().get(m).setNota(citaActualizado.getNota());
								profesionales.get(n).getCitas().get(m).setPaciente(citaActualizado.getPaciente());
								profesionales.get(n).getCitas().get(m).setSala(citaActualizado.getSala());
								profesionales.get(n).getCitas().get(m).setTipo(citaActualizado.getTipo());
								modificado = true;
								break;
							}
						}
						m++;
					}
					n++;
				}
		citas.get(posCitaDesactualizado).setDescripcion(citaActualizado.getDescripcion());
		citas.get(posCitaDesactualizado).setDoctor(citaActualizado.getDoctor());
		citas.get(posCitaDesactualizado).setDuracion(citaActualizado.getDuracion());
		citas.get(posCitaDesactualizado).setFecha(citaActualizado.getFecha());
		citas.get(posCitaDesactualizado).setHora(citaActualizado.getHora());
		citas.get(posCitaDesactualizado).setNota(citaActualizado.getNota());
		citas.get(posCitaDesactualizado).setPaciente(citaActualizado.getPaciente());
		citas.get(posCitaDesactualizado).setSala(citaActualizado.getSala());
		citas.get(posCitaDesactualizado).setTipo(citaActualizado.getTipo());
		
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

	public ArrayList<Profesional> getProfesionales() {
		return profesionales;
	}

	public void setProfesionales(ArrayList<Profesional> profesionales) {
		this.profesionales = profesionales;
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
