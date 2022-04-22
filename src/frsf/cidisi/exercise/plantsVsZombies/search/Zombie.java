package frsf.cidisi.exercise.plantsVsZombies.search;

public class Zombie {
	
	private int tipo; // 1 a 5
	private int[] posicion; // 5*9 inicialmente 9
	private int proximoMovimiento; // 1 y 3 ciclo de percepcion ??
	
	public Zombie() {
		super();
	}
	
	public Zombie(int tipo, int proximoMovimiento) {
		super();
		this.tipo = tipo;
		this.proximoMovimiento = proximoMovimiento;
	}
	
	public Zombie(int tipo, int[] posicion, int proximoMovimiento) {
		super();
		this.tipo = tipo;
		this.posicion = posicion;
		this.proximoMovimiento = proximoMovimiento;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public int[] getPosicion() {
		return posicion;
	}
	
	public void setPosicion(int[] posicion) {
		this.posicion = posicion;
	}
	
	public int getProximoMovimiento() {
		return proximoMovimiento;
	}
	
	public void setProximoMovimiento(int proximoMovimiento) {
		this.proximoMovimiento = proximoMovimiento;
	}
}
