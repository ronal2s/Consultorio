package logical;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Consultorio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3805513106190494313L;
	/**
	 * 
	 */
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
		vacuna = new ArrayList<Vacuna>();
	}
	
	public static Consultorio getInstance()
	{
		if(consultorio == null)
		{
			consultorio = new Consultorio();
		}
		return consultorio;
	}
	
	public boolean login(String cedula, String clave)
	{
		int i=0;
		boolean correcto = false, encontrado = false;
		//Antes vamos a validar si fue el administrador que entró
		if((cedula.equalsIgnoreCase("ronal2s") && clave.equalsIgnoreCase("123")) || (cedula.equalsIgnoreCase("kiana") && clave.equalsIgnoreCase("123")))
		{
			encontrado = true;
			correcto = true;
		}
		try
		{
		while(i<empleados.size() || !encontrado) {
			if(empleados.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				//Si encontramos al empleado con la cedula...
				if(empleados.get(i).getClave().equals(clave))
				{
					correcto = true;
				}
				encontrado = true;
				
			}
			i++;
		}
		}
		catch(Exception e)
		{
			
		}
		i=0;
		try
		{
		while(i<profesionales.size() || !encontrado) {
			if(profesionales.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				//Si encontramos al empleado con la cedula...
				if(profesionales.get(i).getClave().equals(clave))
				{
					correcto = true;
				}
				encontrado = true;
				
			}
			i++;
		}
		}
		catch(Exception e)
		{
			
		}
		return correcto;
	}
	
	public String getTipousuario(String cedula)
	{
		String tipo ="";
		boolean encontrado = false;
		int i=0;
		
		if(cedula.equalsIgnoreCase("ronal2s"))
		{
			tipo = "Administrador";
			encontrado = true;
		}
		
		try
		{
		while(i<empleados.size() || !encontrado)
		{
			if(empleados.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				tipo = empleados.get(i).getCargo();
				encontrado = true;
			}
			i++;
		}
		}catch(Exception e)
		{
			
		}
		
		try
		{
			i=0;
			while(i<profesionales.size() || !encontrado)
			{
				if(profesionales.get(i).getCedula().equalsIgnoreCase(cedula))
				{
					tipo = "Profesional";
					encontrado = true;
				}
				i++;
			}	
		}catch(Exception e)
		{
			
		}
		return tipo;
	}
	
	public void imprimirHistorial(int posPaciente) throws IOException
	{
		File archivo = new File("Historial Paciente " + pacientes.get(posPaciente).getNombre() + " " + pacientes.get(posPaciente).getApellidos() + ".txt");
		FileWriter escritor = new FileWriter(archivo);
		escritor.close();
		escritor = new FileWriter(archivo,true);
		ArrayList<Consulta> c = pacientes.get(posPaciente).getHistoriaClinica();
		String texto = "";
		for (Consulta consulta : c) {
			texto = "Fecha: " + consulta.getFecha() + System.lineSeparator() +System.lineSeparator()+
					"Profesional: " + consulta.getDoctor().getNombre() + " " + consulta.getDoctor().getApellidos() + System.lineSeparator() + 
					"Sintomas:"+System.lineSeparator() + consulta.getSintomas() + System.lineSeparator() + System.lineSeparator()+"Exploración:"+System.lineSeparator() + consulta.getExploracion() + System.lineSeparator() + System.lineSeparator()+"Diagnósico:"+System.lineSeparator() + consulta.getDiagnostico() + System.lineSeparator() +
					System.lineSeparator()+"Enfermedad:"+System.lineSeparator() + consulta.getEnfermedad() + System.lineSeparator() +
					System.lineSeparator()+"Tratamiento:"+System.lineSeparator() + consulta.getTratamiento() + System.lineSeparator()+System.lineSeparator();
			escritor.write(texto);
					
					
		}
		escritor.close();
	}
	
	
	
	public void SaveMe() throws IOException
	{
		FileOutputStream archivo = new FileOutputStream("data.dat");
		ObjectOutputStream object = new ObjectOutputStream(archivo);
		object.writeObject(consultorio);
		archivo.close();
	}
	
	public void loadMe() throws IOException, ClassNotFoundException
	{
		FileInputStream archivo = new FileInputStream("data.dat");
		ObjectInputStream object = new ObjectInputStream(archivo);
		consultorio = (Consultorio) object.readObject();
		object.close();
	}
	
	public void guardarDatos() throws IOException
	{
		FileOutputStream archivoPaciente = new FileOutputStream("Pacientes.dat");
		FileOutputStream archivoProfesional = new FileOutputStream("Profesionales.dat");
		FileOutputStream archivoEmpleado = new FileOutputStream("Empleados.dat");
		FileOutputStream archivoCitas = new FileOutputStream("Citas.dat");
		FileOutputStream archivoVacunas = new FileOutputStream("Vacunas.dat");
		FileOutputStream archivoEnfermedades = new FileOutputStream("Enfermedades.dat");

		ObjectOutputStream pacienteObject = new ObjectOutputStream(archivoPaciente);
		ObjectOutputStream profesionalObject = new ObjectOutputStream(archivoProfesional);
		ObjectOutputStream empleadoObject = new ObjectOutputStream(archivoEmpleado);
		ObjectOutputStream citasObject = new ObjectOutputStream(archivoCitas);
		ObjectOutputStream vacunasObject = new ObjectOutputStream(archivoVacunas);
		ObjectOutputStream enfermedadesObject = new ObjectOutputStream(archivoEnfermedades);
		
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
		
		//Guardando vacunas
		vacunasObject.writeInt(vacuna.size());
		for (Vacuna v : vacuna) {
			vacunasObject.writeObject(v);
		}
		//Guardando enfermedades
		enfermedadesObject.writeInt(enfermedades.size());
		for (Enfermedad e : enfermedades) {
			enfermedadesObject.writeObject(e);
		}
		archivoEnfermedades.close();
		archivoVacunas.close();
		archivoCitas.close();
		archivoEmpleado.close();
		archivoPaciente.close();
		archivoProfesional.close();
		
	}
	
	public void cargarDatos() throws IOException, ClassNotFoundException
	{//RECORDAR ARREGLAR QUE EN EL PRIMER INICIO EL PROGRAMA SE VA A LA VERGA
		/*FileInputStream archivoVacunas=null, archivoEnfermedades=null;
		ObjectInputStream  enfermedadesObject=null;
										
					
		archivoEnfermedades = new FileInputStream("Enfermedades.dat");
		enfermedadesObject = new ObjectInputStream(archivoEnfermedades);*/

		/*cargarPacientes();
		cargarCitas();
		cargarDatos();
		cargarEmpleados();
		cargarProfesionales();
		cargarVacunas();*/
				
}

	public void cargarVacunas() throws ClassNotFoundException, IOException
	{
		FileInputStream archivoVacunas = new FileInputStream("Vacunas.dat");
		ObjectInputStream vacunasObject = new ObjectInputStream(archivoVacunas);

		//Cargando vacunas
		int n = vacunasObject.readInt();
		for (int i = 0; i < n; i++) {
			vacuna.add((Vacuna) vacunasObject.readObject());
		}
		System.out.println("Hay " + vacuna.size() + " vacunas.");

		archivoVacunas.close();

	}
	
	public void cargarCitas() throws ClassNotFoundException, IOException
	{
		FileInputStream archivoCitas = new FileInputStream("Citas.dat");

		ObjectInputStream citasObject = new ObjectInputStream(archivoCitas);

		//Cargando citas
		int n = citasObject.readInt();
		for (int i = 0; i < n; i++) {
			citas.add((Cita) citasObject.readObject());
		}
		System.out.println("Hay " + citas.size() + " citas.");

		archivoCitas.close();
	}
	
	public void cargarEmpleados() throws IOException, ClassNotFoundException
	{
		FileInputStream archivoEmpleados = new FileInputStream("Empleados.dat");
		ObjectInputStream empleadoObject = new ObjectInputStream(archivoEmpleados);

		//Cargando empleados
		int n = empleadoObject.readInt();
		for (int i = 0; i < n; i++) {
			empleados.add((Empleado) empleadoObject.readObject());
		}
		System.out.println("Hay " + empleados.size() + " empleados.");


		archivoEmpleados.close();
	}
	
	public void cargarPacientes() throws IOException, ClassNotFoundException
	{
	
		FileInputStream archivoPacientes = new FileInputStream("Pacientes.dat");;
		ObjectInputStream pacienteObject = new ObjectInputStream(archivoPacientes);

		int n = -1;
		//RECORDAR QUITAR TODOS ESTOS TRY CATCH DE ABAJO
		//Cargando pacientes
			n = pacienteObject.readInt();
			for (int i = 0; i < n; i++) 
			{
				pacientes.add((Paciente) pacienteObject.readObject());
			}
			System.out.println("Hay " + pacientes.size() + " pacientes.");
		

		archivoPacientes.close();
	}
	
	public void cargarProfesionales() throws IOException, ClassNotFoundException
	{
		FileInputStream archivoProfesionales = new FileInputStream("Profesionales.dat");
		ObjectInputStream profesionalObject = new ObjectInputStream(archivoProfesionales);		
		//Cargando profesionales
		int n = profesionalObject.readInt();
		for (int i = 0; i < n; i++) {
			profesionales.add((Profesional) profesionalObject.readObject());
		}
		System.out.println("Hay " + pacientes.size() + " profesionales.");

		archivoProfesionales.close();
	}

	
	
	/*
	 * 
	public void cargarDatos() throws IOException, ClassNotFoundException
	{//RECORDAR ARREGLAR QUE EN EL PRIMER INICIO EL PROGRAMA SE VA A LA VERGA
		FileInputStream archivoPacientes=null,archivoProfesionales=null,archivoEmpleados=null,archivoCitas=null,archivoVacunas=null, archivoEnfermedades=null;
		ObjectInputStream pacienteObject = null, profesionalObject=null,empleadoObject=null,citasObject=null, vacunasObject=null, enfermedadesObject=null;

				try
				{
				archivoPacientes = new FileInputStream("Pacientes.dat");
				}catch(IOException es)
				{
					FileOutputStream archivo  = new FileOutputStream("Pacientes.dat");
					ObjectOutputStream objeto = new ObjectOutputStream(archivo);
					archivo.close();
					System.out.print("ENTRA6");

				}
				
				try
				{
				archivoProfesionales = new FileInputStream("Profesionales.dat");
				}catch(IOException es)
				{
					FileOutputStream archivo  = new FileOutputStream("Profesionales.dat");
					ObjectOutputStream objeto = new ObjectOutputStream(archivo);
					archivo.close();
					System.out.print("ENTRA5");

				}
				
				try
				{
					archivoEmpleados = new FileInputStream("Empleados.dat");

				}catch(IOException es)
				{
					FileOutputStream archivo  = new FileOutputStream("Empleados.dat");
					ObjectOutputStream objeto = new ObjectOutputStream(archivo);
					archivo.close();
					System.out.print("ENTRA4");

				}
				
				try
				{
					archivoCitas = new FileInputStream("Citas.dat");

				}catch(IOException es)
				{
					FileOutputStream archivo  = new FileOutputStream("Citas.dat");
					ObjectOutputStream objeto = new ObjectOutputStream(archivo);
					archivo.close();
					System.out.print("ENTRA3");

				}
				
				try
				{
					archivoVacunas = new FileInputStream("Vacunas.dat");

				}catch(IOException es)
				{
					FileOutputStream archivo  = new FileOutputStream("Vacunas.dat");
					ObjectOutputStream objeto = new ObjectOutputStream(archivo);
					archivo.close();
					System.out.print("ENTRA2");

				}
				
				try
				{
					archivoEnfermedades = new FileInputStream("Enfermedades.dat");

				}catch(IOException es)
				{
					FileOutputStream archivo  = new FileOutputStream("Enfermedades.dat");
					ObjectOutputStream objeto = new ObjectOutputStream(archivo);
					archivo.close();
					System.out.print("ENTRA1");

				}
				
				
				try
				{
				pacienteObject = new ObjectInputStream(archivoPacientes);
				profesionalObject = new ObjectInputStream(archivoProfesionales);
				empleadoObject = new ObjectInputStream(archivoEmpleados);
				citasObject = new ObjectInputStream(archivoCitas);
				vacunasObject = new ObjectInputStream(archivoVacunas);
				enfermedadesObject = new ObjectInputStream(archivoEnfermedades);
				}catch(IOException e)
				{
					System.out.print("ENTRA");
					cargarDatos();
				}
			int n = -1;
			//RECORDAR QUITAR TODOS ESTOS TRY CATCH DE ABAJO
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
			
			try
			{
				//Cargando vacunas
				n = vacunasObject.readInt();
				for (int i = 0; i < n; i++) {
					vacuna.add((Vacuna) vacunasObject.readObject());
				}
				System.out.println("Hay " + vacuna.size() + " vacunas.");
				}catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
			archivoVacunas.close();
			archivoCitas.close();
			archivoEmpleados.close();
			archivoPacientes.close();
			archivoProfesionales.close();

	}*/
	 
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
	public void crearEnfermedad(String nombre, String tipo, /*ArrayList<String>*/String caracteristicas)
	{
		Enfermedad enfermedad = new Enfermedad(nombre, tipo, caracteristicas);
		enfermedades.add(enfermedad);
	}
	
	//Crear nueva vacuna
	public void crearVacuna ( String name, String tipo/*String dosis*/) {
		Vacuna vacuna = new Vacuna ( name, tipo );
		this.vacuna.add(vacuna);
	}
	
	//Asignar lista de vacunas a Paciente
	public void vacunaPaciente ( ArrayList<Vacuna> v, int ind ) {
		pacientes.get(ind).setVacunas(v);
	}
	
	public int buscarVacuna(String nombre)
	{
		int posicion = -1, n=0;
		boolean encontrado = false;
		
		while(n < vacuna.size() || !encontrado)
		{
			if(vacuna.get(n).getName().equalsIgnoreCase(nombre))
			{
				encontrado = true;
				posicion = n;
			}
			n++;
		}
		
		return posicion;
	}
	
	//Crear consulta para el paciente
	public void crearConsulta(String fecha, Paciente paciente, Profesional doctor, String sintomas,
			String exploracion, String diagnostico, String tratamiento, String enfermedad, Cita cita, boolean AHistorial)
	{
		Consulta consulta = new Consulta(fecha, paciente, doctor, sintomas, exploracion, diagnostico, tratamiento, enfermedad, cita);
		//Buscar el paciente en nuestra lista
		int posPaciente = buscarPaciente(paciente.getCedula());
		if(posPaciente != -1)
		{
			if(AHistorial)
			{
				ArrayList<Consulta> consultasPaciente = pacientes.get(posPaciente).getHistoriaClinica();
				consultasPaciente.add(consulta);
				pacientes.get(posPaciente).setHistoriaClinica(consultasPaciente);
				System.out.print("Copnsulta agregado al historial del paciente, tamaño de cintas: " + consultasPaciente.size());
			}
			
			String texto = String.format("Fecha:%s              Diagnostico:%s ", fecha, diagnostico);
			System.out.println(texto);
			
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
