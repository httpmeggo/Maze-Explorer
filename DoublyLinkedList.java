/**
 * This class creates and stores generic values
 * in a doubly linked list, and allows for operations
 * to be made on the doubly linked list that transforms
 * the doubly linked list.
 * 
 * @author Megan Nicius
 * @version 04/04/2021
 */

package project3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

	/**
	 * This node class allows for the creation of nodes.
	 * @author Megan Nicius
	 * @param <E> generic data type that determines what data can be allowed
	 * in the node.
	 */
	class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;
		
		Node(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

public class DoublyLinkedList<E> implements Iterable<E>{
	
	Node<E> head = null;
	Node<E> tail = null;
	int size = 0;
	
	public DoublyLinkedList(){

	}
	
	
	/**
	 * This method retrieves the head node.
	 * @return head node.
	 */
	public Node<E> getHead() {
		return head;
	}
	
	/**
	 * This method sets a specified node as the head node.
	 * @param head
	 */
	public void setHead(Node<E> head) {
		this.head = head;
	}
	
	/**
	 * This method retrieves the tail node.
	 * @return
	 */
	public Node<E> getTail() {
		return tail;
	}
	
	/**
	 * This method sets a specified node as the tail node.
	 * @param tail
	 */
	public void setTail(Node<E> tail) {
		this.tail = tail;
	}
	
	/**
	 * This method retrieves the size of the doubly linked list.
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * This method sets a specified size as the size of the 
	 * doubly linked list.
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
		
	/**
	 * Appends a new node to the end of doubly linked list.
	 * Stores specific data value in newly-added node.
	 * @param e element that will be appended to the end of the list.
	 * @return true if the list is changed due to the call, 
	 * false if accepted parameter is null, false, otherwise.
	 * @throws ClassCastException if the class of the specified
	 * element prevents it from being added to the doubly linked
	 * list.
	 */
	public boolean add (E e) throws ClassCastException {
		//check the element is not null
		if(e != null) {
			//create new node to store element and reassign pointers
			Node nodeForEnd = new Node(e);
			//pointer reassignment for if list is empty
			if(isEmpty()) {
				head = tail = nodeForEnd;
				head.prev = null;
				tail.next = null;
				size++;
				return true;
			}
			//pointer reassignment for if list is not empty
			else {
				tail.next = nodeForEnd;
				nodeForEnd.prev = tail;
				tail = nodeForEnd;
				tail.next = null;
				size--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Stores specified data in a node at a specific position in the
	 * doubly linked list. Checks to make sure the specified position is
	 * valid, meaning that it is both greater than or equal to 0 and less
	 * than or equal to the size of the doubly linked list.
	 * @author Joanna Klukowska
	 * @param e element that will be added to the list.
	 * @param pos the position at which the element will be added to the list
	 * providing that the element and position are valid.
	 * @return true if the list is changed as a result of the method being
	 * called, false, otherwise.
	 * @throws ClassCastException if the specified element's class prevents
	 * it from being added to the list.
	 * @throws IndexOutOfBoundsException if the specified position, pos, is
	 * less than 0 or greater than the size of the list.
	 */
	public boolean add (E e, int pos) throws ClassCastException, IndexOutOfBoundsException{
		//validate position
		if (pos < 0 || pos > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> n = new Node<E>(e);
		
		//set head and tail to new node
		if(size == 0) {
			head = tail = n;
		}
		//if position is 0 then set new node's next pointer to head and head
		//to the new node
		else if (pos == 0) {
			n.next = head;
			head = n;
		}
		//if position is at the end then set tail's next pointer to new node
		//set tail to tail's next pointer
		else if (pos == size()) {
			tail.next = n;
			tail = tail.next;
		}
		else {
			//otherwise find position in list and add element
			Node<E> current = head;
			int count = 0;
			while (count < pos) {
				current = current.next;
				count++;
			}
			n.next = current.next;
			current.next = n;
		}
		//increase size by one and return true
		size++;
		return true;
	}
	
	/**
	 * This method removes all of the elements from the list
	 * and sets the size of the list equal to zero.
	 */
	public void clear() {
		//set head and tail to null and size to 0
		head = tail = null;
		size = 0;
		
	}
	
	/**
	 * This method checks to see if the created doubly linked
	 * list contains a specific object as a stored data value.
	 * @param o the element whose presence the method will search
	 * the list for.
	 * @return true if the object is contained in the data of
	 * the doubly linked list, false, otherwise.
	 */
	public boolean contains(Object o) {
		//create object to hold data of elements in list
		Object e;
		//create temp node at start of list
		Node temp = head;
		//if list is empty return false
		if(isEmpty())
			return false;
		//otherwise return true if element is found when searching through list
		while(temp != null) {
			e = temp.data;
			if(Objects.equals(o, e)) {
				return true; 
			}
			else{
				temp = temp.next;
			}
		}
		return false;
	}
	
	
	/**
	 * This method compares the object specified with the
	 * the list for equality.
	 * @param o the object that will be compared with the doubly linked
	 * list for equality
	 * @return true if and only if the object specified
	 * is also an instance of a DoublyLinkedList, 
	 * all corresponding pairs of elements in both lists
	 * are equal, and both lists have the same size, false, 
	 * otherwise.
	 */
	public boolean equals (Object o) {
		int i = 0;
		//check if object meets equals criteria
		if(o instanceof DoublyLinkedList<?>) {
			if(((DoublyLinkedList) o).size() == size()) {
				Node temp = head;
				while(i < size()) {
					if(((DoublyLinkedList) o).get(i) == get(i)) {
						i++;
					}
					else
						return false;
				}
				//indicates that all elements in list matched so return true
				if(i == size() + 1)
					return true;
			}
			return false;
		}
		return false;
	}
	
	
	/**
	 * This method finds an element at a specific position
	 * and stores that element. It also checks to verify if
	 * specified position is a valid position in the list,
	 * meaning that the position exists in the list and is
	 * not greater than or less than the size of the list.
	 * @param pos index of the element that will be returned.
	 * @return element at specified position if it exists, 
	 * null, otherwise.
	 */
	public E get(int pos) {
		//if position is 0 but list isn't empty return false
		if(!isEmpty() && pos == 0){
			return head.data;
		}
		//if position is last index and list is not empty
		else if (!isEmpty() && pos == size() - 1) {
			return tail.data;
		}
		//if position is greater than 0 and less than size - 1
		else if(pos < size() - 1 && pos > 0) {
			Node<E> currentNode = head;
			for(int counter = 0; counter < pos; counter++) {
				currentNode = currentNode.next;
			}
			return currentNode.data;
		}
		return null;
	}
	
	/**
	 * This method checks to see if there are any elements in the list
	 * by checking if the value of the head node is equal to null.
	 * @return true if the list is empty, false, otherwise.
	 */
	public boolean isEmpty() {
		//if head is null then list is empty
		return (head == null);
	}
	
	/**
	 * This method searches the doubly linked list to see if the specified
	 * element is contained in the list, and then stores the index of the
	 * element in the list.
	 * @param o element to search for the index of.
	 * @return the first index of the specified element in the list, or -1
	 * if the element is not contained in the list.
	 */
	public int indexOf(Object o) {
		//check if element is contained in list
		if(contains(o)) {
			//if so, create an object and retrieve each element until a matching one
			//is found. return matching element
			for(int i = 0; i < size(); i++) {
				Object retrievedData = get(i);
				if(o.equals(retrievedData)) {
					return i;
				}
			}
		}
		return -1;
		
	}
	
	/**
	 * This method removes the first occurrence of the specified element
	 * from the doubly linked list if and only if the element is contained
	 * in the list.
	 * @param o element that will be removed from the list if it exists in
	 * the list.
	 * @return true if the element was contained in the list and has been
	 * removed, false, otherwise.
	 * @throws ClassCastException if the specified element's type is not
	 * compatible with the doubly linked list.
	 * @throws NullPointerException if the specified element is null.
	 */
	public boolean remove(Object o) throws ClassCastException, 
	NullPointerException{
		//if element is not contained then return false
		if(contains(o) == false)
			return false;
		//otherwise, find element and remove it and then return true
		else {
			size = size();
			int pos = indexOf(o);
			remove(pos);
			return true;
		}
	}

	/**
	 * This method removes the element at the specified position as long
	 * as the specified position is valid.
	 * @author Joanna Klukowska
	 * @param pos the index of the element that the method will remove
	 * @return the element that was removed from the list.
	 * @throws IndexOutOfBoundsException if pos is less than zero or greater
	 * than or equal to the size of the list.
	 */
	public E remove(int pos) throws IndexOutOfBoundsException {
		//throw exception if position is out of bounds
		if (pos < 0 || pos > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		//if position is at the head, remove head and reassign pointers
		if(pos == 0) {
			E tmp = head.data;
			head = head.next;
			//set tail to null if size of list is 1
			if(size == 1) {
				tail = null;
			}
			//decrease size and return removed element
			size--;
			return tmp;
		}
		//otherwise remove head and reassign pointers
		Node<E> current = head;
		int stopPos = pos-1;
		int count = 0;
		
		while (count < stopPos) {
			current = current.next;
			count++;
		}
		E tmp = current.next.data;
		current.next = current.next.next;
		
		if(pos == size - 1) {
			tail = current;
		}
		
		size--;
		return tmp;
	}
	
	/**
	 * This method conveys the number of elements in the doubly linked list.
	 * @return an integer that is equivalent to the number of elements in
	 * the doubly linked list.
	 */
	public int size() {
		//if head is null then size is 0
		if(isEmpty())
			size = 0;
		//otherwise count each element in list and store size
		else {
			Node temp = head;
			int counter = 0;
			while(temp != null) {
				counter++;
				temp = temp.next;
			}
			size = counter;
		}
		return size;
	}
	
	/**
	 * This method creates a string representation of the elements in the
	 * doubly linked list, with each element enclosed in square brackets
	 * ("[]") and separated by a comma and a space (", "). The elements are
	 * converted to strings by means of the method String.valueOf(Object) 
	 * from the String class.
	 * @return the string representation of the doubly linked list.
	 */
	public String toString() {
		//if list is empty then return empty string
		if(isEmpty())
			return "";
		String returnString = "";
		Node<E> currentNode = head;
		int i = 0;
		//loop to print nodes in a string
		while(i < size() - 1) {
			returnString += "[" + String.valueOf(currentNode.data) + "], ";
			currentNode = currentNode.next;
			i++;
		}
		//separate statement to print last node so comma doesn't appear 
		returnString += "[" + String.valueOf(currentNode.data) + "]";
	
		return returnString;
	}
	
	/**
	 * This private class contains two public methods, hasNext()
	 * and next() that allow the program to iterate through
	 * the doubly linked list. 
	 * @author Joanna Klukowska
	 */
	private class Itr implements Iterator<E>{
		private Node<E> currentNode = head;
		
		/**
		 * This method checks if the current node points to 
		 * another node by checking to see whether the current
		 * node is equal to null.
		 * @author Joanna Klukowska
		 * @return true if the current node is not equal to null,
		 * false, otherwise.
		 */
		public boolean hasNext() {
			return (currentNode != null);
		}
		
		/**
		 * This method creates a new variable and stores the data of the
		 * current node into the variable, and then forwards the current
		 * node to the following node.
		 * @author Joanna Klukowska
		 * @return the data of the current node that was stored in the 
		 * newly created variable.
		 */
		public E next() {
			E currentData = currentNode.data;
			currentNode = currentNode.next;
			return currentData;
		}
		
	}
	
	/**
	 * This method overrides the iterator method from the Iterator class.
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Itr();
	}
}
