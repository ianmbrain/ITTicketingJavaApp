package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the AbstractCategory class. 
 * Tests that tickets can be added, removed, and completed correctly.
 * @author ianbrain
 *
 */
class AbstractCategoryTest {

	/**
	 * Test that the category can be created and throws the proper exceptions.
	 */
	@Test
	void testAbstractCategory() {
		assertDoesNotThrow(() -> new Category("c1", 1));
		
		assertThrows(IllegalArgumentException.class, () -> new Category(null, 1));
		assertThrows(IllegalArgumentException.class, () -> new Category("c1", -1));
	}
	
	/**
	 * Test that tickets are added to the category and remain in sorted order.
	 */
	@Test
	void testAddTicket() {
		Category c1 = new Category("c1", 1);
		Ticket t1 = new Ticket("t1", "description1", false);
		Ticket t2 = new Ticket("t2", "description2", false);
		
		c1.addTicket(t1);
		assertEquals("t1", c1.getTicket(0).getTicketName());
		
		c1.addTicket(t2);
		assertEquals("t1", c1.getTicket(0).getTicketName());
		assertEquals("t2", c1.getTicket(1).getTicketName());
		
		
	}
	
	/**
	 * Test that tickets are removed from the category and other tickets remain in sorted order.
	 */
	@Test
	void testRemoveTicket() {
		Category c1 = new Category("c1", 1);
		Ticket t1 = new Ticket("t1", "description1", false);
		Ticket t2 = new Ticket("t2", "description2", false);
		Ticket t3 = new Ticket("t3", "description3", true);
		
		c1.addTicket(t1);
		c1.addTicket(t2);
		c1.addTicket(t3);
		
		c1.removeTicket(1);
		assertEquals("t1", c1.getTicket(0).getTicketName());
		assertEquals("t3", c1.getTicket(1).getTicketName());
		
		c1.removeTicket(0);
		assertEquals("t3", c1.getTicket(0).getTicketName());
		
		c1.removeTicket(0);
		assertThrows(IndexOutOfBoundsException.class, () -> c1.getTicket(0));
	}

	/**
	 * Test that when tickets are completed, the completed count is incremented and the ticket is removed.
	 */
	@Test
	void testCompleteTicket() {
		Category c1 = new Category("c1", 0);
		Ticket t1 = new Ticket("t1", "description1", false);
		Ticket t2 = new Ticket("t2", "description2", false);
		Ticket t3 = new Ticket("t3", "description3", true);
		
		c1.addTicket(t1);
		c1.addTicket(t2);
		c1.addTicket(t3);
		
		assertDoesNotThrow(() -> c1.completeTicket(t3));
		assertEquals(1, c1.getCompletedCount());
		assertEquals(2, c1.getTickets().size());
		
		assertDoesNotThrow(() -> c1.completeTicket(t2));
		assertEquals(2, c1.getCompletedCount());
		assertEquals(1, c1.getTickets().size());
		
		assertDoesNotThrow(() -> c1.completeTicket(t1));
		assertEquals(3, c1.getCompletedCount());
		assertEquals(0, c1.getTickets().size());
	}
}
