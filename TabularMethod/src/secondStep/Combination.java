package secondStep;

import java.util.LinkedList;

import firstStep.Implicant;

public class Combination {
	public LinkedList<Implicant> myCombination = new LinkedList<Implicant>();
	
	/**
	 * add Implicant to the combination 
	 * @param imp
	 */
	public void add(Implicant imp)
	{
		myCombination.add(imp);
	}
	/**
	 * Remove last implicant from the combination
	 */
	public void removeLast() {
		myCombination.removeLast();
	}
	
	/**
	 * @param minterms
	 * @return true if the compination covers all the minterms
	 */
	public boolean coverAllMinterms(LinkedList<Integer> minterms)
	{
		for(Integer minterm : minterms)
		{
			boolean iscovered = false;
			for(Implicant imp : myCombination)
			{
				if(imp.coversminTerm(minterm))
					iscovered = true;
			}
			if(!iscovered)
				return false;
		}
		return true;
	}
	public Combination copy()
	{
		Combination res = new Combination();
		for(Implicant imp : myCombination)
		{
			res.add(new Implicant(imp));
		}
		return res;
	}
	
	public boolean coversAllImpsOf(Combination comb)
	{
		if(myCombination.size() <= comb.myCombination.size())
		{
			return false;
		}
		for(Implicant imp : comb.myCombination)
		{
			if(!myCombination.contains(imp))
			{
				return false;
			}
		}
		return true;
	}
}
