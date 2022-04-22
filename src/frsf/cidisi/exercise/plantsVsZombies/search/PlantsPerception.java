package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PlantsPerception extends Perception {

	//preguntar lo que vamos a percibir
	 
    public static int DESCONOCIDO_PERCEPTION = -1;
    public static int VACIO_PERCEPTION = 0;
    public static int ENEMIGO_PERCEPTION = 1;
    public static int SOL_PERCEPTION = 2;
    public static int GIRASOL_PERCEPTION = 3;

    private int sensorIzquierdo;
    private int sensorDerecho;
    private int sensorArriba;
    private int sensorAbajo;

    public PlantsPerception() {}
    
    public PlantsPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.s
     */
    @Override
    public void initPerception(Agent agent, Environment environment) {
        PlantsAgent plantsAgent = (PlantsAgent) agent;
        PlantsEnvironment plantsEnvironment = (PlantsEnvironment) environment;
        PlantsEnvironmentState environmentState = plantsEnvironment.getEnvironmentState();

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        this.setSensorDerecho(plantsEnvironment.getMoverDerecha(row, col));
        this.setSensorIzquierdo(plantsEnvironment.getMoverIzquierda(row, col));
        this.setSensorArriba(plantsEnvironment.getMoverArriba(row, col));
        this.setSensorAbajo(plantsEnvironment.getMoverAbajo(row, col));
    }

    public int getSensorIzquierdo() {
        return sensorIzquierdo;
    }

    public void setSensorIzquierdo(int sensorIzquierdo) {
        this.sensorIzquierdo = sensorIzquierdo;
    }

    public int getSensorDerecho() {
        return sensorDerecho;
    }

    public void setSensorDerecho(int sensorDerecho) {
        this.sensorDerecho = sensorDerecho;
    }

    public int getSensorArriba() {
        return sensorArriba;
    }

    public void setSensorArriba(int sensorArriba) {
        this.sensorArriba = sensorArriba;
    }

    public int getSensorAbajo() {
        return sensorAbajo;
    }

    public void setSensorAbajo(int sensorAbajo) {
        this.sensorAbajo = sensorAbajo;
    }

	@Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        
        str.append("Sensor Izquierdo: " + this.sensorIzquierdo);
        str.append("; ");
        str.append("Sensor Derecho: " + this.sensorDerecho);
        str.append("; ");
        str.append("Sensor Arriba: " + this.sensorArriba);
        str.append("; ");
        str.append("Sensor Abajo: " + this.sensorAbajo);

        return str.toString();
    }
}
