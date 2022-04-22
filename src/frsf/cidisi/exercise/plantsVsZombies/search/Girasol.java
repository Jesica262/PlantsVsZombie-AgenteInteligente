package frsf.cidisi.exercise.plantsVsZombies.search;

public class Girasol {

	private int[] posicion;
	private int cantidadSol; // 1 a 3 por ciclo de percepcion
	private boolean estado;
	
	public Girasol() {
		this.cantidadSol = (int) (Math.random()*3+1);
		this.estado = false;
	}
	
	public Girasol(int cantidadSol, boolean estado) {
		super();
		this.cantidadSol = cantidadSol;
		this.estado = estado;
	}
	
	public Girasol(int[] posicion, int cantidadSol, boolean estado) {
		super();
		this.posicion = posicion;
		this.cantidadSol = cantidadSol;
		this.estado = estado;
	}
	
	public int[] getPosicion() {
		return posicion;
	}
	
	public void setPosicion(int[] posicion) {
		this.posicion = posicion;
	}
	
	public int getCantidadSol() {
		return cantidadSol;
	}
	
	public void setCantidadSol(int cantidadSol) {
		this.cantidadSol = cantidadSol;
	}
	
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
