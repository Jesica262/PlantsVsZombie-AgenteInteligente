package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.Random;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoPlanta extends SearchBasedAgentState {

    private int[][] matriz;
    private int[] posicionPlants;
    private int[] posicionInicial;
    private int cantidadSol;
    private int celdasVisitadasX;
    private int celdasVisitadasY;
    private int contadorZombie;
    private int zombieTotal;
    private int zombiePercibidos;

    public EstadoPlanta(int[][] m, int row, int col, 
    		int s, int cont, int z, int zp, int x, int y) {
        matriz = m;
        posicionPlants = new int[] {row,col};
        posicionInicial = new int[2];
        posicionInicial[0] = row;
        posicionInicial[1] = col;
        cantidadSol = s;
        contadorZombie = cont;
        celdasVisitadasX = x;
        celdasVisitadasY = y;
        zombieTotal = z;
        zombiePercibidos = zp;
    }
    public EstadoPlanta() {
    	
        matriz = new int[5][9];
        posicionPlants = new int[2];
        cantidadSol = 6;
        contadorZombie = 0;
        celdasVisitadasX = 0;
        celdasVisitadasY = 0;
        zombiePercibidos = 0;
          
        this.initState();
    }
    
    public static int numeroRandom(int min, int max) {
        
        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
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

        EstadoPlanta newState = 
        		new EstadoPlanta(newmatriz, this.getRowPosition(),
        							 this.getColumnPosition(), this.cantidadSol, 
        							 this.contadorZombie, this.zombieTotal, 
        							 this.zombiePercibidos, this.celdasVisitadasX,
        							 this.celdasVisitadasY);   
        return newState;
    }

    @Override
    public void updateState(Perception p) {
    	
        PercepcionPlanta plantsPerception = (PercepcionPlanta) p;

        int row = this.getRowPosition();
        int col = this.getColumnPosition();
        
        // Muestra la informaci�n que se encuentra en la fila y columna.
    	int i = col;

		for (Integer valor : plantsPerception.getSensorFilaDerecha()) 
		{ 
			matriz[row][i] = valor.intValue();
		
			if( matriz[row][i] >= 1 && matriz[row][i] <= 5)
			{
				zombiePercibidos++;
			}
			i++;
		}
		
	/*	int j = col;
			
		for (Integer valor : plantsPerception.getSensorFilaIzquierda()) 
		{ 
			matriz[row][j] = valor.intValue();
			
			if( matriz[row][j] >= 1 && matriz[row][j] <= 5)
			{
				zombiePercibidos++;
			}
			j--;
		}
		
		int k = row;

		for (Integer valor : plantsPerception.getSensorColumnaArriba()) 
		{ 
			matriz[k][col] = valor.intValue();
			
			if(matriz[k][col] >= 1 && matriz[k][col] <= 5)
			{
				zombiePercibidos++;
			}
			k++;
		}

		int m = row;
		
		for (Integer valor : plantsPerception.getSensorColumnaAbajo()) 
		{ 
			matriz[m][col] = valor.intValue();
			
			if(matriz[m][col] >= 1 && matriz[m][col] <= 5)
			{
				zombiePercibidos++;
			}
			m--;
		}
		*/
        int cantSoles = this.getCantidadSol();
        int contadorZombie = plantsPerception.getContZombie();
        int celdasx = plantsPerception.getCeldasVisitadasX();
        int celdasy = plantsPerception.getCeldasVisitadasY();
        int zombieTotal = plantsPerception.getZombieTotal();
        this.celdasVisitadasX = celdasx;
        this.celdasVisitadasX = celdasy;
        this.contadorZombie = contadorZombie;
        this.cantidadSol = cantSoles;
        this.zombieTotal = zombieTotal;
    }

    @Override
    public void initState() {
    
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[0].length; col++) {
                matriz[row][col] = PercepcionPlanta.PERCEPCION_VACIO;
            }
        }
        
        this.setRowPosition(0);
        this.setColumnPosition(0);
        this.setCantidadSol(10);
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
        str += " Zombie Percibidos: " + this.getZombiePercibidos() + "\n";
        str += " Cantidad celdas visitadas en X: " + this.getCeldasVisitadasX() + "\n";
        str += " Cantidad celdas visitadas en Y: " + this.getCeldasVisitadasY() + "\n";

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
            	} else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_GIRASOL){
            		str += "G ";
            	} else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL1){
            		str += "* ";
            	} else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL2){
            		str += "** ";
            	} else if (matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL3){
            		str += "*** ";
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

    public int numeroRandomGirasol()
    {
    	return numeroRandom(1,3);
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
        return celdasVisitadasX;
    }

	public boolean isAllmatrizKnown() {

	        for (int col = 0; col < matriz[0].length; col++) {
	            if (matriz[0][col] == PercepcionPlanta.PERCEPCION_VACIO) {
	            	
	                return false;
	            }	    
	        }
  
	    return true;
	}
	
	
	public int incrementarSol(int row,int col)
	{
		int sol = 0;		
		if(matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL1)
		{
			sol = 1;
		}
		else if(matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL2)
		{
			sol = 2;
		}
		else if(matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL3)
		{
			sol = 3;
		}
		return sol;
	}
	
	public boolean haySol(int row, int col)
	{
		return (matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL1
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL2
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL3);
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

	public int getZombiePercibidos() {
		return zombiePercibidos;
	}

	public void setZombiePercibidos(int zombiePercibidos) {
		this.zombiePercibidos = zombiePercibidos;
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
	
}