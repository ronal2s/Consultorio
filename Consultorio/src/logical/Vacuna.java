package logical;

import java.util.ArrayList;

public class Vacuna {
	private String tipo;//Tipo
<<<<<<< HEAD
	private ArrayList<String> dosis;//Lista de fechas dosis asignada
	public String ELIMINAESTALINEAAVER2;
=======
	private ArrayList<String> dosis;//Lista de dosis
>>>>>>> branch 'master' of https://github.com/ronal2s/Consultorio
	
	
	public Vacuna(String tipo, ArrayList<String> lista ) {
		super();
		this.tipo = tipo;
		this.dosis = lista;
<<<<<<< HEAD
	}
	
	public void addDosis ( String v ) {
		dosis.add(v);
=======
>>>>>>> branch 'master' of https://github.com/ronal2s/Consultorio
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
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/ronal2s/Consultorio
	
}
