package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoAmbiente;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class AtacarZombie extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoPlanta plantsState = (EstadoPlanta) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if ((plantsState.getmatriz()[row][col] == 1) && (plantsState.getcantidadSol() > 30)) {

            // There is no more enemy if we fight against it
            plantsState.setmatrizPosition(row, col, PercepcionPlanta.VACIO_PERCEPTION);
            
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

        if ((environmentState.getMatriz()[row][col] == 1)/* && (plantsState.getCantidadSol()>environmentState.getNivel())*/ ) {
            
        	environmentState.setMatriz(row, col, PercepcionPlanta.VACIO_PERCEPTION);
            plantsState.setmatrizPosition(row, col, PercepcionPlanta.VACIO_PERCEPTION);
           // plantsState.setCantidadSol(plantsState.getCantidadSol());
            
            return environmentState;
        }

        return null;
    }
  
    @Override
    public Double getCost() {
        return new Double(0);
    }

    @Override
    public String toString() {
        return "Fight";
    }
}
