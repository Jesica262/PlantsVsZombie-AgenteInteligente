package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.PlantsAgentState;
import frsf.cidisi.exercise.plantsVsZombies.search.PlantsEnvironmentState;
import frsf.cidisi.exercise.plantsVsZombies.search.PlantsPerception;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverArriba extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        PlantsAgentState plantsState = (PlantsAgentState) s;

       // plantsState.increaseVisitedCellsCount();

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if (col == 4) {
        	col = 0;
        } else {
        	col = col + 1;
        }

        plantsState.setColumnPosition(col);

        if (plantsState.getmatrizPosition(row, col) != PlantsPerception.VACIO_PERCEPTION) {

            plantsState.setmatrizPosition(row, col, PlantsPerception.VACIO_PERCEPTION);

            return plantsState;
        }

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        PlantsEnvironmentState environmentState = (PlantsEnvironmentState) est;
        PlantsAgentState plantsState = ((PlantsAgentState) ast);

       // plantsState.increaseVisitedCellsCount();

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if (col == 4) {
        	col = 0;
        } else {
        	col = col + 1;
        }

        plantsState.setColumnPosition(col);

        environmentState.setPosicionAgente(new int[] {row, col});
        
        return environmentState;
    }

    @Override
    public Double getCost() {
        return new Double(0);
    }

    @Override
    public String toString() {
        return "GoUp";
    }
}
