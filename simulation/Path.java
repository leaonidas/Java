package simulation;

import java.util.Arrays;

/**
 * @author Group 20
 * Class Path helps store and preform operations on ant's current path
 */
public class Path {
	
	int[] nodes;
	int[] weight;
	int currentpos;
	
	JGraph j;
	
	/**
	 * @param nbnodes number of nodes
	 * @param nestnode initial node
	 * Path constructor
	 * Initializes graph j, with adjacent nodes
	 */
	public Path(int nbnodes, int nestnode) {
		nodes = new int[nbnodes];
		weight = new int[nbnodes];
		j=new JGraph(nbnodes, nestnode);
	}
	
	/**
	 * @param i line position
	 * @param k colun position
	 * @return element in graph's i,k position
	 */
	public int getGraphElem(int i, int k) {
		return j.getElem(i, k);
	}
	
	/**
	 * @return j graph
	 */
	public JGraph getJ() {
		return j;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Path [nodes=" + Arrays.toString(nodes) + "]";
	}

	/**
	 * @param n node's value
	 * @param w weight corresponding to node n
	 * Sets node in path
	 */
	public void setNode(int n, int w) {
		nodes[currentpos] = n;
		weight[currentpos] = w;
		currentpos++;
	}
	
	/**
	 * Deletes node in position i
	 */
	public void deleteNode() {
		currentpos--;
		nodes[currentpos]=0;
		weight[currentpos]=0;	
	}
	
	/**
	 * @param g graph
	 * Deletes all nodes
	 */
	public void clearNodes(AGraph g) {
		while(currentpos!=0) {
			deleteNode();
		}
		j.setJ(g);
	}
	
	/**
	 * @param node
	 * @return true if node is already in path
	 * Checks if node is already in path
	 */
	public boolean verifyNode (int node) {
		for(int i=0; i<currentpos+1;i++) {
			if(nodes[i]==node)
				return false;
		}
		return true;
	}
	
	/**
	 * @param i 
	 * @return node in position i
	 */
	public int getNode(int i) {
		return nodes[i];
	}
	
	/**
	 * @return node where ant currently is
	 */
	public int getCurrentNode() {
		return nodes[currentpos-1];
	}
	
	/**
	 * @return current position in path vector
	 */
	public int getCurrentPos() {
		return currentpos;
	}
	
	/**
	 * @return true if path reached final node
	 */
	public boolean lastNode () {
		if (currentpos==nodes.length-1)
			return true;
		else
			return false;
	}
	
	/**
	 * @return true if the path is complete
	 */
	public boolean completePath () {
		if (currentpos==nodes.length)
			return true;
		else
			return false;
	}
	
	/**
	 * @return path's total weight
	 */
	public int getTotalWeight() {
		int total=0;
		for(int i=0; i<weight.length; i++) {
			total+=weight[i];
		}
		return total;
	}
	
	/**
	 * @param p path
	 * @return true if path's total weight is smaller than p's weight
	 */
	public boolean comparePaths(Path p) {
		if (this.getTotalWeight()<p.getTotalWeight())
			return true;
		else
			return false;
	}
	
	/**
	 * @param node
	 * @return number of non visited adjacent nodes in j matrix
	 */
	public int countAdj(int node) {
		int n=0;
		for(int l=0; l<j.nbnodes; l++) {
			if (j.getElem(node, l)==1 && verifyNode(l)==true && l!=j.nestnode-1)
				n++;
		}
		
		return n;
	}
	
	
	/**
	 * @param a graph
	 * @param node
	 * @return number of non visited adjacent nodes in "a" matrix
	 */
	public int countAdj(AGraph a, int node) {
		int n=0;
		for(int l=0; l<j.nbnodes; l++) {
			if (a.getEdge(node, l)!=null && l!=a.nestnode-1)
				n++;
		}
		
		return n;
	}
	
	/**
	 * @param a graph
	 * @param node
	 * @return number of adjacent nodes in "a" matrix
	 */
	public int countAdjBack(AGraph a, int node) {
		int n=0;
		for(int l=0; l<j.nbnodes; l++) {
			if (a.getEdge(node, l)!=null)
				n++;
		}
		
		return n;
	}
}