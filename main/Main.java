package main;

import org.w3c.dom.Document;

import simulation.Simulator;
import utils.Utils;


/**
 * @author Group 20
 * Contains projects main method
 *
 */
public class Main {

	/**
	 * @param args
	 * Creates document, simulator and document parser.
	 * Runs the simulation.
	 */
	public static void main(String[] args) {
		
		Document doc;
		Simulator sim=new Simulator();
		Utils util= new Utils();
		
		doc=util.openDoc(args[0]);
		util.getInfo(doc, sim);
		
		sim.simulate(sim);

	}
}
