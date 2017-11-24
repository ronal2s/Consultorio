package logical;

import java.util.ArrayList;

public class Vacuna {
	private String tipo;//Tipo
	private ArrayList<String> dosis;//Lista de dosis
	
	
	public Vacuna(String tipo, ArrayList<String> lista, boolean estado) {
		super();
		this.tipo = tipo;
		this.dosis = lista;
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
