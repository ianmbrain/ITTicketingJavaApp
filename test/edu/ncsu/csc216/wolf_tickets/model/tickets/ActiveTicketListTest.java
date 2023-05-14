package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the active ticket list class in the wolf tickets system.
 * Test that methods for setting the category name, adding and clearing tickets, 
 * and getting the tickets as an array work correctly.
 * 
 * @author ianbrain
 *
 */
class ActiveTicketListTest {
	/** Ticket used for testing. */
	private Ticket t1 = new Ticket("t1", "description1", false);
	/** Ticket used for testing. */
	private Ticket t2 = new Ticket("t2", "description2", false);
	/** Ticket used for testing. */
	private Ticket t3 = new Ticket("t3", "description3", true);
	/** Ticket used for testing. */
	private Ticket t4 = new Ticket("t4", "description3", true);
	/** Ticket used for testing. */
	private Ticket t5 = new Ticket("t5", "description3", true);
	/** Ticket list used for testing */
	private ActiveTicketList l1 = new ActiveTicketList();
	
	/**
	 * Test that the active list name cannot be set to something other than Active Tickets.
	 */
	@Test
	void testSetCategoryName() {
		assertDoesNotThrow(() -> l1.setCategoryName("Active Tickets"));
		assertThrows(IllegalArgumentException.class, () -> l1.setCategoryName("other"));
	}
	
	/**
	 * Test that tickets that are not active throw and exception and that active tickets can be added to the list.
	 */
	@Test
	void testAddTicket() {
		assertThrows(IllegalArgumentException.class, () -> l1.addTicket(t1));
		assertThrows(IllegalArgumentException.class, () -> l1.addTicket(t2));
		
		assertDoesNotThrow(() -> l1.addTicket(t3));
		assertDoesNotThrow(() -> l1.addTicket(t4));
		assertDoesNotThrow(() -> l1.addTicket(t5));
		
		assertEquals(t3.getTicketName(), l1.getTicket(0).getTicketName());
		assertEquals(t4.getTicketName(), l1.getTicket(1).getTicketName());
		assertEquals(t5.getTicketName(), l1.getTicket(2).getTicketName());
	}
	
	/**
	 * Test that clearing tickets removes all the tickets from the list.
	 */
	@Test
	void testClearTickets() {
		ActiveTicketList l2 = new ActiveTicketList();
		
		l2.addTicket(new Ticket("Ticket 1", "Ticket 1 Description", true));
		l2.addTicket(new Ticket("Ticket 2", "Ticket 2 Description", true));
		l2.addTicket(new Ticket("Ticket 3", "Ticket 3 Description", true));
		l2.addTicket(new Ticket("Ticket 4", "Ticket 4 Description", true));
		l2.addTicket(new Ticket("Ticket 5", "Ticket 5 Description", true));
		
		assertDoesNotThrow(() -> l1.clearTickets());
		
		assertEquals(0, l1.getTickets().size());
		assertEquals(0, l1.getTicketsAsArray().length);
		
	}
	
	/**
	 * Test that tickets are returned as an array with the category name and ticket name.
	 */
	@Test
	void testGetTicketsAsArray() {
		Category c1 = new Category("c1", 1);
		t3.addCategory(c1);
		t4.addCategory(c1);
		t5.addCategory(c1);
		
		assertDoesNotThrow(() -> l1.addTicket(t3));
		assertDoesNotThrow(() -> l1.addTicket(t4));
		assertDoesNotThrow(() -> l1.addTicket(t5));
		
		assertEquals(t3.getCategoryName(), l1.getTicketsAsArray()[0][0]);
		assertEquals(t3.getTicketName(), l1.getTicketsAsArray()[0][1]);
		
		assertEquals(t4.getCategoryName(), l1.getTicketsAsArray()[1][0]);
		assertEquals(t4.getTicketName(), l1.getTicketsAsArray()[1][1]);
		
		assertEquals(t5.getCategoryName(), l1.getTicketsAsArray()[2][0]);
		assertEquals(t5.getTicketName(), l1.getTicketsAsArray()[2][1]);
	}

}
