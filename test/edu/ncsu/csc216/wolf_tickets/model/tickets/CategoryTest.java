package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the category class in the wolf tickets system.
 * Test that the ticket array works as expected and that the compare to method compares categories correctly.
 * 
 * @author ianbrain
 *
 */
class CategoryTest {
	/** Ticket used for testing. */
	private Ticket t1 = new Ticket("t1", "description1", false);
	/** Ticket used for testing. */
	private Ticket t2 = new Ticket("t2", "description2", false);
	/** Ticket used for testing. */
	private Ticket t3 = new Ticket("t3", "description3", true);
	/** Category used for testing. */
	private Category c1 = new Category("a1", 1);
	/** Category used for testing. */
	private Category c2 = new Category("b2", 2);
	/** Category used for testing. */
	private Category c3 = new Category("c3", 3);
	
	/**
	 * Test that the array contains the index of the ticket in the first column and the name of the ticket in the second column.
	 */
	@Test
	void testGetTicketsAsArray() {
		c1.addTicket(t1);
		c1.addTicket(t2);
		c1.addTicket(t3);
		
		assertEquals("0", c1.getTicketsAsArray()[0][0]);
		assertEquals(t1.getTicketName(), c1.getTicketsAsArray()[0][1]);
		
		assertEquals("1", c1.getTicketsAsArray()[1][0]);
		assertEquals(t2.getTicketName(), c1.getTicketsAsArray()[1][1]);
		
		assertEquals("2", c1.getTicketsAsArray()[2][0]);
		assertEquals(t3.getTicketName(), c1.getTicketsAsArray()[2][1]);
		
	}
	
	/**
	 * Test that -1 is returned when the name comes before the compared name, 1 when it comes after, and 0 if they are equal.
	 */
	@Test
	void testCompareTo() {
		assertEquals(-1, c1.compareTo(c2));
		assertEquals(1, c3.compareTo(c2));
		assertEquals(0, c3.compareTo(c3));
	}

}
