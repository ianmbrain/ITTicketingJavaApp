package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Test class for the swap list class in the wolf tickets system.
 * Test that elements can be added and removed and that other elements remain in swap order.
 * Test that elements can be swapped around the list correctly.
 * 
 * @author ianbrain
 *
 */
class SwapListTest {
	
	/** String used for testing */
	private static final String S1 = "s1";
	/** String used for testing */
	private static final String S2 = "s2";
	/** String used for testing */
	private static final String S3 = "s3";
	/** String used for testing */
	private static final String S4 = "s4";
	
	/**
	 * Test that the list can be constructed and throws the proper exceptions.
	 */
	@Test
	void testSwapList() {
		assertDoesNotThrow(() -> new SwapList<String>());
		
		SwapList<String> list = new SwapList<String>();
		assertEquals(0, list.size());
	}
	
	/**
	 * Test that elements are added to the end of the list
	 */
	@Test
	void testAdd() {
		SwapList<String> list = new SwapList<String>();
		
		assertThrows(NullPointerException.class, () -> list.add(null));
		
		list.add(S1);
		assertEquals(1, list.size());
		assertEquals(S1, list.get(0));
		
		list.add(S2);
		assertEquals(2, list.size());
		assertEquals(S1, list.get(0));
		assertEquals(S2, list.get(1));
		
		list.add(S3);
		assertEquals(3, list.size());
		assertEquals(S1, list.get(0));
		assertEquals(S2, list.get(1));
		assertEquals(S3, list.get(2));
		
		list.add(S3);
		list.add(S3);
		list.add(S3);
		list.add(S3);
		list.add(S3);
		list.add(S3);
		
		// Size is now 10
		list.add(S3);
		
		assertDoesNotThrow(() -> list.add(S3));
		
	
	}

	/**
	 * Test that elements are removed correctly.
	 */
	@Test
	void testRemove() {
		SwapList<String> list = new SwapList<String>();
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		list.add(S1);
		list.add(S2);
		list.add(S3);
		list.add(S4);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		
		list.remove(0);
		assertEquals(3, list.size());
		assertEquals(S2, list.get(0));
		assertEquals(S3, list.get(1));
		assertEquals(S4, list.get(2));
		
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals(S2, list.get(0));
		assertEquals(S4, list.get(1));
		
		list.remove(1);
		assertEquals(1, list.size());
		assertEquals(S2, list.get(0));
	}

	/**
	 * Test that elements can be moved up correctly.
	 */
	@Test
	void testMoveUp() {
		SwapList<String> list = new SwapList<String>();
		list.add(S1);
		list.add(S2);
		list.add(S3);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveUp(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveUp(list.size()));
		
		list.moveUp(0);
		assertEquals(3, list.size());
		assertEquals(S1, list.get(0));
		assertEquals(S2, list.get(1));
		assertEquals(S3, list.get(2));
		
		list.moveUp(1);
		assertEquals(3, list.size());
		assertEquals(S2, list.get(0));
		assertEquals(S1, list.get(1));
		assertEquals(S3, list.get(2));
		
		list.moveUp(2);
		assertEquals(3, list.size());
		assertEquals(S2, list.get(0));
		assertEquals(S3, list.get(1));
		assertEquals(S1, list.get(2));
	}

	/**
	 * Test that elements can be moved down correctly.
	 */
	@Test
	void testMoveDown() {
		SwapList<String> list = new SwapList<String>();
		list.add(S1);
		list.add(S2);
		list.add(S3);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveDown(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveDown(list.size()));
		
		list.moveDown(list.size() - 1);
		assertEquals(3, list.size());
		assertEquals(S1, list.get(0));
		assertEquals(S2, list.get(1));
		assertEquals(S3, list.get(2));
		
		list.moveDown(1);
		assertEquals(3, list.size());
		assertEquals(S1, list.get(0));
		assertEquals(S3, list.get(1));
		assertEquals(S2, list.get(2));
		
		list.moveDown(0);
		assertEquals(3, list.size());
		assertEquals(S3, list.get(0));
		assertEquals(S1, list.get(1));
		assertEquals(S2, list.get(2));
		
		
	}
	
	/**
	 * Test that elements can be moved to the front correctly.
	 */
	@Test
	void testMoveToFront() {
		SwapList<String> list = new SwapList<String>();
		list.add(S1);
		list.add(S2);
		list.add(S3);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveDown(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveDown(list.size()));
		
		list.moveToFront(0);
		assertEquals(3, list.size());
		assertEquals(S1, list.get(0));
		assertEquals(S2, list.get(1));
		assertEquals(S3, list.get(2));
		
		list.moveToFront(1);
		assertEquals(3, list.size());
		assertEquals(S2, list.get(0));
		assertEquals(S1, list.get(1));
		assertEquals(S3, list.get(2));
		
		list.moveToFront(2);
		assertEquals(3, list.size());
		assertEquals(S3, list.get(0));
		assertEquals(S2, list.get(1));
		assertEquals(S1, list.get(2));
	}

	/**
	 * Test that elements can be moved to the back correctly.
	 */
	@Test
	void testMoveToBack() {
		SwapList<String> list = new SwapList<String>();
		list.add(S1);
		list.add(S2);
		list.add(S3);
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveDown(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.moveDown(list.size()));
		
		list.moveToBack(list.size() - 1);
		assertEquals(3, list.size());
		assertEquals(S1, list.get(0));
		assertEquals(S2, list.get(1));
		assertEquals(S3, list.get(2));
		
		list.moveToBack(1);
		assertEquals(3, list.size());
		assertEquals(S1, list.get(0));
		assertEquals(S3, list.get(1));
		assertEquals(S2, list.get(2));
		
		list.moveToBack(0);
		assertEquals(3, list.size());
		assertEquals(S3, list.get(0));
		assertEquals(S2, list.get(1));
		assertEquals(S1, list.get(2));
	}

}
