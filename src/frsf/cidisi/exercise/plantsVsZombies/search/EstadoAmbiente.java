package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.ArrayList;
import java.util.Random;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

    private int[][] matriz;
    private int[] posicionAgente;
    private int[] posicionZombie;
    private int[] posicionGirasol;
    private int cantidadSoles;
    private int tipo;
    private int proximoMov;
    private int iteracion = 0;
    private ArrayList<Zombie> listZombies;
    private ArrayList<Girasol> listGirasoles;

    public EstadoAmbiente(int[][] m) {
    	matriz = m;
    }

    public EstadoAmbiente() {
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
        for (int cantZombie = 0; cantZombie < rangoList; cantZombie++)
        {
        	//tipo = (int) (Math.random()*5+1);
        	//proximoMov = (int) (Math.random()*3+1);
        	//listZombies.add(new Zombie(tipo, proximoMov));     
        	listZombies.add(new Zombie(1, 3)); 
        }
     
        this.setPosicionAgente(new int[] {2,0});
        //(int) Math.random()*19+2
        this.setCantidadSoles(5);
   
      //  int filaLobo = randInt(0, 8);
      //  int columnaLobo = randInt(0, 13);
        
      //  this.setPosLobo(filaLobo, columnaLobo);
    }

	/**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        str = str + "[ \n";
        for (int row = 0; row < matriz.length; row++) {
            str = str + "[ ";
            for (int col = 0; col < matriz.length; col++) {
                str = str + matriz[row][col] + " ";
            }
            str = str + " ]\n";
        }
        str = str + " ]";

        return str;
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
			   matriz[row][col] = PercepcionPlanta.PERCEPCION_ENEMIGO;
		   }
	   }
	    	
	 //  this.setPosLobo(filaLobo, columnaLobo);
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
		return (matriz[row][col]== PercepcionPlanta.PERCEPCION_GIRASOL);
	}
}
