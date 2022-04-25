package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoAmbiente;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverIzquierda extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if (plantsState.getmatrizPosition(row, col) == PercepcionPlanta.PERCEPCION_VACIO) {

            plantsState.setRowPosition(row);
            return plantsState;
        }
        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        EstadoPlanta plantsState = ((EstadoPlanta) ast);

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if (row == 0) {
        	row = 8;
        } else {
        	row = row - 1;
        }

        if(!environmentState.hayZombie(row, col))
        {
        	plantsState.setRowPosition(row);
        	environmentState.setPosicionAgente(new int[] {row, col});
        }
        return environmentState;
    }

    @Override
    public Double getCost() {
        return new Double(0);
    }

    @Override
    public String toString() {
        return "GoLeft";
    }
}
