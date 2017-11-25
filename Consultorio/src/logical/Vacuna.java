package logical;

import java.util.ArrayList;

public class Vacuna {
	private String tipo;//Tipo
	private ArrayList<String> dosis;//Lista de fechas dosis asignada
	public String ELIMINAESTALINEAAVER2;
	
	
	public Vacuna(String tipo, ArrayList<String> lista ) {
		super();
		this.tipo = tipo;
		this.dosis = lista;
	}
	
	public void addDosis ( String v ) {
		dosis.add(v);
	}

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
