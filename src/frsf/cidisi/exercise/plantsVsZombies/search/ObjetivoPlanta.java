package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoPlanta extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
    	
    	EstadoPlanta estadoPlanta = (EstadoPlanta) agentState;
    	
    	if(estadoPlanta.getContadorZombie() == estadoPlanta.getZombieTotal() && estadoPlanta.getCantidadSol()>0)
    	{
    		return true;
    	}
    	else if(estadoPlanta.getCantidadZombie() == 0 && estadoPlanta.getCantidadSol()>0)
    	{
    		return true;
    	}
    
    	return false;
    }
}

