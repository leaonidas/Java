package simulation;

import pec.PEC;

/**
 * @author Group 20
 * Evaporation event decrements edge's pheromne level
 */
public class Evaporation extends Event {
	
	int [] pathvec;

	
	/**
	 * @param t value to be set as time
	 * @param p refers to PEC where event is
	 * @param a refers to graph being analyzed
	 * @param pa path with current values
	 * @param e value to be set as eta
	 * @param r value to be set as rho
	 * Evaporation constructor
	 */
	public Evaporation (double t, PEC p, AGraph a, Path pa, double r, double e) {
		super(t, p, a, pa, e, r);
		pathvec = new int[a.nbnodes];
	}
	
	/**
	 * @param t value to be set as time
	 * @param p refers to PEC where event is
	 * @param a refers to graph being analyzed
	 * @param pa path with current values
	 * @param e value to be set as eta
	 * @param r value to be set as rho
	 * @param pathve vector where current path will be stored
	 */
	public Evaporation (double t, PEC p, AGraph a, Path pa, double r, double e, int[] pathve) {
		super(t, p, a, pa, e, r);
		pathvec = pathve;
	}
	
	/* (non-Javadoc)
	 * @see simulation.Event#simulateEvent()
	 */
	@Override
	public int simulateEvent() {
		int count=0;
		double temp=0;
		
		if(graph.getPhero(pathvec[pathvec.length-1], pathvec[0])!=0) {
			temp=graph.getPhero(pathvec[pathvec.length-1],  pathvec[0])-rho;
			if(temp<0) {
				temp=0;
			}
			graph.setPhero(temp, pathvec[pathvec.length-1], pathvec[0]);
			graph.setPhero(temp, pathvec[0], pathvec[pathvec.length-1]);
			count++;
		}
		for(int i=0; i<pathvec.length-1; i++ ) {
			if(graph.getPhero(pathvec[i], pathvec[i+1])!=0) {
				temp=graph.getPhero(pathvec[i],  pathvec[i+1])-rho;
				
				if(temp<0) {
					temp=0;
				}
				graph.setPhero(temp, pathvec[i], pathvec[i+1]);
				graph.setPhero(temp, pathvec[i+1], pathvec[i]);
				count++;
			}
		}
		
		if (count!=0) {
			pec.addEvent(new Evaporation(time+getTime(eta), pec, graph, path, rho, eta, pathvec));
			return count;
		}
		return 0;
		
	}
}
