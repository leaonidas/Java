package simulation;

import pec.PEC;
import simulation.Path;

/**
 * @author Group 20
 * Print Class is responsible for printing desired output in a determined time interval
 */
public class Print extends Event{

	int n;
	int nm;
	int ne;
	double instFin;
	Simulator sim;
	
	/**
	 * @param t value to be set as time
	 * @param p refers to PEC where event is
	 * @param a refers to graph being analyzed
	 * @param pa path with current values
	 * @param e value to be set as eta
	 * @param r value to be set as rho
	 * @param nmb number of iterations
	 * @param moves number of moves
	 * @param evap number of evaporations
	 * @param fin final instant
	 * @param s simulator
	 */
	public Print (double t, PEC p, AGraph a, Path pa, double r, double e, int nmb, int moves, int evap, double fin, Simulator s) {
		super(t, p, a, pa, e, r);
		n=nmb;
		nm=moves;
		ne=evap;
		instFin=fin;
		sim=s;
	}

	/* (non-Javadoc)
	 * @see simulation.Event#simulateEvent()
	 * Prints desired output
	 */
	@Override
	public int simulateEvent() {
		System.out.println("Observation "+ n+":");
		System.out.println("\tPresent instant: "+time);
		System.out.println("\tNumber of move events: "+nm);
		System.out.println("\tNumber of evaporation events: "+ne);
		System.out.print("\tHamiltonian cycle: ");
		if(path.nodes[0]!=0) {
			System.out.println(toString(path));
		}
		System.out.println();
		n++;
		pec.addEvent(new Print(getTime()+instFin/20, pec, graph, path, rho, eta, n, sim.getMoves(), sim.getEvaporations(), instFin, sim));
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see simulation.Event#toString(simulation.Path)
	 */
	@Override
	public String toString(Path pa) {
		String bestPath="{" + (pa.nodes[pa.nodes.length-1]+1)+",";
		
		for(int i = 0; i<pa.nodes.length-1; i++) {
			bestPath = bestPath + (pa.nodes[i]+1);
			if(i!=pa.nodes.length-2) {
				bestPath = bestPath + ",";
			}
		}
		bestPath = bestPath + "}";
		
		return bestPath;
	}	
}
