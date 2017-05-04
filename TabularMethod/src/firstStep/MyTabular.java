package firstStep;

import java.util.LinkedList;

import interFaces.Tabular;

public class MyTabular implements Tabular {
	private int num;
	public LinkedList<Integer>[] implicant;
//	public int[][] minTerms = new int [4][3];
//	public int[] dontCares;
	// public LinkedList<Implicants> implicants = new LinkedList<Implicants>();

	@Override
	public void setNumOfVariables(int num) {
		// TODO Auto-generated method stub
		this.num = num;
		
	}

	@Override
	public void setMinterms(String minterms) {
		// TODO Auto-generated method stub
		for (int i = 0; i < minterms.length(); i++) {
			char z = minterms.charAt(i);
			StringBuilder c = new StringBuilder();
			while (z != ' ' && Character.isDigit(z) && z != ',') {
				c.append(z);
				i++;
				if (i >= minterms.length()) {
					break;
				}
				z = minterms.charAt(i);
			}
			int impl = 0;
			if (c != null && c.length() != 0) {
				impl = Integer.parseInt(c.toString());
				c = null;
			}
			int tmp = impl;
			int numOfOnes = 0;
			while (tmp != 0) {
				numOfOnes += tmp % 2;
				tmp /= 2;
			}
			int j=0;
//			while (minTerms[numOfOnes][j] != 0){
				j++;
			}
//			minTerms[numOfOnes][j] = impl;
			Implicant minterm = new Implicant();
//			minTerms[numOfOnes] = minterm.minTerm;
		}
	

	@Override
	public void setDontCares(String dontcares) {
		// TODO Auto-generated method stub
		setMinterms(dontcares);
	}

}
