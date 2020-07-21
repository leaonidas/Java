package simulation;

import java.util.Comparator;
import java.util.Random;

import pec.PEC;

/**
 * @author Group 20
 * 
 *
 */
public abstract class Event {
	
	double eta;
	
	double rho;
		
	protected final double time;
	
	protected final PEC pec;
	
	static Random random=new Random();
	
	AGraph graph;
	
	Path path;
	
		
	/**
	 * @param t value to set as event's time
	 * @param p refers to PEC where event is
	 * @param a refers to graph being analyzed
	 * @param pa path with current values
	 * @param e value to be set as eta
	 * @param r value to be set as rho
	 * Event constructor
	 */
	public Event(double t, PEC p, AGraph a, Path pa, double e, double r) {
		time=t;
		pec=p;
		graph=a;
		path=pa;
		eta=e;
		rho=r;
	}
	
	/**
	 * @return event's time
	 */
	public double getTime() {
		return time;
	}
	
	
	/**
	 * @author Group 20
	 * Compares two event's times
	 */
	public static class CompareEvents implements Comparator<Event>{

		@Override
		public int compare(Event e1, Event e2) {
			if(e1.getTime()>e2.getTime()) {
				return 1;
			}
			else if (e1.getTime()<e2.getTime()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
	/**
	 * @param m distribution's mean value
	 * @return value to be added to current time, to set has next event's time
	 */
	public double getTime(double m) {
		double next=random.nextDouble();
		return -m*Math.log(1.0-next);
	}
	
	/**
	 * @return
	 * Simulates Move, Evaporation and Print events
	 */
	public abstract int simulateEvent();


	/**
	 * @param pa
	 * @return
	 */
	public String toString(Path pa) {
		return null;
	}
		
}