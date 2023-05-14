package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * Group reader is used to read groups from files into the wolf tickets system.
 * Methods are static so that other classes can use them to read in groups.
 * The methods for processing categories and tickets are private as they are only used in this class.
 * @author ianbrain
 *
 */
public class GroupReader {
	
	/**
	 * Reads in a group containing categories and tickets from the specified file.
	 * Throws exception if the file cannot be read from.
	 * @param groupFile file to read the group data from
	 * @return group from the file to be used in the ticket system
	 * @throws IllegalArgumentException if the file cannot be read from.
	 */
	public static Group readGroupFile(File groupFile) {
		Scanner fileReader;
		
		// Throw exception if the file cannot be read it
		try {
			 fileReader = new Scanner(new FileInputStream(groupFile));
		} catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to load file.");
        }
		
		String groupString = "";
		
		// Process the file. Throw exception if the first character is not "!"
		String groupName = fileReader.nextLine();
		while (fileReader.hasNextLine()) { 
	    	groupString = groupString + fileReader.nextLine() + "\n";
	    }
		if(groupName.charAt(0) != '!') {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		Group returnGroup = new Group(groupName.substring(2).trim());
		SwapList<String> catTokens = new SwapList<String>();
		
		// Separates the string into strings beginning with "#"
		Scanner lineReader = new Scanner(groupString);
		lineReader.useDelimiter("\\r?\\n?[#]");
		while(lineReader.hasNext()) {
			catTokens.add(lineReader.next());
		}
		
		for(int i = 0; i < catTokens.size(); i++) {
			try {
				returnGroup.addCategory((Category) processCategory(catTokens.get(i)));
			} catch (Exception e) {
				// Any categories that throw exceptions should not be added to the group.
			}
		}
		
		
		lineReader.close();
		fileReader.close();
		
		returnGroup.setCurrentCategory("Active Tickets");
		returnGroup.setChanged(false);
		return returnGroup;
	}
	
	/**
	 * Processes line of text from the group file into a category to use in ticket system.
	 * @param categoryText line of category text to create category from
	 * @return category from file line
	 */
	private static AbstractCategory processCategory(String categoryText) {
		AbstractCategory returnCat;
		
		Scanner lineReader = new Scanner(categoryText);
		lineReader.useDelimiter("\n");
		
		// Process the first line of the category
		String catString = lineReader.next();
		
		Scanner catReader = new Scanner(catString);
		catReader.useDelimiter(",");
		String catName = catReader.next().trim();
		int completedTickets = Integer.valueOf(catReader.next());
		
		returnCat = new Category(catName, completedTickets);
		catReader.close();
		
		lineReader.useDelimiter("\\r?\\n?[*]");
		while(lineReader.hasNext()) {
			try {
				returnCat.addTicket(processTicket(returnCat, lineReader.next()));
			} catch(Exception e) {
				// If the ticket throws and exception, do not add it and move on to the next ticket.
			}
		}
		
		lineReader.close();
		return returnCat;
	}
	
	/**
	 * Processes a ticket from a information in the group file using category and ticket text to create the ticket.
	 * @param category category to associate the ticket with
	 * @param ticketText text from the file line used to create the ticket
	 * @return ticket object
	 */
	private static Ticket processTicket(AbstractCategory category, String ticketText) {
		Scanner lineReader = new Scanner(ticketText);
		lineReader.useDelimiter("\n");
		
		String firstLine = lineReader.next().trim();
		
		Scanner firstReader = new Scanner(firstLine);
		firstReader.useDelimiter(",");
		String ticketName = firstReader.next();
		
		boolean status = false;
		if(firstReader.hasNext()) {
			status = true;
		}
		firstReader.close();
		
		String description = "";
		while(lineReader.hasNext()) {
			description = description + lineReader.next();
		}
		
		lineReader.close();
		if(ticketName.toLowerCase().equals("active")) {
			ticketName = "";
		}
		return new Ticket(ticketName, description, status);
		
		
		
		
	}
}
