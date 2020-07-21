package pec;

import java.util.PriorityQueue;

import simulation.Event;

/**
 * @author Group 20
 * Class PEC implements IPEC interface
 *
 */
public class PEC implements IPEC {
	
	private PriorityQueue<Event> events=null;
	
	/**
	 * PEC constructor
	 */
	public PEC() {
		events=new PriorityQueue<Event>(INITCAP, new Event.CompareEvents());
	}
	
	/**
	 * @return 
	 */
	/*public PriorityQueue<Event> getEvents(){
		return events;
	}*/

	/* (non-Javadoc)
	 * @see pec.IPEC#addEvent(simulation.Event)
	 */
	@Override
	public void addEvent(Event event) {
		events.add(event);
	}

	/* (non-Javadoc)
	 * @see pec.IPEC#nextEvent()
	 */
	@Override
	public Event nextEvent() {
		return events.poll();
	}

}
