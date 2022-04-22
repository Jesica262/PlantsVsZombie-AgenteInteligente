package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PlantsSearchMain {
    
    public static void main(String[] args) throws PrologConnectorException {
        PlantsAgent plantsAgent = new PlantsAgent();
        
        PlantsEnvironment plantsEnvironment = new PlantsEnvironment();
        
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(plantsEnvironment, plantsAgent);
        
        simulator.start();
    }
}
