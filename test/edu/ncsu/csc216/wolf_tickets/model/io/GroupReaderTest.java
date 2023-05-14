package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;

/**
 * Test class for the group reader class in the wolf tickets system.
 * Test that groups, categories, and tickets are read in correctly.
 * 
 * @author ianbrain
 *
 */
class GroupReaderTest {
	
	/**
	 * Test that the group file can be read in.
	 * Test that the categories and tickets are correct.
	 */
	@Test
	void testReadGroupFile() {
		// Test to reach 100% method coverage.
		assertDoesNotThrow(() -> new GroupReader());
				
		
		File f = new File("test-files/group1.txt");
		Group g = GroupReader.readGroupFile(f);
		
		assertEquals("CSC IT", g.getGroupName());
		
		String[] catArray = {"Active Tickets", "Classroom Tech", "Desktop", "Web"};
		assertEquals(catArray[0], g.getCategoriesNames()[0]);
		assertEquals(catArray[1], g.getCategoriesNames()[1]);
		assertEquals(catArray[2], g.getCategoriesNames()[2]);
		
		g.setCurrentCategory("Desktop");
		assertEquals("Desktop", g.getCurrentCategory().getCategoryName());
		
		assertEquals("Dr. McLeod's computer won't charge.", g.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("Microphone not detected through docking station.", g.getCurrentCategory().getTicket(1).getTicketName());
	}
	
	/**
	 * Test that the file is read in and the invalid tickets are not included.
	 */
	@Test
	void testReadGroupSeven() {
		File f = new File("test-files/group7.txt");
		Group g = GroupReader.readGroupFile(f);
		
		assertEquals("OIT", g.getGroupName());
		
		String[] catArray = {"Active Tickets", "License Renewals"};
		assertEquals(catArray[0], g.getCategoriesNames()[0]);
		assertEquals(catArray[1], g.getCategoriesNames()[1]);
		
		assertEquals("Active Tickets", g.getCurrentCategory().getCategoryName());
		assertTrue(g.getCurrentCategory().getTicket(0).isActive());
		
		
	}

}
