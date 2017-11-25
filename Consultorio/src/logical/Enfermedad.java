package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Enfermedad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String tipo;
	private ArrayList<String> caracteristicas;
	//Faltan más datos de la enfermedad supongo
	public Enfermedad(String nombre, String tipo, ArrayList<String> caracteristicas) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.caracteristicas = caracteristicas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ArrayList<String> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(ArrayList<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	
	
}
