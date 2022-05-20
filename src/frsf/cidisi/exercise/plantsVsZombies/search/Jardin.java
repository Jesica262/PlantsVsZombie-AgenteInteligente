package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
    
    	this.getEnvironmentState().actualizarZombies();
    	this.getEnvironmentState().actualizarGirasoles();
    	
        PercepcionPlanta perception = new PercepcionPlanta();
        
        int row = this.getEnvironmentState().getPosicionAgente()[0];
        int col = this.getEnvironmentState().getPosicionAgente()[1];
        
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }  
        
		perception.setPosicion(this.getEnvironmentState().getPosicionAgente());
	    perception.setCantidadZombie(this.getEnvironmentState().getCantidadZombie());
	    perception.setCantidadSol(this.getEnvironmentState().getCantidadSoles());
	    perception.setZombieTotal(this.getEnvironmentState().getZombieTotal());
	    perception.setCantidadZombie(this.getEnvironmentState().getCantidadZombie());
	    perception.setListZombies(this.getEnvironmentState().getListZombies());
	    perception.setContadorPlanta(this.getEnvironmentState().getContadorPlanta());
	    perception.setListGirasoles(this.getEnvironmentState().getListGirasoles());
	    
	    perception.setSensorFilaDerecha(this.getEnvironmentState().getDerecha(row, col));
	    perception.setSensorFilaIzquierda(this.getEnvironmentState().getIzquierda(row, col));
	    perception.setSensorColumnaArriba(this.getEnvironmentState().getArriba(row, col));
	    perception.setSensorColumnaAbajo(this.getEnvironmentState().getAbajo(row, col));
    	
        return perception;
    }

    @Override
    public String toString() {
        return environmentState.toString();
    }

    @Override
    public boolean agentFailed(Action actionReturned) {

        EstadoJardin estadoJardin = this.getEnvironmentState();

        int cantidadSoles = estadoJardin.getCantidadSoles();
  
      //   El Agente falla cuando la Planta se queda sin Soles y si el Zombie llego ala casilla inicial.
       if ((cantidadSoles <= 0 ) || estadoJardin.isZombieLlego())
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
