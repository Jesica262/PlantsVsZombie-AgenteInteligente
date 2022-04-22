package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.PlantsAgentState;
import frsf.cidisi.exercise.plantsVsZombies.search.PlantsEnvironmentState;
import frsf.cidisi.exercise.plantsVsZombies.search.PlantsPerception;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class PlantarGirasol extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PlantsAgentState plantsState = (PlantsAgentState) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        /* The 'Eat' action can be selected only if there is food in the current
         * position. Otherwise return 'null'. */
        if (plantsState.getMatriz()[row][col] == PlantsPerception.SOL_PERCEPTION) {
            // If the action is Eat, then the actual position has no more food.
            plantsState.setmatrizPosition(row, col, col);
            return plantsState;
        }
        
        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PlantsEnvironmentState environmentState = (PlantsEnvironmentState) est;
        PlantsAgentState plantsState = ((PlantsAgentState) ast);

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if (environmentState.getMatriz()[row][col] == PlantsPerception.SOL_PERCEPTION) {
            // Update the real world
            environmentState.setMatriz(null);

            // Update the pacman state
            plantsState.setmatrizPosition(row, col, col);
            
            return environmentState;
        }

        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "Eat";
    }
}
