package Try;

import firstStep.MyTabular;
import interFaces.Tabular;

public class Try {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyTabular tab = new MyTabular();
		tab.setNumOfVariables(3);
		tab.setMinterms("0,1 , 2 , 3  , 4 , 5 ,  6  ");
		System.out.println(tab.groups.get(2).myGroup.get(0).num);
//		System.out.println(tab.minTerms[0][0]);
//		System.out.println(tab.minTerms[1][0] + " " + tab.minTerms[1][1] + " " + tab.minTerms[1][2]);
//		System.out.println(tab.minTerms[2][0] + " " + tab.minTerms[2][1]+ " " + tab.minTerms[2][2]);
//		System.out.println(tab.minTerms[3][0]);
		// System.out.println(tab.implicants.get(3).minTerm.get(0));
	}

}
