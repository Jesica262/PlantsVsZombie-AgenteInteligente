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
    public static int PERCEPCION_SOL = 6;
    public static int PERCEPCION_GIRASOL = 7;

    private ArrayList<Integer> sensorFilaDerecha;
    private ArrayList<Integer> sensorFilaIzquierda;
    private ArrayList<Integer> sensorColumnaArriba;
    private ArrayList<Integer> sensorColumnaAbajo;
	
    private int cantidadSol;
    private int contZombie;
    private int zombieTotal;

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
    //    this.setSensorFilaIzquierda(estadoAmbiente.getIzquierda(row,col));
    //    this.setSensorColumnaArriba(estadoAmbiente.getArriba(row,col));
    //    this.setSensorColumnaAbajo(estadoAmbiente.getAbajo(row,col));
		
        //actualizar estado
        this.setCantidadSol(estadoAmbiente.getCantidadSoles());
        this.setContZombie(estadoAmbiente.getContadorZombie());
        this.setZombieTotal(estadoAmbiente.getZombieTotal());
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

	/*
	@Override
    public String toString() {

		String str = "";
		
		str = str + "Cantidad de Soles: " + this.cantidadSol + "\n";
		
		str = str + "Sensor Fila: [ ";
        
		for( int i = 0; i < this.sensorFila.length; i++ )
		{
			if(this.sensorFila[i] == -1) {
	    		str = str + "X ";
	    	} else if (this.sensorFila[i] == 0) {
	    		str = str + "_ ";
	    	} else if (this.sensorFila[i] == 1) {
	    		str = str + "Z1 ";
	    	} else if (this.sensorFila[i] == 2) {
	    		str = str + "Z2 ";
	    	} else if (this.sensorFila[i] == 3) {
	    		str = str + "Z3 ";
	    	} else if (this.sensorFila[i] == 4) {
	    		str = str + "Z4 ";
	    	} else if (this.sensorFila[i] == 5) {
	    		str = str + "Z5 ";
	    	} else if (this.sensorFila[i] == 6) {
	    		str = str + "S ";
	    	} else if (this.sensorFila[i] == 7) {
	    		str = str + "G ";
	    	}
			
        }
	    str = str + " ]\n";
	    
    	str = str + "Sensor Columna: [\n";
    	
	    for (int j = 0; j < this.sensorColumna.length; j++) {
	    	if(this.sensorColumna[j] == -1) {
	    		str = str + " |X| \n";
	    	} else if (this.sensorColumna[j] == 0) {
	    		str = str + " |_| \n";
	    	} else if (this.sensorColumna[j] == 1) {
	    		str = str + " |Z1| \n";
	    	} else if (this.sensorColumna[j] == 2) {
	    		str = str + " |Z2| \n";
	    	} else if (this.sensorColumna[j] == 3) {
	    		str = str + " |Z3| \n";
	    	} else if (this.sensorColumna[j] == 4) {
	    		str = str + " |Z4| \n";
	    	} else if (this.sensorColumna[j] == 5) {
	    		str = str + " |Z5| \n";
	    	} else if (this.sensorColumna[j] == 6) {
	    		str = str + " |S| \n";
	    	} else if (this.sensorColumna[j] == 7) {
	    		str = str + " |G| \n";
	    	}
	    	
        }
	    str = str + " ]\n";
	    
	    return str;
    }*/
}