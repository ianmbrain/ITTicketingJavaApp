package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * SwapList is an array based list class that stores categories and tickets for use in the wolf tickets system.
 * Provides functionality to add, remove, and move elements in the list.
 * Elements are always added to the back of the list and cannot be added elsewhere.
 * 
 * @author ianbrain
 *
 * @param <E> type of elements to be added to the list
 */
public class SwapList<E> implements ISwapList<E> {
	
	/** capacity the array field is initially set to. */
	private static final int INITIAL_CAPACITY = 10;
	
	/** array used to provide functionality to the list. */
	private E[] list;
	
	/** size of the sorted list. */
	private int size;
	
	/**
	 * Constructs a new swap list. Initially sets the size of the list to zero.
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	/**
	 * Adds an element to the list in sorted order. Throws exception if element specified is null.
	 * @param element element to add to the sorted list
	 * @throws NullPointerException if element specified is null
	 */
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		this.checkCapacity(this.size + 1);
		
		this.list[this.size] = element;
		this.size++;
	}
	
	/**
	 * Checks if the list has the capacity to add more elements. If the size of the list is at capacity then doubles the size of the list
	 * @param size capacity to check
	 */
	private void checkCapacity(int size) {
		if(size >= this.list.length) {
			@SuppressWarnings("unchecked")
			E[] returnArray = (E[]) new Object[list.length * 2];
			
			for(int i = 0; i < this.size; i++) {
				returnArray[i] = this.list[i];
			}
			
			this.list = returnArray;
			
		}
	}
	
	/**
	 * Removes an element at the specified index from the list. Throws exception if the index is out of bounds.
	 * @param idx index of element to remove
	 * @return element removed from the list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public E remove(int idx) {
		this.checkIndex(idx);
		
		E returnE = this.list[idx];
		
		for(int i = idx; i < this.size - 1; i++) {
			this.list[i] = this.list[i + 1];
		}
		this.list[this.size] = null;
		
		this.size--;
		return returnE;
	}
	
	/**
	 * Checks if the index specified is valid in the array. Used to check if an index can be added at.
	 * @param idx index to check in the list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	private void checkIndex(int idx) {
		if(idx < 0 || idx >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		
	}
	
	/**
	 * Moves the element at the specified index up. Throws exception if the index is out of bounds.
	 * @param idx index of element to move in the list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public void moveUp(int idx) {
		this.checkIndex(idx);
		
		if(idx != 0) {
			E temp = this.list[idx];
			this.list[idx] = this.list[idx - 1];
			this.list[idx - 1] = temp;
		}
	}
	
	/**
	 * Moves the element at the specified index down. Throws exception if the index is out of bounds.
	 * @param idx index of element to move in the list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public void moveDown(int idx) {
		this.checkIndex(idx);
		
		if(idx != this.size - 1) {
			E temp = this.list[idx];
			this.list[idx] = this.list[idx + 1];
			this.list[idx + 1] = temp;
		}
	}
	
	/**
	 * Moves the element at the specified index to the front. Throws exception if the index is out of bounds.
	 * @param idx index of element to move in the list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public void moveToFront(int idx) {
		this.checkIndex(idx);
		
		if(idx != 0) {
			E temp = this.list[idx];
			
			for(int i = idx; i > 0; i--) {
				this.list[i] = this.list[i - 1];
			}
			
			this.list[0] = temp;
		}
	}
	
	/**
	 * Moves the element at the specified index to the back. Throws exception if the index is out of bounds.
	 * @param idx index of element to move in the list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public void moveToBack(int idx) {
		this.checkIndex(idx);
		
		if(idx != this.size - 1) {
			E temp = this.list[idx];
			
			for(int i = idx; i < this.size - 1; i++) {
				this.list[i] = this.list[i + 1];
			}
			
			this.list[size - 1] = temp;
		}		
	}
	
	/**
	 * Gets the element at the specified index. Throws exception if the index is out of bounds.
	 * @param idx index of element to move in the list
	 * @return element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public E get(int idx) {
		this.checkIndex(idx);

		return this.list[idx];
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return this.size;
	}

	
}
