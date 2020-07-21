package simulation;

/**
 * @author Group 20
 * Class JGraph refers to the neighbours of each node, without considering weight.
 * If node in column is neighbour mj[i][j]=1
 */
public class JGraph extends Graph {
	
	/**
	 * Neighbour matrix
	 */
	private int mj[][];
	
	/**
	 * @param i matrix size
	 * @param n nest node
	 * JGraph constructor
	 */
	public JGraph(int i, int n) {
		super(i, n);
		mj=new int[i][i];
	}
	
	/**
	 * @param elem value to set in matrix i,j
	 * @param i line position
	 * @param j column position
	 */
	public void setElem(int elem, int i, int j) {
		mj[i][j]=elem;
	}
	
	/**
	 * @param i line position
	 * @param j column position
	 * @return value in i,j position
	 */
	public int getElem(int i, int j) {
		return mj[i][j];
	}
	
	/**
	 * @param g graph with weight and pheromone level
	 * Sets J matrix with corresponding values
	 */
	public void setJ(AGraph g) {
		for(int l=0; l<nbnodes; l++) {
			setJi(g, l);
		}
	}
	
	/**
	 * @param i line position
	 * @param k column position
	 * Sets J matrix to 0
	 */
	public void setJij(int i, int k) {
		setElem(0, i, k);;
	}
	
	/**
	 * @param i line position
	 * @param k column position
	 * @return value in i,k position
	 */
	public int getJij(int i, int k) {
		return getElem(i, k);
	}
	
	
	/**
	 * @param g graph with weight and pheromone level
	 * @param i line position
	 * Checks value in g Graph if value is different from null sets value in
	 * matrix J to 1
	 */
	public void setJi(AGraph g, int i){
		for(int k=0; k<nbnodes; k++) {
			if(g.getEdge(i, k)==null)
				setElem(0, i, k);
			else
				setElem(1, i, k);
		}
	}
}
