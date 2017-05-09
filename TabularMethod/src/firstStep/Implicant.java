package firstStep;


import java.util.Collections;
import java.util.LinkedList;


public class Implicant {
	/**
	 * 
	 */
	public int num;
	public LinkedList<Integer> difs = new LinkedList<Integer>();
	public boolean ischecked = false;
	public LinkedList<Integer> cover = new LinkedList<Integer>();

	/**
	 * @param num
	 * Create a new Implicant with the number num
	 */
	public Implicant(int num) {
		this.num = num;
		cover.add(new Integer(num));
	}
	
	/**
	 * @param imp
	 * Create a new copy of the implicant imp
	 */
	public Implicant(Implicant imp) {
		num = imp.num;
		ischecked = imp.ischecked;
		difs = new LinkedList<Integer>(imp.difs);
		cover = new LinkedList<Integer>(imp.cover);
	}
	
	public void addDifference(int difference) {
		difs.add(new Integer(difference));
		Collections.sort(difs);
		addtoCover(difference);
	}
	
	@Override
	public boolean equals(Object arg0) {
		Implicant imp = (Implicant) arg0;
		if (this.num != imp.num)
			return false;
		return this.difs.equals(imp.difs);
	}
	
	/**
	 * @param difference
	 * Update cover when adding "difference" to difs
	 */
	private void addtoCover(int difference) {
		LinkedList<Integer> newCover = new LinkedList<Integer>(cover);
		for(int i = 0;i < newCover.size();i++)
		{
			newCover.set(i, newCover.get(i) + difference);
		}
		cover.addAll(newCover);
		Collections.sort(cover);
	}
	
	/**
	 * @param a
	 * @param b
	 * @return true if implicant a & b has identical differences 
	 * @return false otherwise
	 */
	public static boolean hasequalDiffs(Implicant a, Implicant b) {
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
	
	/**
	 * @param imp
	 * @return true if this implicant covers all the minterms that 'imp' does and more
	 * @return false if this implicant covers the same minterms as 'imp' does or less
	 */
	public boolean coversAllOf(Implicant imp)
	{
		if(cover.size() <= imp.cover.size())
		{
			return false;
		}
		for(Integer i : imp.cover)
		{
			if(!cover.contains(i))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param minterm
	 * @return true if this implicant covers 'minterm'
	 */
	public boolean coversminTerm(Integer minterm)
	{
		if(cover.contains(minterm))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
