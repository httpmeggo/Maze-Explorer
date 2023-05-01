/**
 * This class is a reference-based stack that uses the
 * doubly linked list created in the DoublyLinkedList.java
 * as the internal storage and implements the interface
 * PossibleLocations.java. 
 * 
 * @author Megan Nicius
 * @version 04/04/2021
 */
package project3;

public class StackOfSpaces implements PossibleLocations{

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
	private Node top;
	private Node bottom;
	private int size = 0;
	
	/**
	 * Add a Location object to this stack.
	 * @param s object to be added
	 * @throws NullPointerException if the given location is null
	 */
	@Override
	public void add(Location s) throws NullPointerException {
		//Create new node and set its prev and next to null and top, respectively
		Node newTop = new Node(s, null, null);
		newTop.prev = null;
		newTop.next = top;
		
		//if the top exists then set its prev to point to the new top node
		if(top != null) {
			top.prev = newTop;
		}
		top = newTop;
		size++; //increase size
		
		//if size is 1 then the bottom and top are the same
		if(size == 1)
			bottom = top; 
		else if(size > 1)
			bottom = newTop.next; //otherwise bottom is the new top's next pointer
		
		
	}

	/**
     * Remove the next object from this stack. The specific
     * item returned is the one at the top location in the stack (most
     * recently added item).
     * @return the next object, or null if set is empty
     */
	@Override
	public Location remove() {
		//create location variable and set equal to null
		Location storeData = null;
		//if size is 0 then return null
		if(size == 0) {
			return null;
		}
		//if size is 1 then store data from top and set top & bottom to null
		else if(size == 1) {
			storeData = top.data;
			top = bottom = null;
			return storeData;
		}
		//if size is greater than one, store top's data and reassign pointers
		else if(size > 1) {
			Node newTop = top.next;
			storeData = top.data;
			top = newTop;
			return storeData;
		}
		return storeData;
	}

	/**
     * Determines if this collection is empty or not.
     * @return  true, if set is empty, false, otherwise.
     */
	@Override
	public boolean isEmpty() {
		//if size is 0 then stack is empty
		return (size == 0);
	}
}
