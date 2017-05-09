package Try;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import firstStep.GenerateFistStep;
import firstStep.Implicant;

public class GenerateTester {

	GenerateFistStep tester = new GenerateFistStep();

	@Test
	public void test() {
		tester.generateTabular("0,1,4,5,6,9,7,11,15", "10, 14");
		LinkedList<Implicant> impls = tester.getImplicants();

		int[] num = { 1, 9, 0, 4, 6, 10 };
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] dif = new LinkedList[num.length];
		for (int i = 0; i < num.length; i++) {
			dif[i] = new LinkedList<Integer>();
		}
		int[] minTerms = { 0, 1, 4, 5, 6, 7, 9, 11, 15 };
		dif[0].add(8);
		dif[1].add(2);
		dif[2].add(1);	dif[2].add(4);		
		dif[3].add(1);	dif[3].add(2);		
		dif[4].add(1);	dif[4].add(8);		
		dif[5].add(1);	dif[5].add(4);
		
		for (int i = 0; i < impls.size(); i++) {
			assertEquals(impls.get(i).num, (num[i]));
			for (int j = 0; j < impls.get(i).difs.size(); j++) {
				try {
					assertTrue(impls.get(i).difs.get(j).equals(dif[i].get(j)));
				} catch (Exception e) {

				}
			}
		}
		for (int i = 0; i < tester.getMinterms().size(); i++) {
			assertTrue(tester.getMinterms().get(i).equals(minTerms[i]));
		}
	}

}
