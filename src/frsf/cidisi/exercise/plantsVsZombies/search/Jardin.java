package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.ArrayList;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;

public class Jardin extends Environment {

    public Jardin() {
        // Crea el estado del entorno
        this.environmentState = new EstadoJardin();
    }

    @Override
    public EstadoJardin getEnvironmentState() {
        return (EstadoJardin) super.getEnvironmentState();
    }

    @Override
    public Perception getPercept() {
    
        PercepcionPlanta perception = new PercepcionPlanta();
  
        perception.initPerception(this);
        
        return perception;
    }

    @Override
    public String toString() {
        return environmentState.toString();
    }

    @Override
    public boolean agentFailed(Action actionReturned) {

        EstadoJardin estadoJardin = this.getEnvironmentState();

        int[][] posicionZombieFila = estadoJardin.getMatriz();
        boolean zombieFin = false;
        
        // Verifica que el Zombie llega a la columna 0, por lo que el juego termina.
        
        for(int i = 0; i<posicionZombieFila.length; i++)
        {
        	if( posicionZombieFila[i][0] > 0 && posicionZombieFila[i][0] < 6)
        	{
        		zombieFin = true;
        	}         
        }
        int cantidadSoles = estadoJardin.getCantidadSoles();
  
      //   El Agente falla cuando la Planta se queda sin Soles y si el Zombie llego ala casilla inicial.
       if ((cantidadSoles <= 0 ) || zombieFin)
            return true;
        return false;
    }
    
  
    
    @Override
    public void updateState(AgentState ast, Action action) {
    	 super.updateState(ast, action);
    }
    
    // Metodos especificos para la Planta
    
    public ArrayList<Integer> getFilaDerecha(int row, int col) {
    	return ((EstadoJardin) this.environmentState).getDerecha(row, col);
    }
    
    public ArrayList<Integer> getFilaIzquierda(int row, int col) {
    	return ((EstadoJardin) this.environmentState).getIzquierda(row, col);
    }
    
    public ArrayList<Integer> getColumnaArriba(int row, int col) {
    	return ((EstadoJardin) this.environmentState).getArriba(row, col);
    }
    
    public ArrayList<Integer> getColumnaAbajo(int row, int col) {
    	return ((EstadoJardin) this.environmentState).getAbajo(row, col);
    }
}
