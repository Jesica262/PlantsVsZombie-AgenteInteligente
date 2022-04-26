package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

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

        EstadoJardin plantsEnvironmentState = this.getEnvironmentState();

        int cantidadSoles = plantsEnvironmentState.getCantidadSoles();
        
      //  int posicionZombie = plantsEnvironmentState.getPosicionZombie()[1];

      //   El Agente falla cuando la Planta se queda sin Soles
       if (cantidadSoles <= 0)
            return true;

        return false;
    }

    // Metodos especificos para la Planta
    
    public int[] getFilaDerecha(int row, int col) {
    	return ((EstadoJardin) this.environmentState).getDerecha(row, col);
    }
    
    public int[] getFilaIzquierda(int row, int col) {
    	return ((EstadoJardin) this.environmentState).getIzquierda(row, col);
    }
    
    public int[] getColumnaArriba(int row, int col) {
    	return ((EstadoJardin) this.environmentState).getArriba(row, col);
    }
    
    public int[] getColumnaAbajo(int row, int col) {
    	return ((EstadoJardin) this.environmentState).getAbajo(row, col);
    }
}
