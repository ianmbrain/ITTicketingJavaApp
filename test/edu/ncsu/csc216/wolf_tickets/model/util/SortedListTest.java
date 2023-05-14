package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the sorted class in the wolf tickets system.
 * Test that the list remains in sorted order when added to and removed from.
 * Test other methods such as contains
 * 
 * @author ianbrain
 *
 */
class SortedListTest {
	/** String used for testing */
	private static final String S3 = "s3";
	/** String used for testing */
	private static final String S4 = "s4";
	/** String used for testing */
	private static final String S5 = "s5";
	/** String used for testing */
	private static final String S6 = "s6";
	/** String used for testing */
	private static final String S7 = "s7";
	
	/**
	 * Test that the list can be constructed and does not throw an exception.
	 */
	@Test
	void testSortedList() {
		assertDoesNotThrow(() -> new SortedList<String>());
		
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
	}
	
	/**
	 * Test that the list remains in sorted order when added to.
	 */
	@Test
	void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		assertThrows(NullPointerException.class, () -> list.add(null));
		
		list.add(S5);
		assertEquals(1, list.size());
		assertEquals(S5, list.get(0));
		
		list.add(S4);
		assertEquals(2, list.size());
		assertEquals(S4, list.get(0));
		assertEquals(S5, list.get(1));
		
		list.add(S6);
		assertEquals(3, list.size());
		assertEquals(S4, list.get(0));
		assertEquals(S5, list.get(1));
		assertEquals(S6, list.get(2));
		
		assertThrows(IllegalArgumentException.class, () -> list.add(S4));
		assertThrows(IllegalArgumentException.class, () -> list.add(S6));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
	}
	
	/**
	 * Test that the list remains in sorted order when removed from.
	 */
	@Test
	void testRemove() {
		SortedList<String> list = new SortedList<String>();
		list.add(S4);
		list.add(S5);
		list.add(S6);
		list.add(S7);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
		
		String test;
		test = list.remove(2);
		assertEquals(3, list.size());
		assertEquals(S4, list.get(0));
		assertEquals(S5, list.get(1));
		assertEquals(S7, list.get(2));
		assertEquals(test, S6);
		
		test = list.remove(0);
		assertEquals(2, list.size());
		assertEquals(S5, list.get(0));
		assertEquals(S7, list.get(1));
		assertEquals(test, S4);
		
		test = list.remove(1);
		assertEquals(1, list.size());
		assertEquals(S5, list.get(0));
		assertEquals(test, S7);
		
		list.add(S4);
		test = list.remove(1);
		assertEquals(1, list.size());
		assertEquals(S4, list.get(0));
		assertEquals(test, S5);
		
		list.add(S6);
		test = list.remove(0);
		assertEquals(1, list.size());
		assertEquals(S6, list.get(0));
		assertEquals(test, S4);
	}
	
	/**
	 * Test that the contains method returns true if the list contains the element.
	 */
	@Test
	void testContains() {
		SortedList<String> list = new SortedList<String>();
		list.add(S4);
		assertTrue(list.contains(S4));
		assertFalse(list.contains(S5));
		
		list.add(S5);
		assertTrue(list.contains(S4));
		assertTrue(list.contains(S5));
		assertFalse(list.contains(S6));
		
		list.add(S3);
		assertTrue(list.contains(S3));
		assertTrue(list.contains(S4));
		assertTrue(list.contains(S5));
		assertFalse(list.contains(S6));
	}

}
