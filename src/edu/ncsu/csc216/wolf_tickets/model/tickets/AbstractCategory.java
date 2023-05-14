package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * Abstract class inherited by the category and active ticket list classes.
 * This class records its category name, how many tickets have been completed, and a list of tickets of the category.
 * Provides functionality to add, remove, and complete tickets in the category.
 * 
 * @author ianbrain
 *
 */
public abstract class AbstractCategory {
	/** Name of the category */
	private String categoryName;
	/** Count of tickets completed in the category */
	private int completedCount;
	/** List of tickets category is associated with */
	private ISwapList<Ticket> tickets;
	
	/**
	 * Constructs a new category object using its name and count of tickets completed.
	 * Throws exception if the name is null or empty or if the count is less than 0.
	 * @param categoryName name of category to set
	 * @param completedCount count of tickets completed in the category
	 * @throws IllegalArgumentException if the name is null or empty or if the count is less than 0
	 */
	public AbstractCategory(String categoryName, int completedCount) {
		this.setCategoryName(categoryName);
		this.tickets = new SwapList<Ticket>();
		if(completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		else {
			this.completedCount = completedCount;
		}
		
	}
	
	/**
	 * Returns the name of the category.
	 * @return name of the category
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	
	/**
	 * Sets the name of the category. Throws exception if the name is null or empty or if the count is less than 0.
	 * @param categoryName name of category to set
	 * @throws IllegalArgumentException if the name is null or empty or if the count is less than 0
	 */
	public void setCategoryName(String categoryName) {
		if(categoryName == null || categoryName.isEmpty()) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		this.categoryName = categoryName;
	}
	
	/**
	 * Returns the list of tickets for the category.
	 * @return list of tickets for the category
	 */
	public ISwapList<Ticket> getTickets() {
		return this.tickets;
	}
	
	/**
	 * Returns the count of completed tickets.
	 * @return count of completed tickets
	 */
	public int getCompletedCount() {
		return this.completedCount;
	}
	
	/**
	 * Adds category to the specified ticket.
	 * @param t ticket to add the category to
	 * @throws NullPointerException if ticket is null
	 */
	public void addTicket(Ticket t) {
		this.tickets.add(t);
		
		t.addCategory(this);
	}
	
	/**
	 * Removes the ticket at the specified index and returns that ticket. Throws exception if the index is not within the size of the list.
	 * @param idx index of the ticket to remove
	 * @return the ticket that was removed
	 * @throws IndexOutOfBoundsException if the index is not within the size of the list
	 */
	public Ticket removeTicket(int idx) {
		return this.tickets.remove(idx);
	}
	
	/**
	 * Retrieves a ticket from the given index and returns it. Throw exception if the index is not within the size of the list.
	 * @param idx index of ticket to retrieve from the category
	 * @return the ticket at the specified index
	 * @throws IndexOutOfBoundsException if the index is not within the size of the list
	 */
	public Ticket getTicket(int idx) {
		return this.tickets.get(idx);
	}
	
	/**
	 * Completes the ticket parameter and increments the count of completed tickets. Uses "==" as we want to remove the same ticket object from the list.
	 * @param t ticket to complete
	 */
	public void completeTicket(Ticket t) {
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i) == t) {
				tickets.remove(i);
				this.completedCount++;
			}
		}
	}
	
	/**
	 * Returns 2d array of the tickets associated with the category.
	 * @return 2d string array of tickets associated with the category
	 */
	public abstract String[][] getTicketsAsArray();
}
