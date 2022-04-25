package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PlantaAmbiente extends Environment {

    public PlantaAmbiente() {
        // Crea el estado del entorno
        this.environmentState = new EstadoAmbiente();
    }

    @Override
    public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }

    @Override
    public Perception getPercept() {
        // Create a new perception to return
        PercepcionPlanta perception = new PercepcionPlanta();
        
        // Get the actual position of the agent to be able to create the
        // perception
        int row = this.getEnvironmentState().getPosicionAgente()[0];
        int col = this.getEnvironmentState().getPosicionAgente()[1];

        perception.setSensorFila(this.getFila(row));
        perception.setSensorColumna(this.getColumna(col));
        
     //estado del ambiente
        
        

        return perception;
    }

    @Override
    public String toString() {
        return environmentState.toString();
    }

    @Override
    public boolean agentFailed(Action actionReturned) {

        EstadoAmbiente plantsEnvironmentState = this.getEnvironmentState();

        int cantidadSoles = plantsEnvironmentState.getCantidadSoles();
        
      //  int posicionZombie = plantsEnvironmentState.getPosicionZombie()[1];

      //   El Agente falla cuando la Planta se queda sin Soles
       if (cantidadSoles <= 0)
            return true;

        return false;
    }

    // Metodos especificos para la Planta
    
    public int getMoverArriba(int row, int col) {
        return ((EstadoAmbiente) this.environmentState).getMoverArriba(row, col);
    }

    public int getMoverAbajo(int row, int col) {
        return ((EstadoAmbiente) this.environmentState).getMoverAbajo(row, col);
    }

    public int getMoverDerecha(int row, int col) {
        return ((EstadoAmbiente) this.environmentState).getMoverDerecha(row, col);
    }

    public int getMoverIzquierda(int row, int col) {
        return ((EstadoAmbiente) this.environmentState).getMoverIzquierda(row, col);
    }
    
    public int[] getFila(int row) {
    	return ((EstadoAmbiente) this.environmentState).getFila(row);
    }
    
    public int[] getColumna(int col) {
    	return ((EstadoAmbiente) this.environmentState).getColumna(col);
    }
}
