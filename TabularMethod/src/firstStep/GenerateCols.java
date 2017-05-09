package firstStep;

import java.util.LinkedList;

public class GenerateCols {
	public LinkedList<Column> cols = new LinkedList<Column>();
	MyTabular firstCol = new MyTabular();

	public void generate(int num, String minterms, String dontcares) {
		firstCol.setNumOfVariables(num);
		firstCol.setMinterms(minterms, dontcares);
		cols.add(firstCol.group0);
		int i = 0;
		while (i < firstCol.num) {
			Column x = new Column();
			Group tmp;
			for (int j = 0; j < cols.get(i).colGroups.size() - 1; j++) {
				tmp = Group.merge(cols.get(i).colGroups.get(j), cols.get(i).colGroups.get(j + 1));
				x.colGroups.add(tmp);
			}
			cols.add(x);
			i++;
		}
	}

	public LinkedList<Implicant> getImplicants() {
		LinkedList<Implicant> primeImplicants = new LinkedList<Implicant>();
		for (int i = 0; i < cols.size(); i++) {
			LinkedList<Implicant> tmp = null;
			for (int j = 0; j < cols.get(i).colGroups.size(); j++) {
				tmp = cols.get(i).colGroups.get(j).getPrimeImplicants();
				if (tmp != null) {
					for (int k = 0; k < tmp.size(); k++) {
						if (!firstCol.minTerms.contains(tmp.get(k).num) && tmp.get(k).difs.size() == 0) {
							continue;
						}
						primeImplicants.add(tmp.get(k));
					}
				}
			}
		}
		return primeImplicants;
	}

	public LinkedList<Integer> getMinterms() {
		return firstCol.minTerms;
	}
}
