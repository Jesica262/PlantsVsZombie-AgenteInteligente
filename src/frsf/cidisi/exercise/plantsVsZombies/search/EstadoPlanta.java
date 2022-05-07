package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.Random;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoPlanta extends SearchBasedAgentState {

    private int[][] matriz;
    private int[] posicionPlants;
    private int[] posicionInicial;
    private int cantidadSol;
    private int celdasVisitadas;
    private int contadorZombie;
    private int zombieTotal;

    public EstadoPlanta(int[][] m, int row, int col, int s, int cont, int z, int v) {
        matriz = m;
        posicionPlants = new int[] {row,col};
        posicionInicial = new int[2];
        posicionInicial[0] = row;
        posicionInicial[1] = col;
        cantidadSol = s;
        contadorZombie = cont;
        celdasVisitadas = v;
        zombieTotal = z;
    }

    public EstadoPlanta() {
    	
        matriz = new int[5][9];
        posicionPlants = new int[2];
        cantidadSol = 6;
        contadorZombie = 0;
        celdasVisitadas = 0;
        zombieTotal = 2;
       
        this.initState();
    }

    @Override
    public SearchBasedAgentState clone() {
    	
        int[][] newmatriz = new int[5][9];

        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[0].length; col++) {
                newmatriz[row][col] = matriz[row][col];
            }
        }

        int[] newPosition = new int[2];
        newPosition[0] = posicionPlants[0];
        newPosition[1] = posicionPlants[1];

        EstadoPlanta newState = new EstadoPlanta(newmatriz, this.getRowPosition(),
        										 this.getColumnPosition(), this.cantidadSol, 
        										 this.contadorZombie, this.zombieTotal, this.celdasVisitadas);
   
        return newState;
    }

    @Override
    public void updateState(Perception p) {
    	
        PercepcionPlanta plantsPerception = (PercepcionPlanta) p;

        int row = this.getRowPosition();
        int col = this.getColumnPosition();
        
        // Muestra la información que se encuentra en la fila y columna.
    	int i = col;

		for (Integer valor : plantsPerception.getSensorFilaDerecha()) 
		{ 
			matriz[row][i] = valor.intValue();
			i++;
			
		}
		
		/*	i = col;
		
		for (Integer valor : plantsPerception.getSensorFilaIzquierda()) 
		{ 
			matriz[row][i] = valor.intValue();
			i--;
		}

		i = row;

		for (Integer valor : plantsPerception.getSensorColumnaArriba()) 
		{ 
			matriz[i][col] = valor.intValue();
			i++;
		}

		i = row;
		
		for (Integer valor : plantsPerception.getSensorColumnaAbajo()) 
		{ 
			matriz[i][col] = valor.intValue();
			i--;
		}*/
		
        int cantSoles = this.getCantidadSol();
        int contadorZombie = plantsPerception.getContZombie();
        int celdas = plantsPerception.getCeldasVisitadas();
        this.celdasVisitadas = celdas;
        this.contadorZombie = contadorZombie;
        this.cantidadSol = cantSoles;
    }

    @Override
    public void initState() {
    	
    	Random random = new Random();
    	
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[0].length; col++) {
                matriz[row][col] = PercepcionPlanta.PERCEPCION_VACIO;
            }
        }
        
        this.setRowPosition(0);
        this.setColumnPosition(0);
        this.setCantidadSol(4);
        this.setContadorZombie(0);
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	  
    	String str = "";

    	str += "\n\n Posicion: [" + this.getRowPosition() + "," + "" + this.getColumnPosition() + "]\n";
        str += " Cantidad de Soles: " + this.getCantidadSol() + "\n";
        str += " Zombie muertos: " + this.contadorZombie + "\n";
        str += " Total zombie: " + this.getZombieTotal() + "\n";
        str += " Cantidad celdas visitadas: " + this.getCeldasVisitadas() + "\n";

        str = str + "\n";
        for (int row = 0; row < matriz.length; row++) {
            str += "| ";
            for (int col = 0; col < matriz[0].length; col++) {
            	
                if (matriz[row][col] == PercepcionPlanta.PERCEPCION_DESCONOCIDO) {
                    str += "x ";
                } else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_VACIO) { 
                	str +=  "_ ";
                } else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO1) {
                	str += "z1 ";
                } else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO2) {
                	str += "z2 ";
                } else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO3) {
                	str += "z3 ";
                } else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO4) {
                	str += "z4 ";
                } else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO5) {
                	str += "z5 ";
            	} else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL){
            		str += "* ";
            	} else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_GIRASOL){
            		str += "o ";
            	} else {
            		str += matriz[row][col] + " ";
                }
            }
            str = str + " |\n";
        }
        str = str + " ";

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EstadoPlanta))
            return false;

        int[][] matrizObj = ((EstadoPlanta) obj).getMatriz();
        int[] positionObj = ((EstadoPlanta) obj).getPosition();
        int sol = ((EstadoPlanta) obj).getCantidadSol();

        if (!(sol == this.getCantidadSol())) {
        	return false;
        }
        
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[0].length; col++) {
                if (matriz[row][col] != matrizObj[row][col]) {
                    return false;
                }
            }
        }

        if (posicionPlants[0] != positionObj[0] || posicionPlants[1] != positionObj[1]) {
            return false;
        }
        
        return true;
    }

    public int getmatrizPosition(int row, int col) {
        return matriz[row][col];
    }

    public void setmatrizPosition(int row, int col, int value) {
        this.matriz[row][col] = value;
    }

    public int[] getPosition() {
        return posicionPlants;
    }

    public void setRowPosition(int value) {
        this.posicionPlants[0] = value;
    }

    public void setColumnPosition(int value) {
        this.posicionPlants[1] = value;
    }

    public int getRowPosition() {
        return posicionPlants[0];
    }

    public int getColumnPosition() {
        return posicionPlants[1];
    }
    
    public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}

	public int[] getPosicionPlants() {
		return posicionPlants;
	}

	public void setPosicionPlants(int[] posicionPlants) {
		this.posicionPlants = posicionPlants;
	}

	public int[] getPosicionInicial() {
		return posicionInicial;
	}

	public void setPosicionInicial(int[] posicionInicial) {
		this.posicionInicial = posicionInicial;
	}

	public int getCantidadSol() {
		return cantidadSol;
	}

	public void setCantidadSol(int cantidadSol) {
		this.cantidadSol = cantidadSol;
	}

	public int getCeldasVisitadas() {
		return celdasVisitadas;
	}

	public void setCeldasVisitadas(int celdasVisitadas) {
		this.celdasVisitadas = celdasVisitadas;
	}

   public boolean isNoMoreZombie() {
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[0].length; col++) {
                if ( matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO1
        				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO2
        				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO3
        				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO4
        				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO5 ) {
                	
                    return false;
                }
            }
        }
        return true;
    }
   
   public boolean hayZombie(int row, int col) {
      
	   if ( matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO1
       			|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO2
       			|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO3
       			|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO4
       			|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO5 ) {
                  
		   return true;
       }
       return false;
   }
    
    public boolean tengoSol()
    {
    	return (this.cantidadSol>0);
    }
    
    public int getVisitedCellsCount() {
        return celdasVisitadas;
    }

	public boolean isAllmatrizKnown() {

	        for (int col = 0; col < matriz[0].length; col++) {
	            if (matriz[0][col] == PercepcionPlanta.PERCEPCION_VACIO) {
	            	
	                return false;
	            }	    
	        }
  
	    return true;
	}
	
	
	public void incrementarSol()
	{
		this.cantidadSol++;
	}
	
	public boolean haySol(int row, int col)
	{
		return (matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL);
	}

	public int getContadorZombie() {
		return contadorZombie;
	}

	public void setContadorZombie(int contadorZombie) {
		this.contadorZombie = contadorZombie;
	}

	public int getZombieTotal() {
		return zombieTotal;
	}

	public void setZombieTotal(int zombieTotal) {
		this.zombieTotal = zombieTotal;
	}
	
}