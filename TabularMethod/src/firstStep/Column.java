package firstStep;

import java.util.LinkedList;

public class Column {
	public LinkedList<Group> myColumn = new LinkedList<Group>();
	/**
	 * add a new group 'a' to the column
	 * @param a
	 */
	public void add(Group a)
	{
		myColumn.add(a);
	}
	/**
	 * @return the size of the group
	 */
	public int size() {
		return myColumn.size();
	}
}
