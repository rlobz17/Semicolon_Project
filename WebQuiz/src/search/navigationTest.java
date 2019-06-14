package search;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class navigationTest {

	@Test
	void test1() {
		navigation n = new navigation(140, 10);
		int x = n.getPageNumber();
		assertEquals(14, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		for(int i=6; i<=14; i++) {
			assertTrue(arr.contains(i));
		}
		
		assertTrue(arr.contains(1));
	}
	
	@Test
	void test2() {
		navigation n = new navigation(185, 8);
		int x = n.getPageNumber();
		assertEquals(19, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		for(int i=4; i<=12; i++) {
			assertTrue(arr.contains(i));
		}
		
		assertTrue(arr.contains(1));
		assertTrue(arr.contains(19));
	}
	
	@Test
	void test3() {
		navigation n = new navigation(20, 1);
		int x = n.getPageNumber();
		assertEquals(2, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		for(int i=1; i<=2; i++) {
			assertTrue(arr.contains(i));
		}
		
		for(int i=-10; i<=0; i++) {
			assertFalse(arr.contains(i));
		}
		
		for(int i=3; i<=15; i++) {
			assertFalse(arr.contains(i));
		}
	}
	
	@Test
	void test4() {
		navigation n = new navigation(2054, 150);
		int x = n.getPageNumber();
		assertEquals(206, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		for(int i=146; i<=54; i++) {
			assertTrue(arr.contains(i));
		}
		
		assertTrue(arr.contains(1));
		assertTrue(arr.contains(150));
	}
	
	@Test
	void test5() {
		navigation n = new navigation(0, 1);
		int x = n.getPageNumber();
		assertEquals(0, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		assertTrue(arr.contains(1));
	}

}
