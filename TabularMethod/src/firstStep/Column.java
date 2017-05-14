package firstStep;

import java.util.LinkedList;

public class Column {
	public LinkedList<Group> myColumn = new LinkedList<Group>();

	/**
	 * add a new group 'a' to the column
	 * 
	 * @param a
	 */
	public void add(Group a) {
		myColumn.add(a);
	}

	/**
	 * @param index
	 * @return the group number "index"
	 */
	public Group get(int index) {
		return myColumn.get(index);
	}

	/**
	 * @return the size of the group
	 */
	public int size() {
		return myColumn.size();
	}

	/**
	 * @return the next generated column
	 */
	public Column generateNextCol() {
		Column col = new Column();
		Group tmp;
		for (int i = 0; i < size() - 1; i++) {
			tmp = Group.merge(get(i), get(i + 1));
			// StaticMethods.removeDuplicate(tmp.implicants);
			col.add(tmp);
		}
		return col;
	}

	/**
	 * 
	 * @param implicants
	 * @return first Column
	 */
	public static Column generateFirstColumn(LinkedList<Integer> implicants) {
		Column firstColumn = new Column();
		Group[] groups = new Group[Inputs.variblesNum + 1];
		for (int i = 0; i <= Inputs.variblesNum; i++) {
			groups[i] = new Group();
		}

		for (int i = 0; i < implicants.size(); i++) {
			int implicant, tmp;
			implicant = tmp = implicants.get(i);
			int numOfOnes = 0;
			while (tmp != 0) {
				numOfOnes += tmp % 2;
				tmp /= 2;
			}

			Implicant minterm = new Implicant(implicant);
			groups[numOfOnes].add(minterm);
		}
		for (int i = 0; i <= Inputs.variblesNum; i++) {
			if (groups[i].implicants.size() != 0) {
				firstColumn.myColumn.add(groups[i]);
			}
		}
		return firstColumn;
	}

	public LinkedList<Implicant> colConcat() {
		LinkedList<Implicant> tmp = new LinkedList<Implicant>();
		for (int i = 0; i < myColumn.size(); i++) {
			for (int j = 0; j < myColumn.get(i).implicants.size(); j++) {
				tmp.add(myColumn.get(i).implicants.get(j));
			}
		}
		return tmp;
	}
}
