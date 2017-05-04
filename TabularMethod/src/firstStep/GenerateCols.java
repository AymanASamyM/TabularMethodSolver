package firstStep;

import java.util.LinkedList;

public class GenerateCols {
	public LinkedList<col> cols = new LinkedList<col>();

	public void generate(int num, String minterms, String dontcares) {
		MyTabular firstCol = new MyTabular();
		firstCol.setNumOfVariables(num);
		firstCol.setMinterms(minterms, dontcares);
		cols.add(firstCol.group0);
		for (int i = 0; i < firstCol.num; i++) {
			col x = new col();
			Group tmp;
			for (int j = 0; j < cols.get(i).colGroups.size() - 1; j++) {
				tmp = Group.merge(cols.get(i).colGroups.get(j), cols.get(i).colGroups.get(j + 1));
				x.colGroups.add(tmp);
			}
			cols.add(x);
		}

	}

	public LinkedList<Implicant> getImplicants() {
		LinkedList<Implicant> primeImplicants = new LinkedList<Implicant>();
		for (int i = 0; i < cols.size(); i++) {
			LinkedList<Implicant> tmp = null;
			for (int j = 0; j < cols.get(i).colGroups.size(); j++) {
				tmp = cols.get(i).colGroups.get(j).checker();
			
			if (tmp != null) {
				for (int k = 0; k < tmp.size(); k++) {
					primeImplicants.add(tmp.get(k));
				}
			}
			}
		}
		return primeImplicants;
	}
}
