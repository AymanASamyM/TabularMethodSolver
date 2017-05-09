package Try;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import firstStep.Implicant;

public class ImplicantTester {

	@Test
	public void test1() {
		/*
		 * a = 0
		 * b = 6
		 */
		Implicant a = new Implicant(0);
		Implicant b = new Implicant(6);
		assertTrue(Implicant.hasequalDiffs(a, b));
		LinkedList<Integer> aCover = new LinkedList<Integer>();
		LinkedList<Integer> bCover = new LinkedList<Integer>();
		aCover.add(0);
		bCover.add(6);
		assertEquals(aCover, a.coveredMinterms);
		assertEquals(bCover, b.coveredMinterms);
	}
	
	@Test
	public void test2() {
		/*
		 * a = 0(2)
		 * b = 0(4)
		 */
		Implicant a = new Implicant(0);
		Implicant b = new Implicant(0);
		assertTrue(Implicant.hasequalDiffs(a, b));
		a.addDifference(2);
		b.addDifference(4);
		assertFalse(Implicant.hasequalDiffs(a, b));
		LinkedList<Integer> aCover = new LinkedList<Integer>();
		LinkedList<Integer> bCover = new LinkedList<Integer>();
		aCover.add(0);
		aCover.add(2);
		bCover.add(0);
		bCover.add(4);
		assertEquals(aCover, a.coveredMinterms);
		assertEquals(bCover, b.coveredMinterms);
	}
	
	@Test
	public void test3() {
		/*
		 * a = 0(2,4)
		 * b = 1(2,4)
		 */
		Implicant a = new Implicant(0);
		Implicant b = new Implicant(1);
		assertTrue(Implicant.hasequalDiffs(a, b));
		a.addDifference(2);
		a.addDifference(4);
		b.addDifference(2);
		b.addDifference(4);
		assertTrue(Implicant.hasequalDiffs(a, b));
		LinkedList<Integer> aCover = new LinkedList<Integer>();
		LinkedList<Integer> bCover = new LinkedList<Integer>();
		aCover.add(0);
		aCover.add(2);
		aCover.add(4);
		aCover.add(6);
		bCover.add(1);
		bCover.add(3);
		bCover.add(5);
		bCover.add(7);
		assertEquals(aCover, a.coveredMinterms);
		assertEquals(bCover, b.coveredMinterms);
	}

}
