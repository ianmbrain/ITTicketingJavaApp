package edu.ncsu.csc216.wolf_tickets.model.group;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Test class for the group class in the wolf tickets system. 
 * Test categories and tickets can be added, edited, and utilized correctly.
 * 
 * @author ianbrain
 *
 */
class GroupTest {
	/** Category used for testing. */
	private Category a1 = new Category("a1", 1);
	/** Category used for testing. */
	private Category a2 = new Category("a2", 1);
	/** Category used for testing. */
	private Category b1 = new Category("b1", 2);
	/** Category used for testing. */
	private Category c1 = new Category("c1", 3);
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
	/** Ticket used for testing. */
	private Ticket t6 = new Ticket("t6", "description3", true);
	
	/**
	 * Test that the group constructor creates a group object and that the correct exceptions are thrown when the parameters are incorrect.
	 */
	@Test
	void testGroup() {
		assertDoesNotThrow(() -> new Group("g1"));
		
		Group g1 = new Group("g1");
		assertEquals("g1", g1.getGroupName());
		assertTrue(g1.isChanged());
		
		assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertThrows(IllegalArgumentException.class, () -> new Group("active tickets"));
	}

	/**
	 * Test that categories are added in alphabetical order and that the most recently added category is the current one.
	 * Test that saving categories changes is changed to false.
	 */
	@Test
	void testAddCategory() {
		Group g1 = new Group("g1");
		
		assertDoesNotThrow(() -> g1.addCategory(a1));
		assertEquals("a1", g1.getCategoriesNames()[1]);
		
		assertDoesNotThrow(() -> g1.addCategory(b1));
		assertEquals("a1", g1.getCategoriesNames()[1]);
		assertEquals("b1", g1.getCategoriesNames()[2]);
		
		assertDoesNotThrow(() -> g1.addCategory(c1));
		assertEquals("a1", g1.getCategoriesNames()[1]);
		assertEquals("b1", g1.getCategoriesNames()[2]);
		assertEquals("c1", g1.getCategoriesNames()[3]);
		
		
		assertDoesNotThrow(() -> g1.addCategory(a2));
		assertEquals("a2", g1.getCategoriesNames()[2]);
		
		assertEquals("a2", g1.getCurrentCategory().getCategoryName());
		
		Category test1 = new Category("active tickets", 1);
		assertThrows(IllegalArgumentException.class, () -> g1.addCategory(c1));
		assertThrows(IllegalArgumentException.class, () -> g1.addCategory(test1));
		
		File f = new File("test-files/test_output.txt");
		assertDoesNotThrow(() -> g1.saveGroup(f));
		assertFalse(g1.isChanged());
	}
	
	/**
	 * Test that the current category can be set to other categories present in the group.
	 */
	@Test
	void testSetCurrentCategory() {
		Group g1 = new Group("g1");
		g1.addCategory(a1);
		g1.addCategory(b1);
		g1.addCategory(c1);
		
		assertDoesNotThrow(() -> g1.setCurrentCategory(a1.getCategoryName()));
		assertEquals(a1.getCategoryName(), g1.getCurrentCategory().getCategoryName());
	}
	
	/**
	 * Test that categories can be edited and an exception is thrown if trying to edit acitve tickets category.
	 */
	@Test
	void testEditCategory() {
		Group g1 = new Group("g1");
		g1.addCategory(a1);
		g1.addCategory(b1);
		g1.addCategory(c1);
		
		Category testEdit = new Category("testEdit", 1);
		g1.addCategory(testEdit);
		
		g1.setCurrentCategory("Active Tickets");
		assertThrows(IllegalArgumentException.class, () -> g1.editCategory("test"));
		
		g1.setCurrentCategory(testEdit.getCategoryName());
		assertThrows(IllegalArgumentException.class, () -> g1.editCategory("Active Tickets"));
		
		assertThrows(IllegalArgumentException.class, () -> g1.editCategory("a1"));
		
		
		assertDoesNotThrow(() -> g1.editCategory("testNewName"));
		assertEquals(testEdit.getCategoryName(), g1.getCurrentCategory().getCategoryName());
		
		assertTrue(g1.isChanged());
	}
	
	/**
	 * Test that categories are removed from the group correctly. The other categories should remain in sorted order.
	 */
	@Test
	void testRemoveCategory() {
		Group g1 = new Group("g1");
		Category testCat1 = new Category("cat1", 0);
		Category testCat2 = new Category("cat2", 0);
		Category testCat3 = new Category("cat3", 0);
		Category testCat4 = new Category("cat4", 0);
		
		g1.addCategory(testCat1);
		assertEquals(testCat1.getCategoryName(), g1.getCurrentCategory().getCategoryName());
		g1.addCategory(testCat2);
		assertEquals(testCat2.getCategoryName(), g1.getCurrentCategory().getCategoryName());
		g1.addCategory(testCat3);
		assertEquals(testCat3.getCategoryName(), g1.getCurrentCategory().getCategoryName());
		g1.addCategory(testCat4);
		assertEquals(testCat4.getCategoryName(), g1.getCurrentCategory().getCategoryName());
		
		g1.removeCategory();
		assertEquals("Active Tickets", g1.getCurrentCategory().getCategoryName());
		
	}
	
	/**
	 * Test that tickets can be added to groups. 
	 * Test that active tickets and current category are updated correctly.
	 */
	@Test
	void testAddTicket() {
		Group g1 = new Group("g1");
		Category testCat1 = new Category("cat1", 0);
		g1.addCategory(testCat1);
		Category testCat2 = new Category("cat2", 0);
		g1.addCategory(testCat2);
		
		assertEquals(testCat2.getCategoryName(), g1.getCurrentCategory().getCategoryName());
		
		g1.addTicket(t1);
		assertEquals(1, g1.getCurrentCategory().getTickets().size());
		g1.addTicket(t2);
		assertEquals(2, g1.getCurrentCategory().getTickets().size());
		g1.addTicket(t3);
		assertEquals(3, g1.getCurrentCategory().getTickets().size());
		
		g1.setCurrentCategory("cat1");
		assertEquals(testCat1.getCategoryName(), g1.getCurrentCategory().getCategoryName());
		g1.addTicket(t4);
		assertEquals(1, g1.getCurrentCategory().getTickets().size());
		g1.addTicket(t5);
		assertEquals(2, g1.getCurrentCategory().getTickets().size());
		g1.addTicket(t6);
		assertEquals(3, g1.getCurrentCategory().getTickets().size());
		
		g1.setCurrentCategory("Active Tickets");
		assertEquals(4, g1.getCurrentCategory().getTickets().size());
		
	}
	
	/**
	 * Test that tickets can be edited and that the new values are correct.
	 */
	@Test
	void testEditTicket() {
		Group g1 = new Group("g1");
		Category testCat1 = new Category("cat1", 0);
		g1.addCategory(testCat1);
		
		Ticket testTicket = new Ticket("test name", "test description", true);
		Ticket testTicket2 = new Ticket("test name2", "test description2", true);
		
		g1.addTicket(testTicket);
		g1.editTicket(0, "new name", "new description", false);
		assertEquals("new name", testTicket.getTicketName());
		assertEquals("new description", testTicket.getTicketDescription());
		assertFalse(testTicket.isActive());
		
		g1.addTicket(testTicket2);
		g1.editTicket(0, "test name2", "test description2", false);
		assertEquals("test name2", testTicket.getTicketName());
		assertEquals("test description2", testTicket.getTicketDescription());
		assertFalse(testTicket.isActive());
	}

}
