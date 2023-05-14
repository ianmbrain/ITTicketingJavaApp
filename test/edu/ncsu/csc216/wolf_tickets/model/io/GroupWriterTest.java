package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * Test class for the group writer class in the wolf tickets system.
 * 
 * @author ianbrain
 *
 */
class GroupWriterTest {
	/** Ticket used for testing. */
	private Ticket t1 = new Ticket("t1", "description1", false);
	/** Ticket used for testing. */
	private Ticket t2 = new Ticket("t2", "description2", false);
	/** Ticket used for testing. */
	private Ticket t3 = new Ticket("t3", "description3", true);
	/** Category used for testing. */
	private Category a1 = new Category("a1", 1);
	/** Category used for testing. */
	private Category b1 = new Category("b1", 2);
	/** Category used for testing. */
	private Category c1 = new Category("c1", 3);
	
	/**
	 * Test that the group can be written to the file in the correct format
	 */
	@Test
	void testWriteGroupFile() {
		// Test to reach 100% method coverage.
		assertDoesNotThrow(() -> new GroupWriter());
		
		a1.addTicket(t1);
		a1.addTicket(t2);
		
		b1.addTicket(t1);
		
		c1.addTicket(t3);
		
		ISortedList<Category> list = new SortedList<Category>();
		list.add(a1);
		list.add(b1);
		list.add(c1);
		
		File f = new File("test-files/test_output.txt");
		GroupWriter.writeGroupFile(f, "Test Group", list);
		
		try {
			Scanner fileScan = new Scanner(new File("test-files/test_output.txt"));
			assertEquals("! Test Group", fileScan.nextLine());
			assertEquals("# a1,1", fileScan.nextLine());
			assertEquals("* t1", fileScan.nextLine());
			assertEquals("description1", fileScan.nextLine());
			assertEquals("* t2", fileScan.nextLine());
			assertEquals("description2", fileScan.nextLine());
			assertEquals("# b1,2", fileScan.nextLine());
			assertEquals("* t1", fileScan.nextLine());
			assertEquals("description1", fileScan.nextLine());
		} catch(Exception e) {
			fail();
		}
		
		File f1 = new File("test");
		assertThrows(IllegalArgumentException.class, () -> GroupWriter.writeGroupFile(f1, "Test Group", list));
	}

}
