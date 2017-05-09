package Try;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

import firstStep.Implicant;
import secondStep.PrimeImplicants;

public class PrimeImplicantsTester {

	@Test
	public void test1() {
		/*
		 * 				1	5	7	9 	11	12(c)	14(c)		15
		 * 1(4)			x	x
		 * 1(8)			x			x
		 * 5(2)				x	x
		 * 9(2)						x	x
		 * 12(2)(e)							X		x
		 * 7(8)					x								x
		 * 11(4)						x						x
		 * 14(1)									x			x
		 * 
		 * 
		 * Capital X for essential
		 * c for canceled
		 * e for essential
		 */
		Implicant[] primeImps = new Implicant[8];
		primeImps[0] = new Implicant(1);
		primeImps[0].addDifference(4);
		primeImps[1] = new Implicant(1);
		primeImps[1].addDifference(8);
		primeImps[2] = new Implicant(5);
		primeImps[2].addDifference(2);
		primeImps[3] = new Implicant(9);
		primeImps[3].addDifference(2);
		primeImps[4] = new Implicant(12);
		primeImps[4].addDifference(2);
		primeImps[5] = new Implicant(7);
		primeImps[5].addDifference(8);
		primeImps[6] = new Implicant(11);
		primeImps[6].addDifference(4);
		primeImps[7] = new Implicant(14);
		primeImps[7].addDifference(1);
		LinkedList<Implicant> primeImpsll = new LinkedList<Implicant>(Arrays.asList(primeImps));
		Integer[] minterms = {1,5,7,9,11,12,14,15}; 
		PrimeImplicants prImp = new PrimeImplicants(new LinkedList<Integer>(Arrays.asList(minterms)), primeImpsll);
		LinkedList<Implicant> essentialprimeImp = new LinkedList<Implicant>();
		essentialprimeImp.add(primeImps[4]);
		assertEquals(essentialprimeImp, prImp.genrateEssential());
		assertEquals(7, prImp.myPrimeImplicant.size());
		assertEquals(6, prImp.minterms.size());
	}
	
	@Test
	public void test2() {
		/*
		 * 				0(c)	1(c)	3(c)	5(c)	6(c)	7(c)	8(c)	10		14(c)	15(c)	
		 * 1(2,4)(e)			x		X		X				x
		 * 6(1,8)(e)									X		x						x		x
		 * 0(1)(c)		x		x
		 * 0(8)(e)		X												x
		 * 8(2)															x		x
		 * 10(4)																x		x
		 * 
		 */
		Implicant[] primeImps = new Implicant[6];
		primeImps[0] = new Implicant(1);
		primeImps[0].addDifference(2);
		primeImps[0].addDifference(4);
		primeImps[1] = new Implicant(6);
		primeImps[1].addDifference(1);
		primeImps[1].addDifference(8);
		primeImps[2] = new Implicant(0);
		primeImps[2].addDifference(1);
		primeImps[3] = new Implicant(0);
		primeImps[3].addDifference(8);
		primeImps[4] = new Implicant(8);
		primeImps[4].addDifference(2);
		primeImps[5] = new Implicant(10);
		primeImps[5].addDifference(4);
		LinkedList<Implicant> primeImpsll = new LinkedList<Implicant>(Arrays.asList(primeImps));
		Integer[] minterms = {0,1,3,5,6,7,8,10,14,15}; 
		PrimeImplicants prImp = new PrimeImplicants(new LinkedList<Integer>(Arrays.asList(minterms)), primeImpsll);
		LinkedList<Implicant> essentialprimeImp = new LinkedList<Implicant>();
		essentialprimeImp.add(primeImps[0]);
		essentialprimeImp.add(primeImps[1]);
		essentialprimeImp.add(primeImps[3]);
		assertEquals(essentialprimeImp.size(), prImp.genrateEssential().size());
		assertEquals(2, prImp.myPrimeImplicant.size());
		assertEquals(1, prImp.minterms.size());
	}
}
