package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PlantsGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
    	
        if (((PlantsAgentState) agentState).isNoMoreZombie() && ((PlantsAgentState) agentState).tengoSol()) {
        	
            return true;
        }
        return false;
    }
}
