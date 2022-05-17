package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoJardin;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverDerecha extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if(plantsState.getmatrizPosition(row, col) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row, col) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {        	
        	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col)*2);
    		// Atacar zombie 
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col)*2)
        	{
        		// Preguntar ala profe
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);            	
        		System.out.println("\n Agente State: "+plantsState+"\n");
            	return plantsState;
        	}
        	else {
        		return null;
        	}
        }
        else if(plantsState.haySol(row,col)) {
        
        	plantsState.setCantidadSol(plantsState.getCantidadSol()+plantsState.incrementarSol(row, col));
        	plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        	//plantsState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX()+1);
        	System.out.println("\n Agente State: "+plantsState+"\n");
        	return plantsState;
        }
        else if (plantsState.getmatrizPosition(row, col) == PercepcionPlanta.PERCEPCION_VACIO)
        {
        	if(col<8)
        	{
        		if((col==0) 
        				&& plantsState.getmatrizPosition(row, col+1) == PercepcionPlanta.PERCEPCION_VACIO 
        				&& plantsState.getCantidadSol()>1)
        		{
	        			int valor = plantsState.numeroRandomGirasol();
	                	plantsState.setCantidadSol(plantsState.getCantidadSol()-1);
	                	plantsState.setColumnPosition(col+1);
	                	plantsState.setmatrizPosition(row, col, valor+5);
	   
	                	return plantsState;        		
                }   
        		else if(plantsState.getmatrizPosition(row, col+1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
                		&& plantsState.getmatrizPosition(row, col+1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
                {        			
        			plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col+1));
                	// Atacar zombie 
                	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col+1))
                	{
                		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
                		plantsState.setColumnPosition(col+1);
                		plantsState.setmatrizPosition(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
                		plantsState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX()+1);
                		System.out.println("\n Agente State: "+plantsState+"\n");
                		
                		return plantsState;          
                	}
                	else
                	{
                		return null;
                	}
                }
                else {

                    plantsState.setColumnPosition(col+1);
                	plantsState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX()+1);
                	System.out.println("\n Agente State: "+plantsState+"\n");
                	return plantsState;
                }
        	}
        	else {
        		return null;
        	}
        }
		return null;
    }
      

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoJardin environmentState = (EstadoJardin) est;
        EstadoPlanta plantsState = ((EstadoPlanta) ast);
       

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

    	if(plantsState.getmatrizPosition(row, col) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row, col) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
    		// Atacar zombie 
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col)*2)
        	{
        		plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col)*2);
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		environmentState.setContadorZombie(plantsState.getContadorZombie());
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		environmentState.setCantidadSoles(plantsState.getCantidadSol());
 
            	return environmentState;
   
        	}
        	else {
        		return null;
        	}
        }
    	else if(plantsState.haySol(row,col)) {
    	        
         	plantsState.setCantidadSol(plantsState.getCantidadSol()+plantsState.incrementarSol(row, col));
         	plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
         	environmentState.setCantidadSoles(plantsState.getCantidadSol());
         	environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);         	
         	
         	System.out.println("\n Ambiente State: "+environmentState+"\n");
         	return environmentState;
       
        }
    	else if (plantsState.getmatrizPosition(row, col) == PercepcionPlanta.PERCEPCION_VACIO) 
    	{
    		
    	if(col < 8) {
    		
    		if(plantsState.getmatrizPosition(row, col+1) == PercepcionPlanta.PERCEPCION_VACIO && plantsState.getCantidadSol()>1)
    		{
    			if(col==0)
    			{
    				int valor = plantsState.numeroRandomGirasol();
                	plantsState.setCantidadSol(plantsState.getCantidadSol()-1);
                	plantsState.setmatrizPosition(row, col, valor+5);
                	plantsState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX()+1);
                	plantsState.setColumnPosition(col+1);
                	environmentState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX());
                	environmentState.setCantidadSoles(plantsState.getCantidadSol());
                	environmentState.setMatriz(row, col+1, valor);

                	return environmentState;
    			}
    			
            }  
    		else if(plantsState.getmatrizPosition(row, col+1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
            		&& plantsState.getmatrizPosition(row, col+1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
            {
        		 
          		plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col+1));
          		
            	// Atacar zombie 
            	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col+1))
            	{
            		
            		plantsState.setColumnPosition(col+1);
            		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
            		environmentState.setContadorZombie(plantsState.getContadorZombie());
            		plantsState.setmatrizPosition(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
            		environmentState.setMatriz(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
            		environmentState.setCantidadSoles(plantsState.getCantidadSol());
            		plantsState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX()+1);
                	environmentState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX());
                	//System.out.println("celdas visitadas: "+plantsState.getCeldasVisitadas()+" ---  "+plantsState.getContadorZombie()+"\n");
                    
                	return environmentState;                    		
            	}
            	else
            	{
            		return null;
            	}
            }
            else {

            	         	
                plantsState.setColumnPosition(col+1);
            	plantsState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX()+1);
            	plantsState.setZombiePercibidos(plantsState.getZombiePercibidos());
            	environmentState.setCeldasVisitadasX(plantsState.getCeldasVisitadasX());
            	environmentState.setZombiePercibido(plantsState.getZombiePercibidos());
            	System.out.println("\n Agente State: "+plantsState+"\n");
            	return environmentState;
            }
    	}
    	else {
    		return null;
    	}
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
