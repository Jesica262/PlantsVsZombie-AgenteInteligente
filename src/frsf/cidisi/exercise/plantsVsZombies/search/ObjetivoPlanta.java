package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoPlanta extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
    	
    	EstadoPlanta estadoPlanta = (EstadoPlanta) agentState;
    
    	return (estadoPlanta.getContadorZombie() == estadoPlanta.getZombiePercibidos());
    }
}

