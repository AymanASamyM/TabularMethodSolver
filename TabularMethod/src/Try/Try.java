package Try;

import java.util.LinkedList;

import firstStep.GenerateFistStep;
import firstStep.Implicant;

public class Try {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * MyTabular tab = new MyTabular(); tab.setNumOfVariables(4); tab.
		 * setMinterms("0, 1, 2, 3, 4,4,4,4,4,6,6,6,6,6,6,6,6,6,6, 7, 8, 9,10, 11,12,13,14,15"
		 * , "5"); for (int i = 0; i <= 4; i++) { System.out.print("Group ( " +
		 * i + " ) : "); for (int j = 0; j <= 5; j++) { try {
		 * System.out.print(tab.group0.colGroups.get(i).myGroup.get(j).num +
		 * " "); } catch (Exception e) {
		 * 
		 * } } System.out.println(); } System.out.print("minterms = "); for (int
		 * i = 0; i < tab.minTerms.size(); i++) {
		 * System.out.print(tab.minTerms.get(i) + " "); }
		 */
		GenerateFistStep tab2 = new GenerateFistStep();
		tab2.generateTabular("0,1,4,5,6,9,7,11,15", "10, 14");
		LinkedList<Implicant> impls = tab2.getImplicants();
		if (impls == null) {
			System.out.println(1);
			return;
		}
		for (int i = 0; i < impls.size(); i++) {
			System.out.print(impls.get(i).num + " ");
			for (int j = 0; j < impls.get(i).difs.size(); j++) {
				try {
					System.out.print(impls.get(i).difs.get(j) + " ");
					
				} catch (Exception e) {

				}
			}
			System.out.print("==> it covers : ");
			for (int k = 0;k < impls.get(i).cover.size(); k++) {
				System.out.print(impls.get(i).cover.get(k) + " ");
			}
			System.out.println();
		}
		for (int i = 0; i < tab2.getMinterms().size(); i++) {
			System.out.print(tab2.getMinterms().get(i) + " ");
			
		}
		System.out.println();
//		System.out.println( Math.ceil(Math.log(127+1) / Math.log(2)));
//		for (int i=0;i<Math.pow(2, 9);i++){
//			System.out.print(i + ",");
//		}
	}

}
