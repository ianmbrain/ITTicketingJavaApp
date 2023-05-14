package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;

/**
 * Group writer is used to write groups and their associated tickets and categories to files for saving and sharing.
 * The write group file method is static so that other classes can use it to save groups to files.
 * 
 * @author ianbrain
 *
 */
public class GroupWriter {
	
	/**
	 * Writes to the specified file the group name and the list of category tickets specified as a parameter.
	 * @param groupFile file to write in the group to
	 * @param groupName name of the group
	 * @param categories sorted list of categories
	 * @throws IllegalArgumentException if the file cannot be written to
	 */
	public static void writeGroupFile(File groupFile, String groupName, ISortedList<Category> categories) {
		PrintStream fileWriter;
		
		try {
			fileWriter = new PrintStream(groupFile);
		} catch(Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		
		fileWriter.println("! " + groupName);
		
		for(int i = 0; i < categories.size(); i++) {
			fileWriter.println("# " + categories.get(i).getCategoryName() + "," + String.valueOf(categories.get(i).getCompletedCount()));
			
			ISwapList<Ticket> tickets = categories.get(i).getTickets();
			for(int j = 0; j < tickets.size(); j++) {
				
				fileWriter.println("* " + tickets.get(j).toString());
				
			}
		}
		
		fileWriter.close();
		
	}
}
