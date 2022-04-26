package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoJardin;
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
        
     
        if ((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO5) && 
        		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO5)) {
        	
            plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
            plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO5);
            return plantsState;
        } 
        else if((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO4) &&
        		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO4))
        	{
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        	    plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO4);
        		return plantsState;
        	}
        else if((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO3) &&
        		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO3))
        	{
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        	    plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO3);
        		return plantsState;
        	}else if((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO2) &&
            		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO2))
            	{
            		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            	    plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
            		plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO2);
            		return plantsState;
            	}else if((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO1) &&
                		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO1))
                	{
                		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
                		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
                		plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO1);
                		return plantsState;
                	}
        

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoJardin environmentState = (EstadoJardin) est;
        EstadoPlanta plantsState = ((EstadoPlanta) ast);
        
        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        row = environmentState.getPosicionAgente()[0];
        col = environmentState.getPosicionAgente()[1];

        if ((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO5) && 
        		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO5)) {
        		
        		environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO5);
            
        		return environmentState;
        } 
        else if((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO4) &&
        		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO4))
        	{
        		environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO4);
        
        		return environmentState;
        	}
        else if((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO3) &&
        		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO3))
        	{
        		environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO3);
    
        		return environmentState;
        		
        	}else if((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO2) &&
            		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO2))
            	{
        			environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        			plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        			plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        			plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO2);
        
        			return environmentState;
        			
            	}else if((plantsState.getmatriz()[row][col] == PercepcionPlanta.PERCEPCION_ENEMIGO1) &&
                		(plantsState.getcantidadSol() > PercepcionPlanta.PERCEPCION_ENEMIGO1))
                	{
            			environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            			plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            			plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
            			plantsState.setCantidadSol(plantsState.getcantidadSol() - PercepcionPlanta.PERCEPCION_ENEMIGO1);
            
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
