package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the ticket class in the wolf tickets system.
 * 
 * @author ianbrain
 *
 */
class TicketTest {
	/** Ticket used for testing. */
	private Ticket t1 = new Ticket("t1", "description1", false);
	/** Ticket used for testing. */
	private Ticket t2 = new Ticket("t2", "description2", false);
	/** Ticket used for testing. */
	private Ticket t3 = new Ticket("t3", "description3", true);
	
	/**
	 * Test constructing a new ticket throws exceptions when the ticket name is null or empty.
	 */
	@Test
	void testTicket() {
		assertDoesNotThrow(() -> new Ticket("test1.", "test description.", false));
		
		assertThrows(IllegalArgumentException.class, () -> new Ticket(null, "test description.", false));
		assertThrows(IllegalArgumentException.class, () -> new Ticket("test1.", null, false));
	}
	
	/**
	 * Test that returning the ticket name returns the ticket's name.
	 */
	@Test
	void testGetTicketName() {
		assertEquals("t1", t1.getTicketName());
		assertEquals("t2", t2.getTicketName());
		assertEquals("t3", t3.getTicketName());
	}
	
	/**
	 * Test that setting the name throws an exception when the name is null or empty.
	 */
	@Test
	void testSetTicketName() {
		Ticket t4 = new Ticket("t4.", "description4.", true);
		
		assertThrows(IllegalArgumentException.class, () -> t4.setTicketName(null));
		assertThrows(IllegalArgumentException.class, () -> t4.setTicketName(""));
		assertDoesNotThrow(() -> t4.setTicketName("testing name."));
	}
	
	/**
	 * Test that getTicketDescription() returns the ticket's description.
	 */
	@Test
	void testGetTicketDescription() {
		assertEquals("description1", t1.getTicketDescription());
		assertEquals("description2", t2.getTicketDescription());
		assertEquals("description3", t3.getTicketDescription());
	}
	
	/**
	 * Test that categories can be added to tickets and do not throw exceptions.
	 */
	@Test
	void testAddCategory() {
		Ticket t5 = new Ticket("t5.", "description5.", false);
		Category c4 = new Category("c4", 1);
		Category c5 = new Category("c5", 2);
		Category c6 = new Category("c6", 3);
		
		assertThrows(IllegalArgumentException.class, () -> t5.addCategory(null));
		assertDoesNotThrow(() -> t5.addCategory(c4));
		assertDoesNotThrow(() -> t5.addCategory(c5));
		assertDoesNotThrow(() -> t5.addCategory(c6));
		
		
		
	}
	
	/**
	 * Test that completing a ticket increments the completed count and removes the ticket from the list.
	 */
	@Test
	void testCompleteTicket() {
		ActiveTicketList l1 = new ActiveTicketList();
		Ticket t4 = new Ticket("t4", "description3", true);
		Ticket t5 = new Ticket("t5", "description3", true);
		Ticket t6 = new Ticket("t6", "description3", true);
		l1.addTicket(t4);
		l1.addTicket(t5);
		l1.addTicket(t6);
		
		t6.completeTicket();
		assertEquals(1, l1.getCompletedCount());
		t4.completeTicket();
		assertEquals(2, l1.getCompletedCount());
		t5.completeTicket();
		assertEquals(3, l1.getCompletedCount());
		
		assertEquals(0, l1.getTickets().size());
	}

}
