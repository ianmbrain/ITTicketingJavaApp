package edu.ncsu.csc216.wolf_tickets.model.group;

import java.io.File;

import edu.ncsu.csc216.wolf_tickets.model.io.GroupWriter;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/** 
 * Group provides the main functionality to the wolf tickets system. Group has a name and remembers if it has been changed since the last save.
 * System stores a list of categories as well as the active ticket and category.
 * Group provides a variety of functionality such as adding tickets and categories as well as editing these items.
 * 
 * @author ianbrain
 *
 */
public class Group {
	
	/** List of categories in the group */
	private ISortedList<Category> categories;
	
	/** List of active tickets */
	private ActiveTicketList activeTicketList;
	
	/** Current category selected in the GUI */
	private AbstractCategory currentCategory;
	
	/** Name of the group */
	private String groupName;
	
	/** Boolean indicating if the group has been changed since the last save */
	private boolean isChanged;
	
	/**
	 * Constructs group object using the name parameter. Throw exception if the group name is null, empty, or equals Active Tickets.
	 * @param groupName name of group
	 * @throws IllegalArgumentException if the group name is null, empty, or equals Active Tickets
	 */
	public Group(String groupName) {
		this.setGroupName(groupName);
		this.categories = new SortedList<Category>();
		this.activeTicketList = new ActiveTicketList();
		this.currentCategory = this.activeTicketList;
		this.setChanged(true);
	}
	
	/**
	 * Saves group to file specified as parameter. isChanged is updated to false.
	 * @param groupFile file to save the group to
	 */
	public void saveGroup(File groupFile) {
		GroupWriter.writeGroupFile(groupFile, this.groupName, this.categories);
		
		this.isChanged = false;
	}
	
	/**
	 * Returns the name of the group.
	 * @return name of group
	 */
	public String getGroupName() {
		return this.groupName;
	}
	
