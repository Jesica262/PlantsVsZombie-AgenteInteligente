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

    public EstadoPlanta(int[][] m, int row, int col, int s) {
        matriz = m;
        posicionPlants = new int[] {row,col};
        posicionInicial = new int[2];
        posicionInicial[0] = row;
        posicionInicial[1] = col;
        cantidadSol = s;
    }

    public EstadoPlanta() {
    	
        matriz = new int[5][9];
        posicionPlants = new int[2];
        cantidadSol = 1;
       
        this.initState();
    }

    @Override
    public SearchBasedAgentState clone() {
    	
        int[][] newmatriz = new int[5][9];

        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
                newmatriz[row][col] = matriz[row][col];
            }
        }

        int[] newPosition = new int[2];
        newPosition[0] = posicionPlants[0];
        newPosition[1] = posicionPlants[1];

        EstadoPlanta newState = new EstadoPlanta(newmatriz,
                this.getRowPosition(), this.getColumnPosition(), this.cantidadSol);

        return newState;
    }

    @Override
    public void updateState(Perception p) {
    	
        PercepcionPlanta plantsPerception = (PercepcionPlanta) p;

        int row = this.getRowPosition();
        int col = this.getColumnPosition();
        
        // Muestra la información que se encuentra en la fila y columna.
        
        int [] filaDerecha =  plantsPerception.getSensorFilaDerecha();      
        int [] filaIzquierda = plantsPerception.getSensorColumnaAbajo();
        int [] columnaArriba = plantsPerception.getSensorColumnaArriba();
        int [] columnaAbajo = plantsPerception.getSensorColumnaAbajo();
        
    	int i = col;

		for (Integer valor : filaDerecha) 
		{ 
			matriz[row][i] = valor.intValue();
			i++;
		}

		i = col;
		
		for (Integer valor : filaIzquierda) 
		{ 
			matriz[row][i] = valor.intValue();
			i--;
		}

		i = row;

		for (Integer valor : columnaArriba) 
		{ 
			matriz[i][col] = valor.intValue();
			i++;
		}

		i = row;
		
		for (Integer valor : columnaAbajo) 
		{ 
			matriz[i][col] = valor.intValue();
			i--;
		}
		
        int cantSoles = plantsPerception.getCantidadSol();
        this.cantidadSol = cantSoles;
    }

    @Override
    public void initState() {
    	
    	Random random = new Random();
    	
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[0].length; col++) {
                matriz[row][col] = PercepcionPlanta.PERCEPCION_DESCONOCIDO;
            }
        }
        
        this.setRowPosition(2);
        this.setColumnPosition(0);
        this.setcantidadSol(1);
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	  
    	String str = "";

    	str += "\n Posicion: (" + getRowPosition() + "," + "" + getColumnPosition() + ")\n";
        str += " Cantidad de Soles: (" + cantidadSol + ")\n";

        str = str + "[ \n";
        for (int row = 0; row < matriz.length; row++) {
            str += "[ ";
            for (int col = 0; col < matriz[0].length; col++) {
                if (matriz[row][col] == -1) {
                    str += "x ";
                } else if (matriz[row][col] == 0) { 
                	str +=  "_ ";
                } else if (matriz[row][col] == 1) {
                	str += "z1 ";
                } else if (matriz[row][col] == 2) {
                	str += "z2 ";
                } else if (matriz[row][col] == 3) {
                	str += "z3 ";
                } else if (matriz[row][col] == 4) {
                	str += "z4 ";
                } else if (matriz[row][col] == 5) {
                	str += "z5 ";
            	} else if (matriz[row][col] == 6){
            		str += "* ";
            	} else if (matriz[row][col] == 7){
            		str += "o ";
            	} else {
            		str += matriz[row][col] + " ";
                }
            }
            str = str + " ]\n";
        }
        str = str + " ]";

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

        int[][] matrizObj = ((EstadoPlanta) obj).getmatriz();
        int[] positionObj = ((EstadoPlanta) obj).getPosition();

        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
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

	public int[][] getmatriz() {
        return matriz;
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

    public int getcantidadSol() {
        return cantidadSol;
    }

    private void setcantidadSol(int cantidadSol) {
        this.cantidadSol = cantidadSol;
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
            for (int col = 0; col < matriz.length; col++) {
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
    
    public boolean tengoSol()
    {
    	return (cantidadSol>0);
    }
    
    public int getVisitedCellsCount() {
        return celdasVisitadas;
    }

	public boolean isAllmatrizKnown() {
	    for (int row = 0; row < matriz.length; row++) {
	        for (int col = 0; col < matriz.length; col++) {
	            if (matriz[row][col] == PercepcionPlanta.PERCEPCION_DESCONOCIDO) {
	                return false;
	            }
	        }
	    }
	    
	    return true;
	}
	
	public void incrementarSol()
	{
		this.cantidadSol++;
	}

	public int getContadorZombie() {
		return contadorZombie;
	}

	public void setContadorZombie(int contadorZombie) {
		this.contadorZombie = contadorZombie;
	}
	
}