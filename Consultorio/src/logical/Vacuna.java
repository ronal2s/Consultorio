package logical;

import java.util.ArrayList;

public class Vacuna {
	private String tipo;
	private ArrayList<String> lista;
	private boolean estado;
	
	public Vacuna(String tipo, ArrayList<String> lista, boolean estado) {
		super();
		this.tipo = tipo;
		this.lista = lista;
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ArrayList<String> getLista() {
		return lista;
	}

	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
