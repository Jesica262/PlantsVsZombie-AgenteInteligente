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
    private int[] posicion;
    private int cantidadSol;
    private int contZombie;
    private int zombieTotal;
    private int celdasVisitadasX;
    private int celdasVisitadasY;
    private int cantidadZombie;
    private int contadorPlanta;
    private ArrayList<Zombie> listZombies = new ArrayList<Zombie>();
    private ArrayList<Girasol> listGirasoles = new ArrayList<Girasol>();

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

        //actualizar estado
        this.setCantidadSol(estadoAmbiente.getCantidadSoles());
        this.setContZombie(estadoAmbiente.getContadorZombie());
        this.setZombieTotal(estadoAmbiente.getZombieTotal());
        this.setCeldasVisitadasX(estadoAmbiente.getCeldasVisitadasX());
        this.setCeldasVisitadasY(estadoAmbiente.getCeldasVisitadasY());
        this.setCantidadZombie(estadoAmbiente.getCantidadZombie());
        this.setListZombies(estadoAmbiente.getListZombies());
        this.setContadorPlanta(estadoAmbiente.getContadorPlanta());
        
        /* TODO  Preguntar si esta bien tomar toda la columna */
        this.setSensorFilaDerecha(estadoAmbiente.getDerecha(row,col));
        this.setSensorFilaIzquierda(estadoAmbiente.getIzquierda(row,col));
        this.setSensorColumnaArriba(estadoAmbiente.getArriba(row,col));
        this.setSensorColumnaAbajo(estadoAmbiente.getAbajo(row,col));        
    }

	public ArrayList<Zombie> getListZombies() {
		return listZombies;
	}

	public void setListZombies(ArrayList<Zombie> listZombies) {
		this.listZombies = listZombies;
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

	public int getCantidadZombie() {
		return cantidadZombie;
	}

	public void setCantidadZombie(int cantidadZombie) {
		this.cantidadZombie = cantidadZombie;
	}

	public int getContadorPlanta() {
		return contadorPlanta;
	}

	public void setContadorPlanta(int contadorPlanta) {
		this.contadorPlanta = contadorPlanta;
	}

	public ArrayList<Girasol> getListGirasoles() {
		return listGirasoles;
	}

	public void setListGirasoles(ArrayList<Girasol> listGirasoles) {
		this.listGirasoles = listGirasoles;
	}

	public int[] getPosicion() {
		return posicion;
	}

	public void setPosicion(int[] posicion) {
		this.posicion = posicion;
	}

}