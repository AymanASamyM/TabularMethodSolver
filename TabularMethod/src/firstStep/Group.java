package firstStep;

import java.util.LinkedList;

import firstStep.Implicant;

public class Group {
	public LinkedList<Implicant> implicants = new LinkedList<Implicant>();

	/**
	 * @param imp
	 * Add an Implicant (imp) to the Group
	 */
	public void add(Implicant imp) {
		implicants.add(imp);
	}
	
	/**
	 * @return the size of the Group
	 */
	public int size() {
		return implicants.size();
	}
	

	/**
	 * @param index
	 * @return the implicant number "index"
	 */
	public Implicant get(int index) {
		return implicants.get(index);
	}

	/**
	 * @param a
	 * @param b
	 * Merge two groups and
	 * @return the mergedGroup
	 */
	public static Group merge(Group a, Group b) {
		Group res = new Group();
		for (Implicant firstImp : a.implicants) {
			for (Implicant secondImp : b.implicants) {
				int difference = secondImp.num - firstImp.num;
				if (difference < 0)
					continue;
				if (Implicant.hasequalDiffs(firstImp, secondImp) && StaticMethods.isPowerOf2(difference)) {
					Implicant mergedImp = new Implicant(firstImp);
					firstImp.ischecked = secondImp.ischecked = true;
					mergedImp.ischecked = false;
					mergedImp.addDifference(difference);
					res.add(mergedImp);
				}
			}
		}
		StaticMethods.removeDuplicate(res.implicants);
		return res;
	}

	/**
	 * @return the prime Implicant in a group (unchecked implicants)
	 */
	public LinkedList<Implicant> getPrimeImplicants() {
		LinkedList<Implicant> res = new LinkedList<Implicant>();
		for (Implicant currentImp : implicants) { 
			if (!currentImp.ischecked) {
				res.add(currentImp);
			}
		}
		return res;
	}
}
