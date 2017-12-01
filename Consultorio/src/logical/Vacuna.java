package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Vacuna implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String tipo;//Tipo
	//private String dosis;
	private ArrayList<String> dosis;//Lista de fechas dosis asignada
	//public String ELIMINAESTALINEAAVER2;
	
	
	public Vacuna(String name, String tipo /*String dosis*/) {
		super();
		this.name = name;
		this.tipo = tipo;
		this.dosis = new ArrayList<String>();
		//this.dosis = dosis;
	}
	
	
	
	/*public void addDosis ( String v ) {
		dosis.add(v);
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}*/

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ArrayList<String> getLista() {
		return dosis;
	}

	public void setLista(ArrayList<String> lista) {
		this.dosis = lista;
	}	
}