	/**
	 * Sets the name of the group to the specified name. Throws exception if the name is Active Tickets.
	 * @param groupName name to set the group name to
	 * @throws IllegalArgumentException if the name is equal to Active Tickets
	 */
	private void setGroupName(String groupName) {
		if(groupName == null || groupName.isEmpty() || "Active Tickets".toLowerCase().equals(groupName.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		this.groupName = groupName;
		
	}
	
	/**
	 * Returns whether the group was changed since the last save.
	 * @return false if group has not been changed since the last save. Return true otherwise.
	 */
	public boolean isChanged() {
		return this.isChanged;
	}
	
	/**
	 * Sets the changed parameter if the system was saved or changed since last change.
	 * @param changed true if the group has changed since last change, false if the system is saved.
	 */
	public void setChanged(boolean changed) {
		this.isChanged = true;
	}
	
	/**
	 * Adds a new category to the list of categories.
	 * Throw exception if the category parameters name is Active Tickets or is a duplicate of an existing category.
	 * @param category category to add to the sorted list of categories
	 * @throws IllegalArgumentException if the category parameters name is Active Tickets or is a duplicate of an existing category
	 */
	public  void addCategory(Category category) {
		if("Active Tickets".toLowerCase().equals(category.getCategoryName().toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for(int i = 0; i < this.categories.size(); i++) {
			if(this.categories.get(i).getCategoryName().toLowerCase().equals(category.getCategoryName().toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		this.categories.add(category);
		this.setCurrentCategory(category.getCategoryName());
		this.setChanged(true);
	}
	
	/**
	 * Returns string array of the names of the categories in the sorted list.
	 * @return string array of category names in the group.
	 */
	public String[] getCategoriesNames() {
		String[] returnList = new String[this.categories.size() + 1];
		returnList[0] = this.activeTicketList.getCategoryName();
		
		for(int i = 0; i < this.categories.size(); i++) {
			returnList[i + 1] = this.categories.get(i).getCategoryName();
		}
		
		return returnList;
	}
	
	/**
	 * Helper method used for updating the ticket list field to help with adding tickets to the active ticket list.
	 */
	private void getActiveTicketList() {
		this.activeTicketList.clearTickets();
		
		for(int i = 0; i < this.categories.size(); i++) {
			int catSize = this.categories.get(i).getTickets().size();
			for(int j = 0; j < catSize; j++) {
//				System.out.println("I: " + i + ", J: " + j + ", size: " + catSize);
//				System.out.println(categories.get(i).getCategoryName());
				if(this.categories.get(i).getTickets().get(j).isActive()) {
					this.activeTicketList.addTicket(this.categories.get(i).getTickets().get(j));
				}
			}
		}
	}
	
	/**
	 * Sets the current category the specified category. If the category does not exist set the active tickets to the current category.
	 * @param categoryName name of category to set to the current category.
	 */
	public void setCurrentCategory(String categoryName) {
		if("Active Tickets".equals(categoryName)) {
			this.getActiveTicketList();
			this.currentCategory = this.activeTicketList;
		}
		else {
			boolean set = false;
			for(int i = 0; i < categories.size(); i++) {
				if(categories.get(i).getCategoryName().equals(categoryName)) {
					this.currentCategory = categories.get(i);
					set = true;
					break;
				}
			}
			
			if(!set) {
				this.currentCategory = this.activeTicketList;
			}
		}
	}
	
	/**
	 * Returns the current category of the groups
	 * @return the current category
	 */
	public AbstractCategory getCurrentCategory() {
		return this.currentCategory;
	}
	
	/**
	 * Changes the current categories name to the name specified as the parameter.
	 * Throws exception if the name parameter is Active Tickets or a duplicate of an existing category.
	 * @param categoryName name to set the category name to.
	 * @throws IllegalArgumentException if the current category is an active ticket list, the name parameter is Active Tickets or a duplicate of an existing category
	 */
	public void editCategory(String categoryName) {
		String realName = categoryName.trim();
		
		if(this.currentCategory instanceof ActiveTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		
		if("Active Tickets".toLowerCase().equals(realName.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		// Check if duplicate of another category.
		for(int i = 0; i < categories.size(); i++) {
			if(this.categories.get(i).getCategoryName().toLowerCase().equals(realName.toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		Category temp = null;
		// Remove the current category from the list.
		for(int i = 0; i < categories.size(); i++) {
			if(this.categories.get(i).getCategoryName().toLowerCase().equals(currentCategory.getCategoryName().toLowerCase())) {
				temp = this.categories.remove(i);
			}
		}
		
		// Edit and add back the category.
		temp.setCategoryName(realName);
		this.categories.add(temp);
		this.currentCategory = temp;
		
		this.setChanged(true);
	}
	
	/**
	 * Removes the category and sets the current category to active ticket list.
	 * Throws exception if the current category is an active ticket list.
	 * @throws IllegalArgumentException if the current category is an active ticket list
	 */
	public void removeCategory() {
		if(this.currentCategory instanceof ActiveTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be deleted.");
		}
		else {
			for(int i = 0; i < categories.size(); i++) {
				if(this.categories.get(i).getCategoryName().toLowerCase().equals(currentCategory.getCategoryName().toLowerCase())) {
					this.categories.remove(i);
				}
			}
			
			this.setCurrentCategory("Active Tickets");
			this.setChanged(true);
		}
	}
	
	/**
	 * Adds specified ticket to the current category. If the ticket is active update active ticket list to reflect it.
	 * @param t ticket to add to the current category
	 */
	public void addTicket(Ticket t) {
		if(this.currentCategory instanceof Category) {
			this.currentCategory.addTicket(t);
			
			if(t.isActive()) {
				this.getActiveTicketList();
			}
			
			this.setChanged(true);
		}
	}
	
	/**
	 * Updates the information of the ticket at the specified index. If the ticket is active then active ticket list is also updated
	 * @param idx index of the ticket in the category
	 * @param ticketName name of the ticket
	 * @param ticketDescription description of the ticket
	 * @param active active status of ticket
	 */
	public void editTicket(int idx, String ticketName, String ticketDescription, boolean active) {
		if(this.currentCategory instanceof Category) {
			this.currentCategory.getTicket(idx).setTicketName(ticketName);
			this.currentCategory.getTicket(idx).setTicketDescription(ticketDescription);
			this.currentCategory.getTicket(idx).setActive(active);
			
			if(this.currentCategory.getTicket(idx).isActive()) {
				this.getActiveTicketList();
			}
			
			this.setChanged(true);
		}
	}
	
	
	
	
}
