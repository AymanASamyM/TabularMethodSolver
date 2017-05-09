package firstStep;

import java.util.LinkedList;

public class Column {
	public LinkedList<Group> mycolumn = new LinkedList<Group>();
	/**
	 * add a new group 'a' to the column
	 * @param a
	 */
	public void add(Group a)
	{
		mycolumn.add(a);
	}
	/**
	 * @return the size of the group
	 */
	public int size() {
		return mycolumn.size();
	}
}
