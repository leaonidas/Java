package simulation;

/**
 * @author Group 20
 * Class Edge is used as AGraph's element
 *
 */

public class Edge {
	int weight;
	double phero;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [weight=" + weight + ", phero=" + phero + "]";
	}

	/**
	 * @param w value to be set as Edge's weight
	 */
	public Edge(int w) {
		weight = w;
		phero = 0;
	}
	
	/**
	 * @param w value to be set as Edge's weight
	 */
	public void setWeight(int w) {
		weight=w;
	}
	
	/**
	 * @param p value to be set as Edge's pheromone level
	 */
	public void setPhero(double p) {
		phero = p;
	}
	
	/**
	 * @return Edge's weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * @return Edge's pheromone level
	 */
	public double getPhero() {
		return phero;
	}
}
