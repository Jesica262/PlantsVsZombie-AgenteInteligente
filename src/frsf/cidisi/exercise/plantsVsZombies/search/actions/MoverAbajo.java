package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoAmbiente;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverAbajo extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

       // plantsState.increaseVisitedCellsCount(); incrementar para abajo

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        // Check the limits of the world
        if (col == 0) {
            col = 4;
        } else {
            col = col - 1;
        }

        plantsState.setColumnPosition(col);

        if (plantsState.getmatrizPosition(row, col) != PercepcionPlanta.VACIO_PERCEPTION) {

            plantsState.setmatrizPosition(row, col, PercepcionPlanta.VACIO_PERCEPTION);

            return plantsState;
        }

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        EstadoPlanta plantsState = ((EstadoPlanta) ast);

//        plantsState.increaseVisitedCellsCount();

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if (col == 0) {
        	col = 4;
        } else {
        	col = col - 1;
        }

        plantsState.setColumnPosition(col);

        environmentState.setPosicionAgente(new int[] {row, col});
        
        return environmentState;
    }

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}

