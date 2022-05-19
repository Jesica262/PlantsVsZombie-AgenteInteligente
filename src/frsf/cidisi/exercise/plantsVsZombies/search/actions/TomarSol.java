package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoJardin;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class TomarSol extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if(plantsState.haySol(row, col))
        {       	
        	plantsState.setCantidadSol(plantsState.getCantidadSol()+plantsState.incrementarSol(row, col));
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
	
        if(plantsState.haySol(row, col))
        {       	
        	plantsState.setCantidadSol(plantsState.getCantidadSol()+plantsState.incrementarSol(row, col));
        	plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_GIRASOL);
    		environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_GIRASOL);
    		environmentState.setCantidadSoles(plantsState.getCantidadSol());
        	
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
		 return "Tomar Sol";
	}
}

