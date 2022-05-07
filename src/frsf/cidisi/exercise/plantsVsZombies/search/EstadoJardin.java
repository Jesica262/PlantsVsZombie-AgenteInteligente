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
    private int celdasVisitadas;
    private int contadorZombie = 0;
    private int zombieTotal;
    private ArrayList<Integer[]> listZombies;
    private ArrayList<Integer[]> listGirasoles;

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
            for (int col = 0; col < matriz[0].length; col++) {
            	matriz[row][col] = PercepcionPlanta.PERCEPCION_VACIO;
            }
        }

        matriz[0][5] = PercepcionPlanta.PERCEPCION_ENEMIGO1;
        matriz[0][2] = PercepcionPlanta.PERCEPCION_ENEMIGO1;
        matriz[1][3] = PercepcionPlanta.PERCEPCION_ENEMIGO1;
        matriz[1][1] = PercepcionPlanta.PERCEPCION_SOL;
    
        //random 5 a 20, Para Cantidad de Zombies que van a aparecer.
      
        
       // for (int cantZombie = 0; cantZombie < rangoList; cantZombie++)
        //{
        	//tipo = (int) (Math.random()*5+1);
        	//proximoMov = (int) (Math.random()*3+1);
        	//listZombies.add(new Zombie(tipo, proximoMov));     
        //	listZombies.add(null);
     //   }
     
        this.setPosicionAgente(new int[] {0,0});
        this.setPosicionZombie(new int[] {1,0});
        //(int) Math.random()*19+2
        this.setCantidadSoles(4);
        this.setContadorZombie(0);
        this.setZombieTotal(2);
        this.setCeldasVisitadas(0);
     //   int filaZombie =8;
      //  int colZombie = numeroRandom(0,4);
        
      //  this.setPosicionZombie(filaZombie, colZombie);
   
    }
    
    //actualizar

	/**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
    	
        String str = "";

        str += "\n Posicion Planta: [" + this.posicionAgente[0] + "," + this.posicionAgente[1] + "]\n";
        str += " Cantidad de Soles: " + this.getCantidadSoles() + "\n";
        str += " Posicion de Zombies:  \n";
        
     /*   for (int i=0; i<listZombies.size(); i++)
		{
			str += this.listZombies.get(i);
		}*/
        str += " Zombie muertos: " + this.getContadorZombie() + "\n";
        str += " Total zombie: " + this.getZombieTotal() + "\n";

        str = str + " \n";
        
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
    
    
    public int[] getPosicionGirasol() {
		return posicionGirasol;
	}

	public void setPosicionGirasol(int[] posicionGirasol) {
		this.posicionGirasol = posicionGirasol;
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

	public int getCantidadSoles() {
		return cantidadSoles;
	}

	public void setCantidadSoles(int cantidadSoles) {
		this.cantidadSoles = cantidadSoles;
	}
	
	public int[] getPosicionZombie() {
		return posicionZombie;
	}

	public void getMatarZombie(int row, int col)
	{
		// Comparo posicion de la planta con el Zombie
		   int[] posicionPlant = this.getPosicionAgente();
		    	
		   if(posicionPlant[0] == row && posicionPlant[1] == col) 
		   {	
		   	// Comparo soles de Zombie y soles de Planta
			  // if(this.cantidadSoles > this.tipo)
			   //{
		   		//	this.cantidadSoles = this.cantidadSoles - this.tipo;
		   			matriz[row][col] = PercepcionPlanta.PERCEPCION_VACIO;
			   //}
			   //else
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
	
	public ArrayList<Integer> getDerecha(int row, int col) {
	
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
		for(int i=this.posicionAgente[1]; i<9; i++)
		{
			if(matriz[row][i] != PercepcionPlanta.PERCEPCION_VACIO) {
				list.add(matriz[row][i]);
				break;
			}
			
			list.add(matriz[row][i]);
	
		}
	    return list;
	}
	 
	public ArrayList<Integer> getIzquierda(int row, int col) {
	
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
		for(int i=this.posicionAgente[1]; i>0; i--)
		{

			if(matriz[row][i] != PercepcionPlanta.PERCEPCION_VACIO)
			{
				list.add(matriz[row][i]);
		//		System.out.println(" I \n" +list + "   dgsd  "+this.posicionAgente[1]
			//			+" matriz "+matriz[row][i]);
				break;
			}
		}
	        	
	    return list;
	}
	
	public ArrayList<Integer> getArriba(int row, int col) {
	
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
		for(int i=this.posicionAgente[0]; i<5; i++)
		{

			if(matriz[i][col] != PercepcionPlanta.PERCEPCION_VACIO)
			{
				list.add(matriz[i][col]);
				System.out.println(" A \n" +list + "   dgsd  "+this.posicionAgente[1]
						+" matriz "+matriz[i][col]);
				break;
			}
		}
	        	
	    return list;
	}
	
	public ArrayList<Integer> getAbajo(int row, int col) {
	
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
		for(int i=this.posicionAgente[0]; i>0; i--)
		{

			if(matriz[i][col] != PercepcionPlanta.PERCEPCION_VACIO)
			{
				list.add(matriz[i][col]);
				System.out.println(" Abajo \n" +list + "   dgsd  "+this.posicionAgente[1]
						+" matriz "+matriz[row][i]);
				break;
			}
		}
	        	
	    return list;
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

	public int getCeldasVisitadas() {
		return celdasVisitadas;
	}

	public void setCeldasVisitadas(int celdasVisitadas) {
		this.celdasVisitadas = celdasVisitadas;
	}

	public ArrayList<Integer[]> getListZombies() {
		return listZombies;
	}

	public void setListZombies(ArrayList<Integer[]> listZombies) {
		this.listZombies = listZombies;
	}

	public ArrayList<Integer[]> getListGirasoles() {
		return listGirasoles;
	}

	public void setListGirasoles(ArrayList<Integer[]> listGirasoles) {
		this.listGirasoles = listGirasoles;
	}

	public void setPosicionZombie(int[] posicionZombie) {
		this.posicionZombie = posicionZombie;
		
		
	}
	
}