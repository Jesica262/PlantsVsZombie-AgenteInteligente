package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.ArrayList;
import java.util.Random;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoJardin extends EnvironmentState {

    private int[][] matriz;
    private int[] posicionAgente;
    private int[] posicionZombie;
    private int[] posicionGirasol;
    private int cantidadSoles;
    private int tipo;
    private int contadorZombie = 0;
    private ArrayList<Zombie> listZombies;
    private ArrayList<Girasol> listGirasoles;

    public EstadoJardin(int[][] m) {
    	matriz = m;
    }

    public EstadoJardin() {
    	matriz = new int[5][9];
    	this.initState();
    }

    public static int numeroRandom(int min, int max) {
        
        Random random = new Random();

        int randomNum = random.nextInt((max - min) + 1) + min;

        return randomNum;
    }
   
    @Override
    public void initState() {
    	
    //	int rangoList = (int) (Math.random()*16+5);7
    	int rangoList = 5;
    	
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
            	matriz[row][col] = PercepcionPlanta.PERCEPCION_VACIO;
            }
        }
        
        //random 5 a 20, Para Cantidad de Zombies que van a aparecer.
        contadorZombie = rangoList;
        
       // for (int cantZombie = 0; cantZombie < rangoList; cantZombie++)
        //{
        	//tipo = (int) (Math.random()*5+1);
        	//proximoMov = (int) (Math.random()*3+1);
        	//listZombies.add(new Zombie(tipo, proximoMov));     
        //	listZombies.add(null);
     //   }
     
        this.setPosicionAgente(new int[] {2,0});
        //(int) Math.random()*19+2
        this.setCantidadSoles(5);
        
        int filaZombie =8;
        int colZombie = numeroRandom(0,4);
        
        this.setPosicionZombie(filaZombie, colZombie);
   
    }
    
    //actualizar

	/**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
    	
        String str = "";

        str += "\n Posicion Planta: (" + this.posicionAgente[0] + "," + this.posicionAgente[1] + ")\n";
        str += " Cantidad de Soles: (" + this.cantidadSoles + ")\n";
        str += " Posicion de Zombie/s: (";
        
     /*   for (int i=0; i<listZombies.size(); i++)
		{
			str += this.listZombies.get(i);
		}*/
        
        str += ")\n";
        
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
    
    public int[] getPosicionGirasol() {
		return posicionGirasol;
	}

	public void setPosicionGirasol(int[] posicionGirasol) {
		this.posicionGirasol = posicionGirasol;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Girasol> getListGirasoles() {
		return listGirasoles;
	}

	public void setListGirasoles(ArrayList<Girasol> listGirasoles) {
		this.listGirasoles = listGirasoles;
	}

	public void setPosicionZombie(int row, int col) {
		this.posicionZombie[0] = row;
		this.posicionZombie[1] = col;
	}

	public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public void setMatriz(int row, int col, int value) {
        this.matriz[row][col] = value;
    }

    public int[] getPosicionAgente() {
        return posicionAgente;
    }

    public void setPosicionAgente(int[] posicionAgente) {
        this.posicionAgente = posicionAgente;
    }

    public int getMoverAbajo(int row, int col) {
        if (row == 0) {
            return matriz[4][col];
        }
        return matriz[row - 1][col];
    }

    public int getMoverIzquierda(int row, int col) {
        if (col == 0) {
            return matriz[row][8];
        }
        return matriz[row][col - 1];
    }

    public int getMoverDerecha(int row, int col) {
        if (col == 8) {
            return matriz[row][0];
        }
        return matriz[row][col + 1];
    }

    public int getMoverArriba(int row, int col) {
        if (row == 4) {
            return matriz[0][col];
        }
        return matriz[row + 1][col];
    }

	public int getCantidadSoles() {
		return cantidadSoles;
	}

	public void setCantidadSoles(int cantidadSoles) {
		this.cantidadSoles = cantidadSoles;
	}
	
	public void getPosicionZombie() {
		 
		int row = numeroRandom(0, 8);
		int col = numeroRandom(0, 4);
	    	
		// Comparo posicion de la planta con el Zombie
	   int[] posicionPlant = this.getPosicionAgente();
	    	
	   if((posicionPlant[0] != row && posicionPlant[1] == col) || (posicionPlant[0] == row && posicionPlant[1] != col))
	   {	
		   if( !hayGirasol(row, col) )
		   {
			   matriz[row][col] = PercepcionPlanta.PERCEPCION_ENEMIGO1;
		   }
	   }
	}
	
	public void getMatarZombie(int row, int col)
	{
		// Comparo posicion de la planta con el Zombie
		   int[] posicionPlant = this.getPosicionAgente();
		    	
		   if(posicionPlant[0] == row && posicionPlant[1] == col) 
		   {	
		   	// Comparo soles de Zombie y soles de Planta
			   if(this.cantidadSoles > this.tipo)
			   {
		   			this.cantidadSoles = this.cantidadSoles - this.tipo;
		   			matriz[row][col] = PercepcionPlanta.PERCEPCION_VACIO;
			   }
			   else
			   {
				   // Juego terminado
			   }
		   }
	}
	
	public boolean hayGirasol(int row, int col)
	{
		return (matriz[row][col] == PercepcionPlanta.PERCEPCION_GIRASOL);
	}
	
	public boolean hayZombie(int row, int col)
	{
		return (   matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO1
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO2
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO3
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO4
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO5 );
	}
	
	public int[] getDerecha(int row, int col) {
		
		int[] result = new int[] {};
		 
		for(int i=this.posicionAgente[0]; i<8; i++)
		{
			if(matriz[row][i] != PercepcionPlanta.PERCEPCION_VACIO)
			{
				result[i] = matriz[row][i];
				break;
			}
		}
	        	
	    return result;
	}
	 
	public int[] getIzquierda(int row, int col) {
		
		int[] result = new int[] {};
		 
		for(int i=this.posicionAgente[0]; i>0; i--)
		{
			if(matriz[row][i] != PercepcionPlanta.PERCEPCION_VACIO)
			{
				result[i] = matriz[row][i];
				break;
			}
		}
	        	
	    return result;
	}
	
	public int[] getArriba(int row, int col) {
		
		int[] result = new int[] {};
		 
		for(int i=this.posicionAgente[1]; i<4; i++)
		{
			if(matriz[i][col] != PercepcionPlanta.PERCEPCION_VACIO)
			{
				result[i] = matriz[i][col];
				break;
			}
		}
	        	
	    return result;
	}
	
	public int[] getAbajo(int row, int col) {
		
		int[] result = new int[] {};
		 
		for(int i=this.posicionAgente[1]; i>4; i--)
		{
			if(matriz[i][col] != PercepcionPlanta.PERCEPCION_VACIO)
			{
				result[i] = matriz[i][col];
				break;
			}
		}
	        	
	    return result;
	}
}