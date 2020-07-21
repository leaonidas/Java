package simulation;

import pec.PEC;

/**
 * @author Group 20
 * Move event is responsible to make each ant circulate through the graph
 */
public class Move extends Event {
	
	double alpha;
	double beta;
	double delta;
	double plevel;
	
	Path bestpath;

	/**
	 * @param t value to be set as event's time
	 * @param p refers to PEC where event is
	 * @param a refers to graph being analyzed
	 * @param path path with current values
	 * @param bp best path
	 * @param al value to be set as alpha
	 * @param b value to be set as beta
	 * @param d value to be set as delta
	 * @param e value to be set as eta
	 * @param r value to be set as rho
	 * @param pl value to be set as pheromone level
	 * Move event constructor
	 */
	public Move (double t, PEC p, AGraph a, Path path, Path bp, double al, double b, double d, double e, double r, double pl) {
		super(t, p, a, path, e, r);
		alpha=al;
		beta=b;
		delta=d;
		bestpath=bp;
		plevel=pl;
	}
	
	/**
	 * @param a value to be set as alpha
	 */
	public void setAlpha(double a) {
		alpha = a;
	}
	
	/**
	 * @param b value to be set as beta
	 */
	public void setBeta(double b) {
		beta = b;
	}
	
	/**
	 * @param d value to be set as delta
	 */
	public void setDelta(double d) {
		delta = d;
	}
	
	/**
	 * @param i line position
	 * @param prob matrix with probabilities for neighbours node
	 * @return next node in path
	 * Calculates probabilities for each node and chooses next non visited node
	 */
	public int goForward(int i, double prob[][]) {
		double ci=0;
		int n=0;
		int node=-1;
		
		for(int k=0; k<graph.nbnodes; k++) {
			if(path.verifyNode(k)==true  && path.getJ().getJij(i, k)==1 && k!=(graph.getNest()-1)) {
				ci+=calcC(graph.getPhero(i, k), graph.getWeight(i, k));
				prob[n][0]=calcC(graph.getPhero(i, k), graph.getWeight(i, k));
				prob[n][1]=k;
				n++;
			}
		}
		calcP(ci, prob);
		node=unifRand(prob);
		return node;
	}
	
	/**
	 * @param i line position
	 * @param prob matrix with probabilities for neighbours node
	 * @return next node
	 * Calculates probabilities for each node and chooses next visited node, begins backwards movement
	 */
	public int goBack(int i, double prob[][]) {
		double ci=0;
		int n=0;
		int node=-1;
		
		for(int k=0; k<graph.nbnodes; k++) {
			if(path.getJ().getJij(i, k)==1) {
				ci+=calcC(graph.getPhero(i, k), graph.getWeight(i, k));
				prob[n][0]=calcC(graph.getPhero(i, k), graph.getWeight(i, k));
				prob[n][1]=k;
				n++;
			}
		}
		calcP(ci, prob);
		node=unifRand(prob);
		return node;
	}
	
	
	/**
	 * @param f edge's pheromone level
	 * @param a edge's weight
	 * @return value of an intermediate step for probability calculation 
	 */
	public double calcC(double f, double a) {
		double c=(alpha+f)/(beta+a);
		return c;
	}
	
	/**
	 * @param ci sum of neighbours' weights
	 * @param prob matrix with probabilities for neighbours node
	 * Determines probability
	 */
	public void calcP(double ci, double prob[][]) {
		for(int j=0; j<prob.length; j++) {
			prob[j][0]=prob[j][0]/ci;
		}
	}
	
	/**
	 * @param prob matrix with probabilities for neighbours node
	 * @param sum vector with sum of neighbours probabilities
	 */
	public void calcSum(double prob[][], double sum[]) {
		double temp=0;
		for(int i=0; i<prob.length; i++) {
			temp+=prob[i][0];
			sum[i]=temp;
		}
	}
	
