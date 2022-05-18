package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoJardin;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverAbajo extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

        int row = plantsState.getRowPosition();

        // Mover Abajo significa que va de la fila 0 a la 4 permaneciendo en la misma columna, por lo que el valor de la fila va aumentando 
        if (row < 5)
        {
        	plantsState.setRowPosition(row+1);
        	
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
	
    	if(row < 5) 
    	{
    		plantsState.setRowPosition(row+1);
    		environmentState.setPosicionAgente(new int[]{row +1, col}); 
    		
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
		 return "Mover Abajo";
	}
}

