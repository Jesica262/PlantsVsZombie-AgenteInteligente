package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class InicioPlanta {
    
    public static void main(String[] args) throws PrologConnectorException {
        Planta plantsAgent = new Planta();
        
        Jardin plantsEnvironment = new Jardin();
        
        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(plantsEnvironment, plantsAgent);
        
        simulator.start();
    }
}
