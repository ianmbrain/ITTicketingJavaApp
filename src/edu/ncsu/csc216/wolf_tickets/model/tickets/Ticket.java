package edu.ncsu.csc216.wolf_tickets.model.tickets;

import java.util.Scanner;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * Ticket class is the main building block of the wolf tickets system.
 * Tickets hold information on its name, description, and active status.
 * Class also contains a list of the categories that the ticket belongs to.
 * Ticekts both contain categories and are stored within category objects.
 * 
 * @author ianbrain
 *
 */
public class Ticket {
	/** Name of the ticket */
	private String ticketName;
	/** Description of the ticket problem */
	private String ticketDescription;
	/** Boolean storing whether the ticket is active */
	private boolean active;
	/** Swap list containing abstract categories the ticket belongs to */
	private ISwapList<AbstractCategory> categories;
	
	/**
	 * Constructs a ticket object taking as parameters the ticket name, description, and active status.
	 * @param ticketName name of ticket
	 * @param ticketDetails description of problem the ticket is resolving
	 * @param active boolean of whether the ticket is active
	 * @throws IllegalArgumentException if the name is null or empty
	 * @throws IllegalArgumentException if the description is null or empty
	 */
	public Ticket(String ticketName, String ticketDetails, boolean active) {
		setTicketName(ticketName);
		setTicketDescription(ticketDetails);
		setActive(active);
		this.categories = new SwapList<AbstractCategory>();
	}
	
	/**
	 * Returns the name of the ticket.
	 * @return ticket name
	 */
	public String getTicketName() {
		return this.ticketName;
	}
	
	/**
	 * Sets the ticket name. Throws exception if the name is null or empty.
	 * @param ticketName name of ticket to set
	 * @throws IllegalArgumentException if the name is null or empty
	 */
	public void setTicketName(String ticketName) {
		if(ticketName == null || ticketName.isEmpty()) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		
		this.ticketName = ticketName;
	}
	
	/**
	 * Returns the ticket description.
	 * @return ticket description
	 */
	public String getTicketDescription() {
		return this.ticketDescription;
	}
	
	/**
	 * Sets the ticket description. Throws exception if the description is null or empty.
	 * @param ticketDescription description of the ticket to set
	 * @throws IllegalArgumentException if the description is null or empty
	 */
	public void setTicketDescription(String ticketDescription) {
		if(ticketDescription == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		
		this.ticketDescription = ticketDescription;
	}
	
	/**
	 * Return boolean of whether the ticket is active or not.
	 * @return boolean active status of the ticket
	 */
	public boolean isActive() {
		return this.active;
	}
	
	/**
	 * Sets the active status of the ticket.
	 * @param active boolean of active status
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Returns the first category in the category collection.
	 * @return the first abstract category
	 */
	public String getCategoryName() {
		if(this.categories == null || this.categories.size() == 0) {
			return "";
		}
		
		return this.categories.get(0).getCategoryName();
	}
	
	/**
	 * Adds the category to the ticket at the end of the list. Do nothing if the ticket is already registered to the ticket.
	 * @param category abstract category to add to the ticket
	 * @throws IllegalArgumentException if the category is null
	 */
	public void addCategory(AbstractCategory category) {
		if(category == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		
		boolean notContains = true;
		for(int i = 0; i < categories.size(); i++ ) {
			if(categories.get(i) == category) {
				notContains = false;
			}
		}
		
		if(notContains) {
			this.categories.add(category);
		}
	}
	
	/**
	 * Completes the tickets and notifies the categories it is complete.
	 */
	public void completeTicket() {
		for(int i = 0; i < this.categories.size(); i++) {
			this.categories.get(i).completeTicket(this);
		}
	}
	
	/**
	 * Return a string representation of the ticket with *, ticket title, active status, and the description on a new line.
	 * @return string representation of ticket in the form: * title,active status,/n description
	 */
	public String toString() {
//		return this.ticketName + "," + this.ticketDescription + "," + this.active;
				
		String toReturn = "";
		toReturn += this.getTicketName();
		if(this.isActive()) {
			toReturn += ",active";
		}
		
		Scanner lineReader = new Scanner(this.ticketDescription);
		lineReader.useDelimiter("\n");
		while(lineReader.hasNext()) {
			toReturn = toReturn + "\n" + lineReader.next();
		}
		
		lineReader.close();
		return toReturn;
		
//		return "* " + this.ticketName + "," + this.active + "\n"
//				+ "
	}
	
}























