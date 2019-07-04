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
	
	@Test
	void test6() {
		navigation n = new navigation(179, 1);
		int x = n.getPageNumber();
		
		assertEquals(18, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		assertTrue(arr.size()==6);
		
		for(int i=0; i<arr.size(); i++) {
			if(i<5) {
				assertEquals(i+1, arr.get(i));
			} else {
				assertEquals(18, arr.get(i));
			}
		}
		
		assertTrue(arr.contains(1));
	}
	
	@Test
	void test7() {
		navigation n = new navigation(179, 18);
		int x = n.getPageNumber();
		
		assertEquals(18, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		assertTrue(arr.size()==6);
		
		for(int i=0; i<arr.size(); i++) {
			if(i==0) {
				assertEquals(i+1, arr.get(i));
			} else {
				assertEquals(i+13, arr.get(i));
			}
		}
		
		assertTrue(arr.contains(1));
	}
	
	@Test
	void test8() {
		navigation n = new navigation(179, 8);
		int x = n.getPageNumber();
		
		assertEquals(18, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		assertTrue(arr.size()==11);
		
		for(int i=0; i<arr.size(); i++) {
			if(i==0) {
				assertEquals(i+1, arr.get(i));
			} else if(i>0 && i<10){
				assertEquals(i+3, arr.get(i));
			} else {
				assertEquals(18, arr.get(i));
			}
		}
		
		assertTrue(arr.contains(1));
	}
	
	@Test
	void test9() {
		navigation n = new navigation(179, 14);
		int x = n.getPageNumber();
		
		assertEquals(18, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		assertTrue(arr.size()==10);
		
		for(int i=0; i<arr.size(); i++) {
			if(i==0) {
				assertEquals(i+1, arr.get(i));
			} else {
				assertEquals(i+9, arr.get(i));
			}
		}
		
		assertTrue(arr.contains(1));
	}
	
	@Test
	void test10() {
		navigation n = new navigation(179, 12);
		int x = n.getPageNumber();
		
		assertEquals(18, x);
		
		ArrayList<Integer> arr = n.pagesToShow();
		
		assertTrue(arr.size()==11);
		
		for(int i=0; i<arr.size(); i++) {
			if(i==0) {
				assertEquals(i+1, arr.get(i));
			} else if(i==arr.size()-1){
				assertEquals(18, arr.get(i));
			} else {
				assertEquals(i+7, arr.get(i));
			}
		}
		
		assertTrue(arr.contains(1));
	}

}
