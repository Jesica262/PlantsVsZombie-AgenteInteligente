package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoPlanta extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
    	
        if (((EstadoPlanta) agentState).isNoMoreZombie() && ((EstadoPlanta) agentState).tengoSol()) {
        	
            return true;
        }
        return false;
    }
}
