package edu.ncsu.csc216.wolf_tickets.model.tickets;

/**
 * Category class is used to create category objects within the wolf tickets system.
 * Extends the abstract category class and has all the same functionality.
 * Provides additional functionality to compare categories for use in adding them to the sorted list.
 * 
 * @author ianbrain
 *
 */
public class Category extends AbstractCategory implements Comparable<Category> {
	
	/**
	 * Constructs a new category object. Throws exception if the name is null or empty or if the count is less than 0
	 * @param categoryName name of the category
	 * @param completedTickets number of tickets completed in the category
	 * @throws IllegalArgumentException if the name is null or empty or if the count is less than 0
	 */
	public Category(String categoryName, int completedTickets) {
		super(categoryName, completedTickets);
	}
	
	/**
	 * Returns a 2d String array containing the index of the ticket in the list and the name of the ticket.
	 * @return 2d String array containing the index of the ticket in the list and the name of the ticket
	 */
	public String[][] getTicketsAsArray() {
		String[][] returnList = new String[this.getTickets().size()][2];
		
		for(int i = 0; i < this.getTickets().size(); i++) {
			returnList[i][0] = String.valueOf(i);
			returnList[i][1] = this.getTickets().get(i).getTicketName();
		}
		
		return returnList;
	}
	
	/**
	 * Returns a negative value, 0 or positive value based on the the catgory's name.
	 * Used to compare categories when adding them to the sorted list
	 * @param otherCategory category to compare the current category to
	 * @return negative value, 0 or positive value based on the the catgory's name
	 */
	public int compareTo(Category otherCategory) {
		return this.getCategoryName().toLowerCase().compareTo(otherCategory.getCategoryName().toLowerCase());
	}

}
