package dsa_assignment2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * A Drone class to simulate the decisions and information collected by a drone
 * on exploring an underground maze.
 * 
 */
public class Drone implements DroneInterface
{
	private static final Logger logger     = Logger.getLogger(Drone.class);
	
	public String getStudentID()
	{
		//change this return value to return your student id number
		return "1906868";
	}

	public String getStudentName()
	{
		//change this return value to return your name
		return "Woohyeon JO";
	}

	/**
	 * The Maze that the Drone is in
	 */
	private Maze                maze;

	/**
	 * The stack containing the portals to backtrack through when all other
	 * doorways of the current chamber have been explored (see assignment
	 * handout). Note that in Java, the standard collection class for both
	 * Stacks and Queues are Deques
	 */
	private Deque<Portal>       visitStack = new ArrayDeque<>();

	/**
	 * The set of portals that have been explored so far.
	 */
	private Set<Portal>         visited    = new HashSet<>();

	/**
	 * The Queue that contains the sequence of portals that the Drone has
	 * followed from the start
	 */
	private Deque<Portal>       visitQueue = new ArrayDeque<>();

	/**
	 * This constructor should never be used. It is private to make it
	 * uncallable by any other class and has the assert(false) to ensure that if
	 * it is ever called it will throw an exception.
	 */
	@SuppressWarnings("unused")
	private Drone()
	{
		assert (false);
	}

	/**
	 * Create a new Drone object and place it in chamber 0 of the given Maze
	 * 
	 * @param maze
	 *            the maze to put the Drone in.
	 */
	public Drone(Maze maze)
	{
		this.maze = maze;
	}

	/* 
	 * @see dsa_assignment2.DroneInterface#searchStep()
	 */
	@Override
	public Portal searchStep()
	{
		/* WRITE YOUR CODE HERE */
		int currentChamber = maze.getCurrentChamber();
		int numDoors = maze.getNumDoors();
		for(int i=0;i<numDoors;i++) {
			Portal tryPortal1 = new Portal(currentChamber, i);
			if(!visited.contains(tryPortal1)) {
				Portal enterPortal1 = maze.traverse(i);
				visitStack.addLast(enterPortal1);
				visitQueue.addLast(tryPortal1);
				visitQueue.addLast(enterPortal1);
				visited.add(tryPortal1);
				visited.add(enterPortal1);
				return enterPortal1;
			}
		}
		if(visitStack.isEmpty()) {
			return null;
		}else {
			Portal tryPortal2 = visitStack.removeLast();
			Portal enterPortal2 = maze.traverse(tryPortal2.getDoor());
			visitQueue.addLast(tryPortal2);
			visitQueue.addLast(enterPortal2);
			return enterPortal2;
		}
	}
	/* 
	 * @see dsa_assignment2.DroneInterface#getVisitOrder()
	 */
	@Override
	public Portal[] getVisitOrder()
	{
		/* WRITE YOUR CODE HERE */
		return visitQueue.toArray(new Portal[0]);
	}
	/*
	 * @see dsa_assignment2.DroneInterface#findPathBack()
	 */
	@Override
	public Portal[] findPathBack()
	{
		/* WRITE YOUR CODE HERE */
		if(maze.getCurrentChamber() == 0) {
			return new Portal[0];
		}
		ArrayList<Portal> set = new ArrayList<>();
		Iterator<Portal> iterator = visitStack.descendingIterator();
		while(iterator.hasNext()) {
			set.add(iterator.next());
		}
		ArrayList<Portal> toRemove = new ArrayList<>();
		for(int i=0;i<set.size();i++) {
			Portal now = set.get(i);
			ArrayDeque<Portal> save = new ArrayDeque<>();
			save.add(now);
			for(int j=i+1;j<set.size();j++) {
				save.add(set.get(j));
				if(set.get(j).getChamber() == now.getChamber()) {
					save.removeLast();
					toRemove.addAll(save);
					i = j;
					break;
				}
			}
		}
		for (Portal portal : toRemove) {
			set.remove(portal);
		}
		return set.toArray(new Portal[0]);
	}
}
