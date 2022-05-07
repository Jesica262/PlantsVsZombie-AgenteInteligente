package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoJardin;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverIzquierda extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if(plantsState.getmatrizPosition(row, col) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row, col) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
    		// Atacar zombie 
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col)*2)
        	{
        		// Preguntar ala profe
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col)*2);
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            	
            	return plantsState;
   
        	}
        }
        else if (plantsState.getmatrizPosition(row, col) == PercepcionPlanta.PERCEPCION_VACIO)
        {
        	if(col>0)
        	{
        		if(plantsState.getmatrizPosition(row, col-1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
                		&& plantsState.getmatrizPosition(row, col-1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
                {
                	// Atacar zombie 
                	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col-1))
                	{
                		// Preguntar ala profe
                		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
                		plantsState.setColumnPosition(col-1);
                		plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col-1));
                		plantsState.setmatrizPosition(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
         
                		plantsState.setCeldasVisitadas(plantsState.getCeldasVisitadas()+1);
                    	//System.out.println("celdas visitadas: "+plantsState.getCeldasVisitadas()+" ---  "+plantsState.getContadorZombie()+"\n");
                    	return plantsState;
           
                	}
                }
            	
                // Accion TomarSol
                else if(plantsState.haySol(row,col-1)) {
                	
                	plantsState.incrementarSol();
                	plantsState.setColumnPosition(col-1);
                	plantsState.setmatrizPosition(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
                	plantsState.setCeldasVisitadas(plantsState.getCeldasVisitadas()+1);
                	
                	return plantsState;
              
                }
                else {

                    plantsState.setColumnPosition(col-1);
                	plantsState.setCeldasVisitadas(plantsState.getCeldasVisitadas()+1);
                	
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
        		plantsState.setCeldasVisitadas(plantsState.getCeldasVisitadas()+1);
            	environmentState.setCeldasVisitadas(plantsState.getCeldasVisitadas());
            	System.out.println("celdas visitadas: "+plantsState.getCeldasVisitadas()+" ---  "+plantsState.getContadorZombie()+"\n");
                
            	return environmentState;
   
        	}
        }
    	else if (plantsState.getmatrizPosition(row, col) >= PercepcionPlanta.PERCEPCION_VACIO) 
    	{
    		if(col > 0) {
        
        	 if(plantsState.getmatrizPosition(row, col-1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
            		&& plantsState.getmatrizPosition(row, col-1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
            {
            	// Atacar zombie 
            	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col-1))
            	{
            		
            		plantsState.setColumnPosition(col-1);
            		plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col-1));
            		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
            		environmentState.setContadorZombie(plantsState.getContadorZombie());
            		plantsState.setmatrizPosition(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
            		environmentState.setMatriz(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
            		environmentState.setCantidadSoles(plantsState.getCantidadSol());
            		plantsState.setCeldasVisitadas(plantsState.getCeldasVisitadas()+1);
                	environmentState.setCeldasVisitadas(plantsState.getCeldasVisitadas());
                	//System.out.println("celdas visitadas: "+plantsState.getCeldasVisitadas()+" ---  "+plantsState.getContadorZombie()+"\n");
                    
                	return environmentState;
            		
            	}
            
            }
            // Accion TomarSol
            else if(plantsState.haySol(row,col-1)) {
            	
            	plantsState.incrementarSol();
            	plantsState.setColumnPosition(col-1);
            	plantsState.setmatrizPosition(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
            	environmentState.setMatriz(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
            	plantsState.setCeldasVisitadas(plantsState.getCeldasVisitadas()+1);
            	environmentState.setCeldasVisitadas(plantsState.getCeldasVisitadas());
            	
            	return environmentState;
            	
            }
            else if(plantsState.getmatrizPosition(row, col-1) == PercepcionPlanta.PERCEPCION_VACIO) {

                plantsState.setColumnPosition(col-1);
                environmentState.setPosicionAgente(plantsState.getPosicionPlants());
                environmentState.setMatriz(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
                plantsState.setCantidadSol(plantsState.getCantidadSol());
                environmentState.setCantidadSoles(plantsState.getCantidadSol());
            	plantsState.setCeldasVisitadas(plantsState.getCeldasVisitadas()+1);
            	environmentState.setCeldasVisitadas(plantsState.getCeldasVisitadas());
            	
            	return environmentState;

            }
        }
        else
        {
        	return null;
        }
    		
    }
		return null;
    }

    @Override
    public Double getCost() {
        return new Double(0);
    }

    @Override
    public String toString() {
        return "Mover Izquierda";
    }
}
