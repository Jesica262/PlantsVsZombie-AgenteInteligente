package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoJardin;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverDerecha extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

        int col = plantsState.getColumnPosition();

        if(col<8)
    	{
        	plantsState.setColumnPosition(col+1);
        	plantsState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX()+1);
       //     System.out.println("\n Agente State: "+plantsState+"\n");
            
            return plantsState;
         }
        return null;
    }     

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoJardin environmentState = (EstadoJardin) est;
        EstadoPlanta plantsState = ((EstadoPlanta) ast);
       
        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if(col<8)
    	{
        	plantsState.setColumnPosition(col+1);
        	plantsState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX()+1);
        	environmentState.setPosicionAgente(new int[] {row,col+1});
        //    System.out.println("\n Ambiente State: "+environmentState+"\n");
            
            return environmentState;
         }
        return null;   
    }

    @Override
    public String toString() {
        return "Mover Derecha";
    }


	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}
}
