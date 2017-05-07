package firstStep;

import java.util.Collections;
import java.util.LinkedList;

import interFaces.Tabular;

public class MyTabular implements Tabular {
	public int num;
	public col group0 = new col();
	public LinkedList<Integer> minTerms = new LinkedList<Integer>();

	@Override
	public void setNumOfVariables(int num) {
		// TODO Auto-generated method stub
		this.num = num;
	}

	@Override
	public void setMinterms(String minterms, String dontcares) {
		// TODO Auto-generated method stub
		int flag = minterms.length();
		Group[] impls = new Group[num + 1];
		for (int i = 0; i <= num; i++) {
			impls[i] = new Group();
		}
		minterms += "," + dontcares;
		LinkedList<Integer> numbers = new LinkedList<Integer>();
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
			if (c != null && c.length() != 0) {
				if (Integer.parseInt(c.toString()) >= (int) Math.pow(2, num)) {
					try {
						throw new Exception("un valid minterm");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				numbers.add(Integer.parseInt(c.toString()));
				if (i <= flag) {
					minTerms.add(Integer.parseInt(c.toString()));
				}
			}
		}
		Collections.sort(numbers);
		for (int i = 0; i < numbers.size() - 1; i++) {
			if (numbers.get(i).equals(numbers.get(i + 1))) {
				numbers.remove(i + 1);
				if (i < flag) {
					minTerms.remove(i);
				}
				i--;
			}
		}
		for (int i = 0; i < numbers.size(); i++) {
			int impl = numbers.get(i), tmp = impl;
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
			minterm.cover.add(impl);
			impls[numOfOnes].add(minterm);
		}
		for (int i = 0; i <= num; i++) {
			if (impls[i].myGroup.size() != 0) {
				group0.colGroups.add(impls[i]);
			}
		}
	}
}
