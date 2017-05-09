package firstStep;

import java.util.LinkedList;

public class Column {
	public LinkedList<Group> colGroups = new LinkedList<Group>();
	public void add(Group a)
	{
		colGroups.add(a);
	}
	public int size() {
		return colGroups.size();
	}
}
