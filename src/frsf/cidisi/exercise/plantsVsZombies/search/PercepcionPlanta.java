package frsf.cidisi.exercise.plantsVsZombies.search;

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

    private int[] sensorFila;
    private int[] sensorColumna;
    private int cantidadSol;

    public PercepcionPlanta() {
    	
    	cantidadSol = 1;
    }
    
    public PercepcionPlanta(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.s
     */
    @Override
    public void initPerception(Agent agent, Environment environment) {
        Planta planta = (Planta) agent;
        PlantaAmbiente plantaAmbiente = (PlantaAmbiente) environment;
        EstadoAmbiente estadoAmbiente = plantaAmbiente.getEnvironmentState();

        int row = estadoAmbiente.getPosicionAgente()[0];
        int col = estadoAmbiente.getPosicionAgente()[1];

        this.setSensorFila(estadoAmbiente.getFila(row));
        this.setSensorColumna(estadoAmbiente.getColumna(col));
        this.setCantidadSol(estadoAmbiente.getCantidadSoles());
    }


	public int[] getSensorFila() {
		return sensorFila;
	}

	public void setSensorFila(int[] sensorFila) {
		this.sensorFila = sensorFila;
	}

	public int[] getSensorColumna() {
		return sensorColumna;
	}

	public void setSensorColumna(int[] sensorColumna) {
		this.sensorColumna = sensorColumna;
	}

	public int getCantidadSol() {
		return cantidadSol;
	}

	public void setCantidadSol(int cantidadSol) {
		this.cantidadSol = cantidadSol;
	}

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
	    		str = str + "Z ";
	    	} else if (this.sensorFila[i] == 2) {
	    		str = str + "S ";
	    	} else if (this.sensorFila[i] == 3) {
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
	    		str = str + " |Z| \n";
	    	} else if (this.sensorColumna[j] == 2) {
	    		str = str + " |S| \n";
	    	} else if (this.sensorColumna[j] == 3) {
	    		str = str + " |G| \n";
	    	}
	    	
        }
	    str = str + " ]\n";
	    
	    return str;
    }
}