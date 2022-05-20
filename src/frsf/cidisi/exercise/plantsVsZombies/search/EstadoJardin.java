package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.ArrayList;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoJardin extends EnvironmentState {

    private int[][] matriz;
    private int[] posicionAgente;
    private int[] posicionZombie;
    private int[] posicionGirasol;
    private int cantidadSoles;
    private int celdasVisitadasX;
    private int celdasVisitadasY;
    private int contadorZombie;
    private int zombieTotal;
    private int cantidadZombie;
    private int contadorPlanta;
    private boolean zombieLlego = false;
    private ArrayList<Zombie> listZombies = new ArrayList<Zombie>();
    private ArrayList<Zombie> zombieEliminado = new ArrayList<Zombie>();
    private ArrayList<Girasol> listGirasoles = new ArrayList<Girasol>();

    public EstadoJardin(int[][] m) {
    	matriz = m;
    }

    public EstadoJardin() {
    	matriz = new int[5][9];
    	this.initState();
    }

    public static int numeroRandom(int min, int max) {
        
        int randomNum = (int) ((Math.random()*((max - min) + 1)) + min);

        return randomNum;
    }
   
    @Override
    public void initState() {
    	
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[0].length; col++) {
            	matriz[row][col] = PercepcionPlanta.PERCEPCION_VACIO;
            }
        }
        
        cargarZombies();
        
        this.getListZombies().forEach((z) -> {
    		
        	matriz[z.getPostX()][z.getPostY()] = z.getTipo();
    	});
        
        actualizarGirasoles();
        
        this.setPosicionAgente(new int[] {0,0});
        this.setCantidadSoles(20);
        this.setContadorZombie(0);	
        this.setContadorPlanta(0);
        this.setCeldasVisitadasX(0);
        this.setCeldasVisitadasY(0);
   
    }

    public void update() {
    	
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[0].length; col++) {
            	matriz[row][col] = PercepcionPlanta.PERCEPCION_VACIO;
            }
        }  
    }

    public void cargarZombies()
    {
        //random 5 a 20, Para Cantidad de Zombies que van a aparecer.
       	// 5 a 20 
    	ArrayList<Zombie> lista = new ArrayList<Zombie>();
    	listZombies.clear();
    	int rangoList = (int) (numeroRandom(4,5));
        
        for (int cantZombie = 0; cantZombie < rangoList; cantZombie++)
        {     
        	int tipoZombie = (int) (numeroRandom(1,5));
            int posicionAletorioX = (int) (numeroRandom(0,4));
        	int posicionAletorioY = (int) (numeroRandom(6,8));
        	int ciclo = (int) (numeroRandom(1,3));
            
            Zombie zombie = new Zombie(cantZombie, ciclo, tipoZombie, posicionAletorioX, posicionAletorioY );
            
            lista.add(zombie);              
        }
        
        listZombies.addAll(removeDuplicate(lista));
        this.setZombieTotal(listZombies.size());
        this.setCantidadZombie(this.getZombieTotal());
    }
    
    public ArrayList<Zombie> removeDuplicate(ArrayList<Zombie> list){
    	
    	for(int i=0 ;i<list.size()-1; i++)
        {
         for(int j=list.size()-1; j>i; j--)
         {
           if(list.get(j).getPostX() == list.get(i).getPostX() && list.get(j).getPostY() == list.get(i).getPostY()){
            
        	   list.remove(list.get(j).getId());
           } 
         } 
       } 
       return list;
     }
    public void eliminarZombie(int row, int col)
    {
    	this.getListZombies().forEach((z) -> {
    		
    		if(z.getPostX()==row && z.getPostY()==col )
    		{
    			this.listZombies.remove(z);
    		}
    	});
    }
    
    public  ArrayList<Zombie> actualizarZombies()
    {
    	this.getListZombies().forEach((z) -> {
    		
    		/*for (Zombie ze: zombieEliminado) {
    			
    			if(z.getPostX() == ze.getPostX() && z.getPostY() == ze.getPostY())
    			{
    				listZombies.remove(z);
    				//zombieEliminado.remove(ze);
    			}
    		}*/
    		if(z.getPostY() == 0)
    		{
    			this.zombieLlego = true;
    		}
    		else {
    			if(z.getPostY() > 0) {
    				if(z.getCiclo() == 0)
    				{
    					matriz[z.getPostX()][z.getPostY()-1] = z.getTipo();
		    			matriz[z.getPostX()][z.getPostY()] = PercepcionPlanta.PERCEPCION_VACIO;
		    			z.setPostY(z.getPostY()-1);
		    			z.setCiclo(numeroRandom(1,3));
    				} 
    				else {
    					z.setCiclo(z.getCiclo()-1);
    				}
    			}
    		}	
	    });
   
    	return listZombies;
    }
	
    public void actualizarGirasoles() {

		this.getListGirasoles().forEach(g -> {

			int numeroCiclo = numeroRandom(1,3);
			g.setCiclo(g.getCiclo()+numeroCiclo);

		});

	}
	/**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
    	
        String str = "";

        str += "\n Posicion Planta: [" + this.posicionAgente[0] + "," + this.posicionAgente[1] + "]\n";
        str += " Cantidad de Soles: " + this.getCantidadSoles() + "\n";
        str += " Zombie muertos: " + this.getContadorZombie() + "\n";
        str += " Cantidad zombie: " + this.getCantidadZombie() + "\n";
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
    
    public int numeroRandomGirasol()
    {
    	return numeroRandom(1,3);
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
	
	public boolean haySol(int row, int col)
	{
		return (matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL1
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL2
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_SOL3);
	}
	
	public boolean hayZombie(int row, int col)
	{
		return (   matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO1
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO2
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO3
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO4
				|| matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO5 );
	}
	
	public int buscarZombie(int row, int col)
	{
		if ( matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO1 )
		{
			return matriz[row][col];
		} 
		else if ( matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO2 )
		{
			return matriz[row][col];
		}
		else if ( matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO3 )
		{
			return matriz[row][col];
		} 
		else if ( matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO4 )
		{
			return matriz[row][col];
		}
		else if( matriz[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO5 )
		{
			return matriz[row][col];	
		}
		return 0;
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
		 
		for(int i=this.posicionAgente[1]; i>=0; i--)
		{

			if(matriz[row][i] != PercepcionPlanta.PERCEPCION_VACIO)
			{
				list.add(matriz[row][i]);
				break;
			}
			list.add(matriz[row][i]);
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
				break;
			}
			list.add(matriz[row][i]);
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
				break;
			}
			list.add(matriz[row][i]);
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

	public ArrayList<Zombie> getListZombies() {
		return listZombies;
	}

	public void setListZombies(ArrayList<Zombie> listZombies) {
		this.listZombies = listZombies;
	}

	public ArrayList<Girasol> getListGirasoles() {
		return listGirasoles;
	}

	public void setListGirasoles(ArrayList<Girasol> listGirasoles) {
		this.listGirasoles = listGirasoles;
	}

	public void setPosicionZombie(int[] posicionZombie) {
		this.posicionZombie = posicionZombie;
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

	public boolean isZombieLlego() {
		return zombieLlego;
	}

	public void setZombieLlego(boolean zombieLlego) {
		this.zombieLlego = zombieLlego;
	}

	public ArrayList<Zombie> getZombieEliminado() {
		return zombieEliminado;
	}

	public void setZombieEliminado(ArrayList<Zombie> zombieEliminado) {
		this.zombieEliminado = zombieEliminado;
	}

}