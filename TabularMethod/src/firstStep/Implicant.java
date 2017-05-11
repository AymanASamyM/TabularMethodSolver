package firstStep;

import java.util.Collections;
import java.util.LinkedList;

public class Implicant {
	/**
	 * 
	 */
	public Integer num;
	public LinkedList<Integer> difs = new LinkedList<Integer>();
	public boolean ischecked = false;
	public LinkedList<Integer> coveredMinterms = new LinkedList<Integer>();

	/**
	 * @param num
	 *            Create a new Implicant with the number num
	 */
	public Implicant(int num) {
		this.num = num;
		coveredMinterms.add(new Integer(num));
	}

	/**
	 * @param imp
	 *            Create a new copy of the implicant imp
	 */
	public Implicant(Implicant imp) {
		num = imp.num;
		ischecked = imp.ischecked;
		difs = new LinkedList<Integer>(imp.difs);
		coveredMinterms = new LinkedList<Integer>(imp.coveredMinterms);
	}

	public void addDifference(int difference) {
		difs.add(new Integer(difference));
		Collections.sort(difs);
		addtoCover(difference);
	}

	@Override
	public boolean equals(Object arg0) {
		Implicant imp = (Implicant) arg0;
		if (this.num != imp.num)
			return false;
		return this.difs.equals(imp.difs);
	}

	/**
	 * @param difference
	 *            Update cover when adding "difference" to difs
	 */
	private void addtoCover(int difference) {
		LinkedList<Integer> newCover = new LinkedList<Integer>(coveredMinterms);
		for (int i = 0; i < newCover.size(); i++) {
			newCover.set(i, newCover.get(i) + difference);
		}
		coveredMinterms.addAll(newCover);
		Collections.sort(coveredMinterms);
	}

	/**
	 * @param a
	 * @param b
	 * @return true if implicant a & b has identical differences
	 * @return false otherwise
	 */
	public static boolean hasequalDiffs(Implicant a, Implicant b) {
		if (a.difs.size() == b.difs.size()) {
			for (int i = 0; i < a.difs.size(); i++) {
				if (!a.difs.get(i).equals(b.difs.get(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param imp
	 * @return true if this implicant covers all the minterms that 'imp' does
	 *         and more
	 * @return false if this implicant covers the same minterms as 'imp' does or
	 *         less
	 */
	public boolean coversAllOf(Implicant imp) {
		if (coveredMinterms.size() <= imp.coveredMinterms.size()) {
			return false;
		}
		for (Integer i : imp.coveredMinterms) {
			if (!coveredMinterms.contains(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param minterm
	 * @return true if this implicant covers 'minterm'
	 */
	public boolean coversminTerm(Integer minterm) {
		if (coveredMinterms.contains(minterm)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * remove an covered minterm from cover
	 * 
	 * @param minterm
	 */
	public void removeCoveredMinterm(Integer minterm) {
		coveredMinterms.remove(minterm);
	}

	/**
	 * 
	 * @param a
	 *            array of chars {A, B, C, ..}
	 * @return string of the implicant rep in A,B,C,..
	 */
	public String printImplicant(char[] a) {
		if (difs.size() == Inputs.variblesNum) {
			return "1";
		}
		StringBuilder result = new StringBuilder();
		char[] numInBinary = toBinary();
		for (int i = 0; i < difs.size(); i++) {
			int index = Inputs.variblesNum - indexOfOne(difs.get(i));
			numInBinary[index] = '-';
		}
		for (int i = 0; i < numInBinary.length; i++) {
			if (numInBinary[i] == '1') {
				result.append(a[i]);
			} else if (numInBinary[i] == '0') {
				result.append(a[i]);
				result.append('`');
			} else {
				continue;
			}
		}
		return result.toString();

	}

	/**
	 * 
	 * @return binary representation of num
	 */
	private char[] toBinary() {
		char[] tmp = Integer.toBinaryString(num).toCharArray();
		char[] numInBinary = new char[Inputs.variblesNum];
		for (int i = 0, j = 0; i < numInBinary.length; i++) {
			if (i < numInBinary.length - tmp.length) {
				numInBinary[i] = '0';
			} else {
				numInBinary[i] = tmp[j];
				j++;
			}
		}
		return numInBinary;
	}

	/**
	 * 
	 * @param n
	 *            is power of two like {1, 2, 4, 8, ..}
	 * @return the index of one in binary representation of the number
	 */
	private int indexOfOne(int n) {
		int index = 0;
		while (n != 0) {
			n = n >> 1;
			index++;
		}
		return index;
	}

}
