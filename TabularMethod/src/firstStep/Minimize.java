package firstStep;

import java.util.LinkedList;

public class Minimize {
	public Minimize() {

	}

	private static LinkedList<Implicant> mini(LinkedList<Implicant> imps, LinkedList<Integer> minTerms) {
		LinkedList<Implicant> res = new LinkedList<Implicant>();
		LinkedList<boolean[]> covered = new LinkedList<boolean[]>();
		boolean hasDeleted = true;
		while (hasDeleted) {
			hasDeleted = false;
			for (int i = 0; i < minTerms.size(); i++) {
				covered.add(new boolean[imps.size()]);
			}
			for (int i = 0; i < imps.size(); i++) {
//				LinkedList<Integer> mincoverd = imps.get(i).cover();
//				for (int j = 0; j < mincoverd.size(); j++) {
//					int ind = minTerms.indexOf(mincoverd.get(j));
//					covered.get(ind)[i] = true;
				}
			}
			for (int i = 0; i < minTerms.size(); i++) {
				boolean[] minTermCoverd = covered.get(i);
				int count = 0;
				for (int j = 0; j < imps.size(); i++) {
					if (minTermCoverd[j])
						count++;
				}
				if (count == 1) {
					hasDeleted = true;
					for (int j = 0; j < imps.size(); i++) {
						if (minTermCoverd[j]) {
							res.add(imps.get(j));
							for (int k = 0; k < covered.size(); k++) {
								if (covered.get(k)[j]) {
									minTerms.remove(k);
									covered.remove(k);
								}
							}
							break;
						}
					}
				}
			}
//		}
		return res;
	}
}
