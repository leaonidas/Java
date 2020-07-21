package simulation;

/**
 * @author Group 20
 * Sets graph with the XML file parameters, nbnodes and nestnode
 *
 */
public class Graph {
	
	int nbnodes;
	int nestnode;
	
	/**
	 * @param n value to set as nbnodes
	 * @param nest value to set as nestnode
	 * Graph constructor.
	 * Initializes graph with nbnodes nodes and defines the initial node as nestnode.
	 */
	public Graph(int n, int nest) {
		nbnodes=n;
		nestnode=nest;
	}
	
	/**
	 * @param i alue to set as nestnode
	 */
	public void setNbnodes(int i) {
		nbnodes=i;
	}
	
	/**
	 * @param i value to set as nestnode
	 */
	public void setNest(int i) {
		nestnode=i;
	}
	
	
	/**
	 * @return nbnodes value
	 */
	public int getNbnodes() {
		return nbnodes;
	}
	
	/**
	 * @return nestnode value
	 */
	public int getNest() {
		return nestnode;
	}
}
