package edu.ncsu.csc216.wolf_tickets.model.tickets;

/**
 * Class that represents the active tickets within the wolf tickets system.
 * The name of the active tickets category can only be Active Tickets.
 * Provides functionality to add tickets, return the tickets, and clear the ticket list.
 * 
 * @author ianbrain
 *
 */
public class ActiveTicketList extends AbstractCategory {
	/** Name of the active ticket list */
	public static final String ACTIVE_TASKS_NAME = "Active Tickets";
	
	/**
	 * Constructs an active ticket list with the name constant and no tickets.
	 */
	public ActiveTicketList() {
		super(ACTIVE_TASKS_NAME, 0);
	}
	
	/**
	 * Adds a ticket to the list. If the ticket is not active an exception is thrown.
	 * @param t ticket to add to the ticket list
	 * @throws IllegalArgumentException if the ticket is not active.
	 */
	public void addTicket(Ticket t) {
		if(!t.isActive()) {
			throw new IllegalArgumentException("Cannot add ticket to Active Tickets.");
		}
		
		this.getTickets().add(t);
		t.addCategory(this);
	}
	
	/**
	 * Sets the category name. The name of the category must be Active Tickets or an exception is thrown.
	 * @param categoryName name to set which must match Active Tickets.
	 * @throws IllegalArgumentException if the parameter is not equal to Active Tickets.
	 */
	public void setCategoryName(String categoryName) {
		if(!("Active Tickets".equals(categoryName))) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		
		super.setCategoryName(categoryName);
	}
	
	/**
	 * Returns a 2d array of tickets containing the category and ticket name.
	 * @return 2d string array of ticket information: category, ticket name
	 */
	public String[][] getTicketsAsArray() {
		String[][] returnList = new String[this.getTickets().size()][2];
		
		for(int i = 0; i < this.getTickets().size(); i++) {
			returnList[i][0] = this.getTickets().get(i).getCategoryName(); //this.getCategoryName(); //this.getTickets().get(i).getCategoryName();
			returnList[i][1] = this.getTickets().get(i).getTicketName();
		}
		
		return returnList;
	}
	
	/**
	 * Clears the active ticket list of all tickets.
	 */
	public void clearTickets() {
		for(int i = this.getTickets().size() - 1; i >= 0; i--) {
			//this.getTickets().get(i).setActive(false);
			//this.getTickets().remove(i);
			this.removeTicket(i);
			
		}
	}
}