	/**
	 * @param p matrix with probabilities for neighbours node
	 * @return path's next node
	 * Randomly chooses path's next node, bearing in mind each node's probability
	 */
	public int unifRand(double p[][]) {
		int theone=0;
		double sum[]= new double[p.length];
		
		calcSum(p, sum);
		
		float f=(float)Math.random();
		
		for(int i=0; i<p.length; i++) {
			if(i==0) {
				if(f<sum[i])
					theone=(int)p[i][1];
			}
			else{
				if(f<sum[i] && f>sum[i-1])
					theone=(int)p[i][1];
			}
		}
		return theone;
	}

	/* (non-Javadoc)
	 * @see simulation.Event#simulateEvent()
	 * Calculates and schedules next event, being move, evaporation or print
	 */
	@Override
	public int simulateEvent() {
		int n, node, next;

		if (path.getCurrentPos()==0)
			node=(graph.getNest())-1;
		else
			node=path.getCurrentNode();
		
		
		if(path.lastNode()==true && graph.getEdge(node, graph.getNest()-1)!=null) {
			path.setNode(graph.getNest()-1, graph.getWeight(node, graph.getNest()-1));
			pec.addEvent(new Move(getTime()+getTime(graph.getWeight(node, graph.getNest()-1)*delta), pec, graph, path, bestpath, alpha, beta, delta, eta, rho, plevel));
			return 1;
		}
		
		if(path.completePath()==true) {
			
			graph.incPhero(path, plevel);
			Evaporation ev = new Evaporation(time+getTime(eta), pec, graph, path, rho, eta);
			for(int i = 0; i<path.nodes.length; i++) {
				ev.pathvec[i]=path.nodes[i];
			}
			pec.addEvent(ev);
			int w=0;
			for(int i = 0; i<path.nodes.length; i++) {
				w += bestpath.nodes[i];
			}
			if(w==0) {
				for(int i = 0; i<path.nodes.length; i++) {
					bestpath.nodes[i]=path.nodes[i];
					bestpath.weight[i]=path.weight[i];
				}
			}
			
			if(bestpath.comparePaths(path)==false) {
				for(int i = 0; i<path.nodes.length; i++) {
					bestpath.nodes[i]=path.nodes[i];
					bestpath.weight[i]=path.weight[i];
				}
			}
			
			path.clearNodes(graph);
			path.getJ().setJ(graph);
			pec.addEvent(new Move(time, pec, graph, path, bestpath, alpha, beta, delta, eta, rho, plevel));
			return 1;
		}
		
		
		n=path.countAdj(node);
		
		
		if(node==graph.getNest()-1 && n==0)
			return -1;
		
		
		if(n>0) {
			double prob[][]=new double[n][2];
			
			next=goForward(node, prob);
			
			path.setNode(next, graph.getWeight(node, next));

			path.getJ().setJij(node, next);
			
			double total= getTime()+getTime(graph.getWeight(node, next)*this.delta);
			
			pec.addEvent(new Move(total, pec, graph, path, bestpath, alpha, beta, delta, eta, rho, plevel));
			
			return 1;
		}
		
		else {
			
			n=path.countAdjBack(graph, node);
			
			double prob[][]=new double[n][2];
			
			next=goBack(node, prob);
			
			double t;
			
			if (path.getCurrentPos()==1) {
				t=2*getTime(graph.getWeight(node, next)*delta)+getTime(graph.getWeight(node, path.getNode(graph.getNest()))*delta);
			}
			else {
				t=2*getTime(graph.getWeight(node, next)*delta)+getTime(graph.getWeight(node, path.getNode(path.getCurrentPos()-2))*delta);
			}
			path.getJ().setJi(graph, node);

			path.deleteNode();
			
			pec.addEvent(new Move(getTime()+t, pec, graph, path,bestpath, alpha, beta, delta, eta, rho, plevel));
			
			
			
			return 3;
		}	
	}
}
