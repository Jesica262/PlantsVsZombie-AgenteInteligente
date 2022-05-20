package frsf.cidisi.exercise.plantsVsZombies.search;

public class Girasol {

	private int id;
	private int tipo;
	private int ciclo;
	private int contador;
	private int row;

	public Girasol(int id, int ciclo, int contador, int row, int i) {
		super();
		this.id = id;
		this.tipo = i;
		this.ciclo = ciclo;
		this.contador = contador;
		this.row = row; 
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getContador() {
		return contador;
	}


	public void setContador(int contador) {
		this.contador = contador;
	}


	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
