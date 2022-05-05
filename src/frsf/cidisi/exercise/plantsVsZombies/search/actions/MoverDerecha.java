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
       
        
        //agregar si hay zombie en la misma posicion descontar solZombie*2;
        if(col < 9) {
        	
        	if(plantsState.getmatrizPosition(row, col+1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
            		&& plantsState.getmatrizPosition(row, col+1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
            {
            	// Atacar zombie 
            	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col+1))
            	{
            		System.out.println("posicion : "+plantsState.getRowPosition()+"  "+plantsState.getColumnPosition()+"\n");
            		
            		System.out.println("cantidad sol : "+plantsState.getCantidadSol()+"  "+plantsState.getmatrizPosition(row, col+1)+"\n");
            		
            		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
            		plantsState.setColumnPosition(col+1);
            		plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col+1));
            		//plantsState.setContadorZombie(plantsState.getContadorZombie());
            	    System.out.println("cantidad cosas : "+plantsState.getCantidadSol()+"  "+plantsState.getContadorZombie()+"\n");
            		plantsState.setmatrizPosition(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
            		System.out.println("plantaa: "+plantsState);
            		return plantsState;
            	}
            
            }
            // Accion TomarSol
            else if(plantsState.haySol(row,col+1)) {
            	
            	plantsState.incrementarSol();
            	plantsState.setColumnPosition(col+1);
            	plantsState.setmatrizPosition(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
            }
            else if(plantsState.getmatrizPosition(row, col+1) == PercepcionPlanta.PERCEPCION_VACIO) {

                plantsState.setColumnPosition(col+1);
                System.out.println("hola agente : " + plantsState+"\n");
                
            
                //agregar de plantar planta si tiene soles.
                return plantsState;
            }
        }
        else
        {
        	return null;
        }
        return null;
}


    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoJardin environmentState = (EstadoJardin) est;
        EstadoPlanta plantsState = ((EstadoPlanta) ast);
       

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if(col < 9) {
        	
        	if(plantsState.getmatrizPosition(row, col+1) >= PercepcionPlanta.PERCEPCION_ENEMIGO1
            		&& plantsState.getmatrizPosition(row, col+1) <= PercepcionPlanta.PERCEPCION_ENEMIGO5)
            {
            	// Atacar zombie 
            	if(plantsState.getCantidadSol() > plantsState.getmatrizPosition(row, col+1))
            	{
            		plantsState.setColumnPosition(col+1);
            		plantsState.setCantidadSol(plantsState.getCantidadSol()-plantsState.getmatrizPosition(row, col+1));
            		plantsState.setContadorZombie(plantsState.getContadorZombie()+1);
            		plantsState.setmatrizPosition(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
            		environmentState.setMatriz(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
            		environmentState.setCantidadSoles(plantsState.getCantidadSol());
                    System.out.println("sol ambiente: "+environmentState.getCantidadSoles()+"  ---  "+plantsState.getCantidadSol()+"/n");
                    
            		
            		return environmentState;
            	}
            
            }
            // Accion TomarSol
            else if(plantsState.haySol(row,col+1)) {
            	
            	plantsState.incrementarSol();
            	plantsState.setColumnPosition(col+1);
            	plantsState.setmatrizPosition(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
            	environmentState.setMatriz(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
            }
            else if(plantsState.getmatrizPosition(row, col+1) == PercepcionPlanta.PERCEPCION_VACIO) {

                plantsState.setColumnPosition(col+1);
                environmentState.setPosicionAgente(plantsState.getPosicionPlants());
                environmentState.setMatriz(row, col+1, PercepcionPlanta.PERCEPCION_VACIO);
              //  plantsState.setCantidadSol(plantsState.getCantidadSol()-1);
                environmentState.setCantidadSoles(plantsState.getCantidadSol());
                System.out.println("hola ambiente : \n"+ plantsState.getCantidadSol()+ environmentState.getCantidadSoles()
                +" -  \n"+plantsState);
                
                //agregar de plantar planta si tiene soles.
                return environmentState;
            }
        }
        else
        {
        	return null;
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
