package logical;

import java.util.ArrayList;

public class Vacuna {
	private String tipo;//Tipo
	private ArrayList<String> dosis;//Lista de dosis
	private boolean estado;//Si la tiene puesta o no
	public String ELIMINAESTALINEAAVER;
	
	
	public Vacuna(String tipo, ArrayList<String> lista, boolean estado) {
		super();
		this.tipo = tipo;
		this.dosis = lista;
		this.estado = estado;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
