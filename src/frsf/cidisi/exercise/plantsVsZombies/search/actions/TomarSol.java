package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoAmbiente;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
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

        if (plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_SOL) {
         
            plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            plantsState.incrementarSol();
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

        if (environmentState.getMatriz()[row][col] == PercepcionPlanta.PERCEPCION_SOL) {
        
            environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            plantsState.incrementarSol();
            
            return environmentState;
        }

        return null;
    }
    
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
