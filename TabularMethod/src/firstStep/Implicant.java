package firstStep;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Implicant {
	/**
	 * 
	 */
	public int num;
	public LinkedList<Integer> difs = new LinkedList<Integer>();
	public boolean check = false;
	public LinkedList<Integer> cover = new LinkedList<Integer>();

	public Implicant() {

	}

	public Implicant(int num) {
		this.num = num;
	}

	public Implicant(Implicant imp) {
		num = imp.num;
		check = imp.check;
		difs = new LinkedList<Integer>(imp.difs);
	}

	public LinkedList<Integer> cover() {
		LinkedList<Integer> res = new LinkedList<Integer>();
		res.add(new Integer(num));
		for (int i = 0; i < difs.size(); i++) {
			res.add(new Integer(difs.get(i).intValue() + num));
		}
		return res;
	}

	@Override
	public boolean equals(Object arg0) {
		Implicant imp = (Implicant) arg0;
		if (this.num != imp.num)
			return false;
		return this.difs.equals(imp.difs);
	}

	public void addCover(Integer minTerm) {
		cover.add(minTerm);
		Collections.sort(cover);
		for (int i = 0; i < cover.size() - 1; i++) {
			if (cover.get(i).equals(cover.get(i + 1))) {
				cover.remove(i);
				i--;
			}
		}
	}
}
