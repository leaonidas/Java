package simulation;

import pec.PEC;

/**
 * @author Group 20
 * Simulator class is responsible for initializing the PEC and the simulation itself
 */
public class Simulator {
	
	double finalinst;
	
	int antcolsize;
	
	double plevel;
	
	double alpha;
	
	double beta;
	
	double delta;
	
	double eta;
	
	double rho;
	
	int nbnodes;
	
	int nestnode;
	
	public AGraph graph;
	
	private PEC pec=new PEC();
	
	int nMoves;
	
	int nEvaporations;
	
	public int getMoves() {
		return nMoves;
	}
	
	public int getEvaporations() {
		return nEvaporations;
	}
	
	public double getFinalinst() {
		return finalinst;
	}
	
	public void setFinalinst(double f) {
		finalinst=f;
	}
	
	public void setAntColSize(int i) {
		antcolsize=i;
	}
	
	public void setPlevel(double p) {
		plevel=p;
	}
	
	public void setAlpha(double a) {
		alpha=a;
	}
	
	public void setBeta(double b) {
		beta=b;
	}
	
	public void setDelta(double d) {
		delta=d;
	}
	
	public void setEta(double e) {
		eta=e;
	}
	
	public void setRho(double r) {
		rho=r;
	}
	
	public void setNbnodes(int i) {
		nbnodes=i;
	}
	public int getNbnodes() {
		return nbnodes;
	}
	
	public void setNest(int i) {
		nestnode=i;
	}
	public int getNest() {
		return nestnode;
	}
	
	/**
	 * @param g graph
	 * @param f finalinstant
	 * @param col size of ant colony
	 * @param pl pheromone level
	 * @param a alpha
	 * @param b beta
	 * @param d delta
	 * @param e eta
	 * @param r rho
	 */
	public Simulator(AGraph g, double f, int col, double pl, double a, double b, double d, double e, double r) {
		
		graph=g;
		finalinst=f;
		antcolsize=col;
		plevel=pl;
		alpha=a;
		beta=b;
		delta=d;
		eta=e;
		rho=r;
		
	}
	
	/**
	 * Constructor
	 */
	public Simulator () {

	}
	
	/**
	 * @param sim simulation initialized in main
	 * Initializes simulation by adding a move event for each ant and a print event.
	 */
	public void simulate(Simulator sim) {
		
		Event currentEvent=null;
		Ants ants=new Ants(antcolsize, graph.getNbnodes(), graph.getNest());
		
		double currentTime=0;
		/*add first move of each ant to PEC*/
		for(int i=0; i<antcolsize; i++) {
			
			ants.getPath(i).getJ().setJ(graph);
			pec.addEvent(new Move(currentTime, pec, graph, ants.getPath(i), ants.bestPath, alpha, beta, delta, eta, rho, plevel));
			
		}
		pec.addEvent(new Print(finalinst/20, pec, graph, ants.bestPath, rho, eta, 1, nMoves, nEvaporations, finalinst, sim));
		currentEvent=pec.nextEvent();

		while(currentEvent.getTime()<=finalinst) {
			
			int n=0;
			
			n=currentEvent.simulateEvent();
			
			if(currentEvent instanceof Move)
				nMoves+=n;
			else
				nEvaporations+=n;
			currentEvent=pec.nextEvent();
			
		}	
	}
}
