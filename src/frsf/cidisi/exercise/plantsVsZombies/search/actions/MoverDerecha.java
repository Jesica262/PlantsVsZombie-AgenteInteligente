package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoAmbiente;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverDerecha extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

     //   plantsState.increaseVisitedCellsCount();

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if (row == 8) {
        	row = 0;
        } else {
        	row = row + 1;
        }

        plantsState.setRowPosition(col);

        if (plantsState.getmatrizPosition(row, col) == PercepcionPlanta.DESCONOCIDO_PERCEPTION) {

            plantsState.setmatrizPosition(row, col, PercepcionPlanta.VACIO_PERCEPTION);
        }

        return plantsState;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        EstadoPlanta plantsState = ((EstadoPlanta) ast);

      //  plantsState.increaseVisitedCellsCount();

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if (row == 8) {
        	row = 0;
        } else {
        	row = row + 1;
        }

        plantsState.setRowPosition(col);

        environmentState.setPosicionAgente(new int[] {row, col});

        return environmentState;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public String toString() {
        return "GoRight";
    }
}
