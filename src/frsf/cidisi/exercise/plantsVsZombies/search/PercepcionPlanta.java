package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.ArrayList;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PercepcionPlanta extends Perception {

	//preguntar lo que vamos a percibir
	 
    public static int PERCEPCION_DESCONOCIDO = -1;
    public static int PERCEPCION_VACIO = 0;
    public static int PERCEPCION_ENEMIGO1 = 1;
    public static int PERCEPCION_ENEMIGO2 = 2;
    public static int PERCEPCION_ENEMIGO3 = 3;
    public static int PERCEPCION_ENEMIGO4 = 4;
    public static int PERCEPCION_ENEMIGO5 = 5;
    public static int PERCEPCION_GIRASOL = 6;
    public static int PERCEPCION_SOL1 = 7;
    public static int PERCEPCION_SOL2 = 8;
    public static int PERCEPCION_SOL3 = 9;

    private ArrayList<Integer> sensorFilaDerecha;
    private ArrayList<Integer> sensorFilaIzquierda;
    private ArrayList<Integer> sensorColumnaArriba;
    private ArrayList<Integer> sensorColumnaAbajo;
	
    private int cantidadSol;
    private int contZombie;
    private int zombieTotal;
    private int celdasVisitadasX;
    private int celdasVisitadasY;
    private int zombiePercibido;

    public PercepcionPlanta() {}
    
    public PercepcionPlanta(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.s
     */
    @Override
    public void initPerception(Environment environment) {

        Jardin plantaAmbiente = (Jardin) environment;
        EstadoJardin estadoAmbiente = plantaAmbiente.getEnvironmentState();

        int row = estadoAmbiente.getPosicionAgente()[0];
        int col = estadoAmbiente.getPosicionAgente()[1];

        /* TODO  Preguntar si esta bien tomar toda la columna */
        this.setSensorFilaDerecha(estadoAmbiente.getDerecha(row,col));
     //   this.setSensorFilaIzquierda(estadoAmbiente.getIzquierda(row,col));
     //   this.setSensorColumnaArriba(estadoAmbiente.getArriba(row,col));
     //   this.setSensorColumnaAbajo(estadoAmbiente.getAbajo(row,col));
		
        //actualizar estado
        this.setCantidadSol(estadoAmbiente.getCantidadSoles());
        this.setContZombie(estadoAmbiente.getContadorZombie());
        this.setZombieTotal(estadoAmbiente.getZombieTotal());
        this.setCeldasVisitadasX(estadoAmbiente.getCeldasVisitadasX());
        this.setCeldasVisitadasY(estadoAmbiente.getCeldasVisitadasY());
        this.setZombiePercibido(estadoAmbiente.getZombiePercibido());
    }

	public ArrayList<Integer> getSensorFilaDerecha() {
		return sensorFilaDerecha;
	}

	public void setSensorFilaDerecha(ArrayList<Integer> sensorFilaDerecha) {
		this.sensorFilaDerecha = sensorFilaDerecha;
	}

	public ArrayList<Integer> getSensorFilaIzquierda() {
		return sensorFilaIzquierda;
	}

	public void setSensorFilaIzquierda(ArrayList<Integer> sensorFilaIzquierda) {
		this.sensorFilaIzquierda = sensorFilaIzquierda;
	}

	public ArrayList<Integer> getSensorColumnaArriba() {
		return sensorColumnaArriba;
	}

	public void setSensorColumnaArriba(ArrayList<Integer> sensorColumnaArriba) {
		this.sensorColumnaArriba = sensorColumnaArriba;
	}

	public ArrayList<Integer> getSensorColumnaAbajo() {
		return sensorColumnaAbajo;
	}

	public void setSensorColumnaAbajo(ArrayList<Integer> sensorColumnaAbajo) {
		this.sensorColumnaAbajo = sensorColumnaAbajo;
	}

	public int getContZombie() {
		return contZombie;
	}

	public void setContZombie(int contZombie) {
		this.contZombie = contZombie;
	}

	public int getCantidadSol() {
		return cantidadSol;
	}

	public void setCantidadSol(int cantidadSol) {
		this.cantidadSol = cantidadSol;
	}


	public int getCeldasVisitadasX() {
		return celdasVisitadasX;
	}

	public void setCeldasVisitadasX(int celdasVisitadasX) {
		this.celdasVisitadasX = celdasVisitadasX;
	}

	public int getCeldasVisitadasY() {
		return celdasVisitadasY;
	}

	public void setCeldasVisitadasY(int celdasVisitadasY) {
		this.celdasVisitadasY = celdasVisitadasY;
	}

	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		
	}

	public int getZombieTotal() {
		return zombieTotal;
	}

	public void setZombieTotal(int zombieTotal) {
		this.zombieTotal = zombieTotal;
	}

	public int getZombiePercibido() {
		return zombiePercibido;
	}

	public void setZombiePercibido(int zombiePercibido) {
		this.zombiePercibido = zombiePercibido;
	}

}