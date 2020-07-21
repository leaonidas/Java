package pec;

import simulation.Event;

/**
 * @author Group 20
 * Creates interface for the PEC method
 *
 */
public interface IPEC {
	
	/**
	 * Defines priority queue initial capacity
	 */
	public static int INITCAP=10000;
	
	/**
	 * @param event
	 * Adds a new event to the queue
	 */
	public void addEvent(Event event);
	
	/**
	 * @return event in queue with lowest time
	 */
	public Event nextEvent();

}
