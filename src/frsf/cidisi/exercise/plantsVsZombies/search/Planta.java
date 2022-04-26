package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.Perception;
import java.util.Vector;

import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.exercise.plantsVsZombies.search.actions.AtacarZombie;
import frsf.cidisi.exercise.plantsVsZombies.search.actions.MoverArriba;
import frsf.cidisi.exercise.plantsVsZombies.search.actions.MoverDerecha;
import frsf.cidisi.exercise.plantsVsZombies.search.actions.MoverIzquierda;
import frsf.cidisi.exercise.plantsVsZombies.search.actions.PlantarGirasol;
import frsf.cidisi.exercise.plantsVsZombies.search.actions.TomarSol;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Planta extends SearchBasedAgent {

	public static EstadoPlanta estadoAnterior;
	public static EstadoPlanta estadoSiguienteAlRepetido;
	public static boolean seEvitoAccion = false;
	public static boolean accionMediante = false;
	public static boolean comprobarEstadoRepetido = false;
	
    public Planta() {

        // Objetivo deL Agente
        ObjetivoPlanta goal = new ObjetivoPlanta();

        // Estado del Agente
        EstadoPlanta plantsState = new EstadoPlanta();
        this.setAgentState(plantsState);

        // Creaccion de Operadores
        Vector<SearchAction> operators = new Vector<SearchAction>();
       
        operators.addElement(new AtacarZombie());
        operators.addElement(new MoverIzquierda());
        operators.addElement(new MoverArriba());
        operators.addElement(new MoverDerecha());
        operators.addElement(new AtacarZombie());
        operators.addElement(new TomarSol());
        operators.addElement(new PlantarGirasol());

        // Inicializando el Problema del Agente
        Problem problem = new Problem(goal, plantsState, operators);
        this.setProblem(problem);
    }

    @Override
    public Action selectAction() {

    	// Crea la estrategia de Busquedad en Profundidad
        DepthFirstSearch strategy = new DepthFirstSearch();
        
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.EFAIA_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(Planta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
		if(selectedAction == null && ((EstadoPlanta) this.getAgentState()).getCantidadSol() == 1) {

			((EstadoPlanta) this.getAgentState()).setCantidadSol(0);
		}
		else 
			if(comprobarEstadoRepetido && seEvitoAccion) {
				seEvitoAccion = false;
				estadoSiguienteAlRepetido = (EstadoPlanta) ((EstadoPlanta) this.getAgentState()).clone();
			} 

		// Return the selected action
		return selectedAction;
    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
