package firstStep;

import java.util.LinkedList;

import interFaces.Tabular;

public class MyTabular implements Tabular {
	private int num;
	public LinkedList<Group> groups = new LinkedList<Group>();
	// public int[][] minTerms = new int [4][3];
	// public int[] dontCares;
	// public LinkedList<Implicants> implicants = new LinkedList<Implicants>();

	@Override
	public void setNumOfVariables(int num) {
		// TODO Auto-generated method stub
		this.num = num;
	}

	@Override
	public void setMinterms(String minterms) {
		// TODO Auto-generated method stub
		Group[] impls = new Group[num + 1];
		for (int i = 0; i <= num; i++) {
			impls[i] = new Group();
		}
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
			int impl = -1, tmp = 0;
			if (c != null && c.length() != 0) {
				impl = Integer.parseInt(c.toString());
				c = null;
				tmp = impl;
			}
			int numOfOnes = 0;
			if (impl == -1) {
				continue;
			}
			while (tmp != 0) {
				numOfOnes += tmp % 2;
				tmp /= 2;
			}
			Implicant minterm = new Implicant();
			minterm.num = impl;
			impls[numOfOnes].myGroup.add(minterm);
		}
		for (int i = 0; i <= num; i++) {
			if (impls[i].myGroup.size() != 0) {
				groups.add(impls[i]);
			}
		}
	}

	@Override
	public void setDontCares(String dontcares) {
		// TODO Auto-generated method stub
		setMinterms(dontcares);
	}

}
