package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * Implements the ISortedList interface. This class can only take types that implement the comparable interface.
 * Sorted list maintains a list of sorted elements. This is used by the system to store sorted categories.
 * Provides functionality to add, remove, and check if the list contains and elements.
 * Built using a linked list data structure.
 * 
 * @author ianbrain
 *
 * @param <E> type of element to be added to the list
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	/** size of the list. */
	private int size;
	/** First node in the list */
	private ListNode front;
	
	/**
	 * Constructs a new sorted list object. The size of the list starts at zero.
	 */
	public SortedList() {
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Adds an category to the list maintaining the sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is duplicate of existing element
	 */
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if(front == null || element.compareTo(this.front.data) < 0) {
			front = new ListNode(element, front);
			
		}
		
		else {
			ListNode current = this.front;
			
			// Check for duplicate at the first index
			if(element.compareTo(front.data) == 0) {
				throw new IllegalArgumentException("Cannot add duplicate element.");
			}
			
			// Check for duplicates
			while(current.next != null) {
				if(element.compareTo(current.next.data) == 0) {
					throw new IllegalArgumentException("Cannot add duplicate element.");
				}
				current = current.next;
			}
			current = this.front;
			
			while(current.next != null && element.compareTo(current.next.data) > 0) {
				current = current.next;
			}
			
			current.next = new ListNode(element, current.next);
		}
		
		size++;
	}
	
	/**
	 * Throws an exception if the index is out of bounds of the list
	 * @param idx index to check the element at
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	private void checkIndex(int idx) { 
		if(idx < 0 || idx >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Removes the element at the index specified. Throws exception if the index is out of bounds.
	 * @param idx index to check the element at
	 * @return element removed from the list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public E remove(int idx) {
		this.checkIndex(idx);
		
		E removed = null;
		if(idx == 0) {
			removed = this.front.data;
			this.front = this.front.next;
		}
		else {
			ListNode current = front;
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			
			removed = current.next.data;
			current.next = current.next.next;
		}
		
		size--;
		return removed;
		
		
	}
	
	/**
	 * Checks if the element specified is contained within the list. Returns true if it does.
	 * @param element element to check if in the list
	 * @return true if the element is in the list and false otherwise
	 */
	public boolean contains(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if(this.size == 0) {
			return false;
		}
		
		ListNode f = new ListNode(element);
		f = front;
		ListNode current = f;
		if(current.data.equals(element)) {
			return true;
		}
		
		while(current.next != null) {
			if(current.next.data.equals(element)) {
				return true;
			}
			current = current.next;
		}
		
		return false;
	}
	
	/**
	 * Returns an object from the specified index. Throws exception if the index is out of bounds.
	 * @param idx index to get the element from in the list
	 * @return object at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public E get(int idx) {
		this.checkIndex(idx);
		
		ListNode current = this.front;
		
		for(int i = 0; i < idx; i++) {
			current = current.next;
		}
		
		return current.data;
	}
	
	/**
	 * Returns the amount of elements in the sorted list.
	 * @return the amount of elements in the sorted list
	 */
	public int size() {
		return this.size;
	}
	
	private class ListNode {
		/** Data of generic type E that the list node contains */
		public E data;
		
		/** Reference to the next node in the linked list */
		public ListNode next;
		
		/**
		 * Constructs a new list node object by setting its data field and next reference to null.
		 * @param data data of the node
		 */
		public ListNode(E data) {
			this(data, null);
		}
		
		/**
		 * Constructs a new list node object by setting its data field and reference to the next node in the linked list.
		 * @param data data of the node
		 * @param next reference to next node in the linked list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
