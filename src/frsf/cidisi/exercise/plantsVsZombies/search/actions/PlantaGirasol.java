package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoJardin;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

// Se planta unicamente en la primer columna.
public class PlantaGirasol extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if(row == 0 && plantsState.getmatrizPosition(row, col) == PercepcionPlanta.PERCEPCION_VACIO && plantsState.getCantidadSol()>1)
        {       	
        	plantsState.setCantidadSol(plantsState.getCantidadSol()-1);
        	plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_GIRASOL);
        	
        	return plantsState;
        }
        
        return null;
    }     

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoJardin environmentState = (EstadoJardin) est;
        EstadoPlanta plantsState = ((EstadoPlanta) ast);
       
        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];
        
        if(row == 0 && plantsState.getmatrizPosition(row, col) == PercepcionPlanta.PERCEPCION_VACIO && plantsState.getCantidadSol()>1)
        {       	
        	plantsState.setCantidadSol(plantsState.getCantidadSol()-1);
        	plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_GIRASOL);
        	environmentState.setCantidadSoles(plantsState.getCantidadSol());
        	environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_GIRASOL);
        	
        	return environmentState;
        }
        
        return null;	      
    }

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		 return "Atacar Zombie";
	}
}

