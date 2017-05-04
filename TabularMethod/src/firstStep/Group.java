package firstStep;

import java.util.Collections;
import java.util.LinkedList;

import firstStep.Implicant;

public class Group {
	public LinkedList<Implicant> myGroup = new LinkedList<Implicant>();
	public int size = 0;
	public static boolean mergeFlag = false;

	public void add(Implicant imp) {
		myGroup.add(imp);
		size++;
	}

	public static boolean isPowerOf2(int tmp) {
		int numOfOnes = 0;
		while (tmp != 0) {
			numOfOnes += tmp % 2;
			tmp /= 2;
		}
		return numOfOnes == 1;
	}

	public Implicant get(int index) {
		return myGroup.get(index);
	}

	public static Group merge(Group a, Group b) {
		Group res = new Group();
		mergeFlag = false;
		for (int i = 0; i < a.size; i++) {
			for (int j = 0; j < b.size; j++) {
				int diff = b.get(j).num - a.get(i).num;
				if (diff < 0)
					continue;
				if (compareDifs(a.get(i), b.get(j)) && isPowerOf2(diff)) {
					Implicant imp = new Implicant(a.get(i));
					a.get(i).check = true;
					b.get(j).check = true;
					imp.check = false;
					imp.difs.add(new Integer(diff));
					Collections.sort(imp.difs);
					res.add(imp);
					mergeFlag = true;
				}
			}
		}
		res.removeDuplicate();
		return res;
	}

	public void removeDuplicate() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (this.get(i).equals(this.get(j)) && i != j) {
					this.remove(j);
				}
			}
		}
	}

	private void remove(int i) {
		myGroup.remove(i);
		size--;
	}

	public static boolean compareDifs(Implicant a, Implicant b) {
		Collections.sort(a.difs);
		Collections.sort(b.difs);
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

	public LinkedList<Implicant> checker() {
		LinkedList<Implicant> tmp = new LinkedList<Implicant>();
		for (int i = 0; i < myGroup.size(); i++) {
			if (!myGroup.get(i).check) {
				tmp.add(myGroup.get(i));
			}
		}
		return tmp;

	}
}
