package logical;

public class Cita {
	private Paciente paciente;
	private String descripcion;
	private String sala;
	private Profesional doctor;
	private String tipo;
	private String fecha;
	private String hora;
	private double duracion;
	private String nota;
	
	public Cita(Paciente paciente, String descripcion, String sala, Profesional doctor, String tipo, String fecha,
			String hora, double duracion, String nota) {
		super();
		this.paciente = paciente;
		this.descripcion = descripcion;
		this.sala = sala;
		this.doctor = doctor;
		this.tipo = tipo;
		this.fecha = fecha;
		this.hora = hora;
		this.duracion = duracion;
		this.nota = nota;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Profesional getDoctor() {
		return doctor;
	}

	public void setDoctor(Profesional doctor) {
		this.doctor = doctor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}
