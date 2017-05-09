package secondStep;

import java.util.LinkedList;

import firstStep.Implicant;

public class PrimeImplicants {
	public LinkedList<Implicant> myPrimeImplicant = new LinkedList<Implicant>();
	public LinkedList<Integer> minterms;
	public LinkedList<Implicant> essentialPrimeImps = new LinkedList<Implicant>();
	
	public PrimeImplicants(LinkedList<Integer> minterms, LinkedList<Implicant> primeImplicants) {
		this.minterms = minterms;
		this.myPrimeImplicant = primeImplicants;
		genrateEssential();
	}
	
	public LinkedList<Implicant> getEssentials()
	{
		return essentialPrimeImps;
	}
	private void genrateEssential()
	{
		boolean edited = true;
		while (edited) {
			edited = false;
			//Delete redundent Prime Implicants
			for(int i = 0;i < myPrimeImplicant.size();i++)
			{
				Implicant firstImp = myPrimeImplicant.get(i);
				for(int j = 0;j < myPrimeImplicant.size();j++)
				{
					Implicant secondImp = myPrimeImplicant.get(j);
					if(firstImp != secondImp && firstImp.coversAllOf(secondImp))
					{
						myPrimeImplicant.remove(secondImp);
						edited = true;
					}
				}
			}
			//Find essential prime implicants
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
					edited = true;
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
			}
		}
	}
	
	public void deleteImplicant(Implicant deletedImp)
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
}
