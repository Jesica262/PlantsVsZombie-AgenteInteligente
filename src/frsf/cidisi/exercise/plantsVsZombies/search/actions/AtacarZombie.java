package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plantsVsZombies.search.EstadoPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.PercepcionPlanta;
import frsf.cidisi.exercise.plantsVsZombies.search.EstadoJardin;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class AtacarZombie extends SearchAction {

	// Verificar en la posisicion si hay zombie, descuenta el doble, si sigue con sol elimina zombie, vacio matriz, suma contZombie, sino
	// verifica posiciones adyacentes
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoPlanta plantsState = (EstadoPlanta) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if(plantsState.getmatrizPosition(row, col) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row, col) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {       	
        	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col)*2);
    	
        	if(plantsState.getCantidadSol() > 0)
        	{       		
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return plantsState;
        	}
        	else {
        		return null;
        	}
        }

        if(col<8 && plantsState.getmatrizPosition(row, col+1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row, col+1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
        	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col+1))
        	{
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col+1));
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setColumnPosition(col+1);
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return plantsState;
        	}
        	else {
        		return null;
        	}
        }
        else if(col>0 && plantsState.getmatrizPosition(row, col-1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row, col-1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
        	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col-1))
        	{
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col-1));
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setColumnPosition(col-1);
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return plantsState;
        	}
        	else {
        		return null;
        	}
        }
        else if(row<4 && plantsState.getmatrizPosition(row+1, col) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row+1, col) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
        	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row+1, col))
        	{       		
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row+1, col));
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row+1, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setRowPosition(row+1);
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return plantsState;
        	}
        	else {
        		return null;
        	}
        }
        else if(row>0 && plantsState.getmatrizPosition(row-1, col) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row-1, col) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
        	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row-1, col))
        	{       		
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row-1, col));
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row-1, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setRowPosition(row-1);
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return plantsState;
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
    	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col)*2)
        	{       	
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col)*2);
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		environmentState.setContadorZombie(plantsState.getContadorZombie());
        		environmentState.setMatriz(row, col, PercepcionPlanta.PERCEPCION_VACIO);
        		environmentState.setCantidadSoles(plantsState.getCantidadSol());
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return environmentState;
        	}
        	else {
        		return null;
        	}
        }
        else if(col<8 && plantsState.getmatrizPosition(row, col+1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row, col+1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
        	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col+1))
        	{       
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col+1));
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setColumnPosition(col+1);
        		environmentState.setContadorZombie(plantsState.getContadorZombie());
        		environmentState.setMatriz(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
        		environmentState.setPosicionAgente(new int[] {row, col+1});
        		environmentState.setCantidadSoles(plantsState.getCantidadSol());
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return environmentState;
        	}
        	else {
        		return null;
        	}
        }
        else if(col>0 && plantsState.getmatrizPosition(row, col-1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row, col-1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
        	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col-1))
        	{       
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col-1));
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setColumnPosition(col-1);
        		environmentState.setContadorZombie(plantsState.getContadorZombie());
        		environmentState.setMatriz(row, col-1, PercepcionPlanta.PERCEPCION_VACIO);
        		environmentState.setPosicionAgente(new int[] {row, col-1});
        		environmentState.setCantidadSoles(plantsState.getCantidadSol());
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return environmentState;
        	}
        	else {
        		return null;
        	}
        }
        else if(row<4 && plantsState.getmatrizPosition(row+1, col) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row+1, col) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
        	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row+1, col))
        	{    
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row+1, col));
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row+1, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setRowPosition(row+1);
        		environmentState.setContadorZombie(plantsState.getContadorZombie());
        		environmentState.setMatriz(row+1, col, PercepcionPlanta.PERCEPCION_VACIO);
        		environmentState.setPosicionAgente(new int[] {row+1, col});
        		environmentState.setCantidadSoles(plantsState.getCantidadSol());
        		
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return environmentState;
        	}
        	else {
        		return null;
        	}
        }
        else if(row>0 && plantsState.getmatrizPosition(row-1, col) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
        		&& plantsState.getmatrizPosition(row-1, col) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
        {
        	
        	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row-1, col))
        	{
            	plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row-1, col));
        		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
        		plantsState.setmatrizPosition(row-1, col, PercepcionPlanta.PERCEPCION_VACIO);
        		plantsState.setRowPosition(row-1);
        		environmentState.setContadorZombie(plantsState.getContadorZombie());
        		environmentState.setMatriz(row-1, col, PercepcionPlanta.PERCEPCION_VACIO);
        		environmentState.setPosicionAgente(new int[] {row-1, col});
        		environmentState.setCantidadSoles(plantsState.getCantidadSol());
            	
        	//	System.out.println("\n Agente State: "+plantsState+"\n");
            	return environmentState;
        	}
        	else {
        		return null;
        	}
        }
        
        return null;
    }

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		 return "Atacar Zombie";
	}
}

