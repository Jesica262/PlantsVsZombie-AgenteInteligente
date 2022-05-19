package frsf.cidisi.exercise.plantsVsZombies.search;

public class Zombie {

	private int id;
	private int tipo;
	private int ciclo;
	private int postX;
	private int postY;

	public Zombie(int id, int tipo, int postX, int postY) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.postX = postX;
		this.postY = postY;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPostX() {
		return postX;
	}


	public void setPostX(int postX) {
		this.postX = postX;
	}


	public int getPostY() {
		return postY;
	}


	public void setPostY(int postY) {
		this.postY = postY;
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
}
