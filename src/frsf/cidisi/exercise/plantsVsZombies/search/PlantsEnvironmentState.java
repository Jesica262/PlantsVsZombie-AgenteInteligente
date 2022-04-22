package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.ArrayList;
import java.util.Random;

import frsf.cidisi.faia.state.EnvironmentState;

public class PlantsEnvironmentState extends EnvironmentState {

    private int[][] matriz;
    private int[] posicionAgente;
    private int cantidadSoles;
    private int tipo;
    private int proximoMov;
    private ArrayList<Zombie> listZombies;
    private ArrayList<Girasol> listGirasoles;
    
    public PlantsEnvironmentState(int[][] m) {
    	matriz = m;
    }

    public PlantsEnvironmentState() {
    	matriz = new int[5][9];
    	this.initState();
    }

    @Override
    public void initState() {
    	
    	int rangoList = (int) (Math.random()*16+5);
    	
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
            	matriz[row][col] = PlantsPerception.VACIO_PERCEPTION;
            }
        }
        
        /* Sets some cells with foods and enemies. 
        matriz[0][0] = PlantsPerception.FOOD_PERCEPTION;
        matriz[0][2] = PlantsPerception.FOOD_PERCEPTION;
        matriz[3][1] = PlantsPerception.ENEMY_PERCEPTION;
        matriz[2][1] = PlantsPerception.FOOD_PERCEPTION;
        matriz[0][3] = PlantsPerception.ENEMY_PERCEPTION;
        matriz[1][2] = PlantsPerception.FOOD_PERCEPTION;
        */
        
        //random 5 a 20, Para Cantidad de Zombies que van a aparecer.
        for (int cantZombie = 0; cantZombie < rangoList; cantZombie++)
        {
        	tipo = (int) (Math.random()*5+1);
        	proximoMov = (int) (Math.random()*3+1);
        	listZombies.add(new Zombie(tipo, proximoMov));       
        }

        this.setPosicionAgente(new int[] {2,0});
        this.setCantidadSoles((int) Math.random()*19+2);
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
}
