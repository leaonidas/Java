package simulation;



import simulation.Path;

/**
 * @author Group 20
 *AGraph class refers to the adjacency matrix that identifies the graph.
 *The matrix is composed of edges which have the weight and pheromone level.  
 */
public class AGraph extends Graph{
	
	Edge[][] graph;
	
	
	/**
	 * @param i value to set as nbnodes
	 * @param n value to set as nestnode
	 * AGraph constructor.
	 */
	public AGraph(int i, int n) {
		super(i, n);
	}

	
	/**
	 * Creates matrix with nbnodes x nbnodes size.
	 */
	public void setGraph() {
		graph=new Edge[nbnodes][nbnodes];
	}
	
	/**
	 * @param w value to set as weight
	 * @param i line position
	 * @param j column position
	 * Creates edge with weight w in graph's i,j position.
	 */
	public void setElem (int w, int i, int j) {
		graph[i][j] = new Edge(w);
	}
	
	/**
	 * @param i line position
	 * @param j column position
	 * @return Edge's weight in graph's i,j position
	 */
	public int getWeight(int i, int j) {
		return graph[i][j].getWeight();
	}
	
	/**
	 * @param i line position
	 * @param j column position
	 * @return Edge's pheromone level in graph's i,j position
	 */
	public double getPhero(int i, int j) {
		return graph[i][j].getPhero();
	}
	
	/**
	 * @param p value to set as pheromone level
	 * @param i line position
	 * @param j column position
	 * Sets Edge's pheromone level in graph's i,j position
	 */
	public void setPhero(double p, int i, int j) {
		graph[i][j].setPhero(p);
	}
	
	/**
	 * @param inc value to increment in pheromone level
	 * @param i line position
	 * @param j column position
	 * Increment Edge's pheromone level in graph's i,j position
	 */
	public void incPhero(double inc, int i, int j) {
		graph[i][j].phero+=inc;
	}
	
	/**
	 * @param i line position
	 * @param j column position
	 * @return Edge in graph's i,j position
	 */
	public Edge getEdge(int i, int j) {
		return graph[i][j];
	}
	
	/**
	 * @return the sum of all the Edge's weights
	 */
	public int getW() {
		int total=0;
		
		for(int i=0; i<nbnodes; i++) {
			for(int j=0; j<=i; j++) {
				if (getEdge(i, j)!=null)
					total+=getWeight(i, j);
			}
		}
		return total;
	}

	/**
	 * @param p path determined by an ant
	 * @param plevel pheromone level
	 * Calculates how much pheromone level is going to be incremented.
	 * Increments pheromone level by calculated amount.
	 */
	public void incPhero(Path p, double plevel) {
		double inc=0;
		
		inc=(plevel*getW())/p.getTotalWeight();
		
		incPhero(inc, nestnode-1, p.getNode(0));
		incPhero(inc, p.getNode(0), nestnode-1);
		
		for(int i=0; i<nbnodes-1; i++) {
			incPhero(inc, p.getNode(i), p.getNode(i+1));
			incPhero(inc, p.getNode(i+1), p.getNode(i));
		}
	}
}
