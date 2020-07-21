package simulation;

import simulation.Path;

/**
 * @author Group 20
 * Class ants has a path vector of all ants, and stores the best path
 *
 */
public class Ants {
	Path[] ants;
	Path bestPath;
	
	/**
	 * @param colsize value to define the size of Path[] refering to the number of ants
	 * @param nbnodes value to define each ants' number of nodes
	 * @param nestnode value to define initial node
	 */
	public Ants(int colsize, int nbnodes, int nestnode) {
		ants = new Path[colsize];
		for(int i=0; i<colsize; i++) {
			ants[i]= new Path(nbnodes, nestnode);
		}
		bestPath = new Path(nbnodes, nestnode);
	}
	
	/**
	 * @param i value to identify position
	 * @return path of ant i
	 */
	public Path getPath(int i) {
		return ants[i];
	}
	
	/**
	 * @param preBest path to compare with best path
	 * If preBest weight is smaller than bestPath's then preBest is set as bestPath
	 */
	public void setBest(Path preBest) {
		
		if(bestPath.getTotalWeight()==0 || preBest.comparePaths(bestPath)==true) {
			bestPath = preBest;
		}
	}
	
}
