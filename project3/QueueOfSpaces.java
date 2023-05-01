/**
 * This class is a reference-based queue that uses the
 * doubly linked list created in the DoublyLinkedList.java
 * as the internal storage and implements the interface
 * PossibleLocations.java. 
 * 
 * @author Megan Nicius
 * @version 04/04/2021
 */
package project3;

public class QueueOfSpaces implements PossibleLocations {

	/**
	 * This private node class allows for the creation of nodes.
	 * @author Megan Nicius
	 * @param <E> generic data type that determines what data can be allowed
	 * in the node.
	 */
	private class Node<E>{
		public Location data;
		public Node<E> next;
		public Node<E> prev;
		
		public Node(Location s, Node<E> n, Node<E> p){
			data = s;
			next = n;
			prev = p;	
		}
	}
	private Node front;
	private Node back;
	private int size = 0;
	
	/**
	 * Add a Location object to this queue.
	 * @param s object to be added
	 * @throws NullPointerException if the given location is null
	 */
	@Override
	public void add(Location s) throws NullPointerException {
		//create a new node that is equal the current back of queue
		Node nextBack = back;
		//set the current back equal to a new node
		back = new Node(s, null, nextBack);
		
		//set the front equal to the back if the size of the queue
		//before the addition is 0
		if(size == 0)
			front = back;
		//reassign pointer for the nextBack node and increase size
		else {
			nextBack.next = back;
			size++;
		}
	}

	/**
     * Remove the next object from this queue. The specific
     * item returned is the one at the front location in the queue
     * (least recently added item).
     * @return the next object, or null if set is empty
     */
	@Override
	public Location remove() {
		//create new variable of type Location to store the data of element
		Location storeData = null;
		//return null if there are no elements in the queue
		if(size==0)
			return null;
		//otherwise, remove the element in the queue and decrease size
		else {
			storeData = front.data;
			front = front.prev;
			size--;
			//if the new size is 0 then set back & front to null
			if(size==0)
				back = front = null;
		}
		return storeData;
	}

	/**
     * Determines if this collection is empty or not.
     * @return  true, if set is empty, false, otherwise.
     */
	@Override
	public boolean isEmpty() {
		//if size is 0 then queue is empty
		return (size == 0);
	}
}
