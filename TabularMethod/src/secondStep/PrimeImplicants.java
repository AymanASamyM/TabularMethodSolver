package secondStep;

import java.util.LinkedList;

import firstStep.Implicant;

public class PrimeImplicants {
	public LinkedList<Implicant> myPrimeImplicant = new LinkedList<Implicant>();
	public LinkedList<Integer> minterms;
	public LinkedList<Implicant> essentialPrimeImps = new LinkedList<Implicant>();
	public LinkedList<Combination> rightCompinations = new LinkedList<Combination>();
	public Combination comb = new Combination();
	
	public PrimeImplicants(LinkedList<Integer> minterms, LinkedList<Implicant> primeImplicants) {
		this.minterms = minterms;
		this.myPrimeImplicant = primeImplicants;
		genrateEssential();
		generateRightCombinations(0);
		deleteRedundantCombinations();
	}
	
	/**
	 * @return the essential Prime Implicants
	 */
	public LinkedList<Implicant> getEssentials()
	{
		return essentialPrimeImps;
	}
	/**
	 * @param start
	 * recursive method to generate right combinations of non essential prime implicants
	 */
	private void generateRightCombinations(int start) {
		if(start == myPrimeImplicant.size())
		{
			if(comb.coverAllMinterms(minterms))
				rightCompinations.add(comb.copy());
			return;
		}
		generateRightCombinations(start+1);
		comb.add(myPrimeImplicant.get(start));
		generateRightCombinations(start+1);
		comb.removeLast();
	} 
	
	/**
	 * @return the right combinations
	 */
	public LinkedList<Combination> getRigthCombinations() {
		return rightCompinations;
	}
	/**
	 * generate the prime Implicants linked list
	 */
	private void genrateEssential()
	{
		boolean haschanged = true;
		while (haschanged) {
			haschanged = false;
			haschanged = deleteRedundantImps();
			for(int i = 0;i < minterms.size();i++)
			{
				Integer minterm = minterms.get(i);
				int coveredBy = 0;
				for(Implicant imp : myPrimeImplicant)
				{
					if(imp.coversminTerm(minterm))
					{
						coveredBy++;
					}
				}
				if(coveredBy == 1)//Essential Prime Implicant
				{
					haschanged = true;
					deleteEssentialImp(minterm);
				}
			}
		}
	}
	
	/**
	 * delete Redundant Implicants and
	 * @return true if( has deleted one or more)
	 * and     false if hasn't deleted anything 
	 */
	private boolean deleteRedundantImps() {
		boolean hasdeleted = false;
		for(int i = 0;i < myPrimeImplicant.size();i++)
		{
			Implicant firstImp = myPrimeImplicant.get(i);
			for(int j = 0;j < myPrimeImplicant.size();j++)
			{
				Implicant secondImp = myPrimeImplicant.get(j);
				if(firstImp != secondImp && firstImp.coversAllOf(secondImp))
				{
					myPrimeImplicant.remove(secondImp);
					hasdeleted = true;
				}
			}
		}
		return hasdeleted;
	}
	/**
	 * @param minterm
	 * delete Essential Prime Implicant "minterm"
	 */
	private void deleteEssentialImp(Integer minterm) {
		for(int j = 0;j < myPrimeImplicant.size();j++)
		{
			Implicant imp = myPrimeImplicant.get(j);
			if(imp.coversminTerm(minterm))
			{
				essentialPrimeImps.add(new Implicant(imp));
				deleteImplicant(imp);
				break;
			}
		}
	}
	/**
	 * @param  deletedImp
	 * delete "deletedImp" and all of it's minterms from all the prime implicant and the minterms itself 
	 */
	private void deleteImplicant(Implicant deletedImp)
	{
		for(Integer minterm : deletedImp.coveredMinterms)
		{
			minterms.remove(minterm);
			for(Implicant imp : myPrimeImplicant)
			{
				if(deletedImp != imp)
				{
					imp.removeCoveredMinterm(minterm);
				}
			}
		}
		myPrimeImplicant.remove(deletedImp);
	}
	
	private void deleteRedundantCombinations() {
		for(int i = 0;i < rightCompinations.size();i++)
		{
			Combination firstComb = rightCompinations.get(i);
			for(int j = 0;j < rightCompinations.size();j++)
			{
				Combination secondComb = rightCompinations.get(j);
				if(firstComb != secondComb && secondComb.coversAllImpsOf(firstComb))
				{
					rightCompinations.remove(j);
					j = 0;
				}
			}
		}
	}
}
