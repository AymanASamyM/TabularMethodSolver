package secondStep;

import java.util.LinkedList;

import firstStep.Implicant;

public class PrimeImplicants {
	public LinkedList<Implicant> myPrimeImplicant = new LinkedList<Implicant>();
	public LinkedList<Integer> minterms;
	
	public PrimeImplicants(LinkedList<Integer> minterms, LinkedList<Implicant> primeImplicants) {
		this.minterms = minterms;
		this.myPrimeImplicant = primeImplicants;
	}
	
	public LinkedList<Implicant> genrateEssential()
	{
		LinkedList<Implicant> essentialPrimeImps = new LinkedList<Implicant>();
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
		return essentialPrimeImps;
	}
	
	public void deleteImplicant(Implicant imp)
	{
		for(Integer minterm : imp.cover)
		{
			minterms.remove(minterm);
			for(Implicant secondImp : myPrimeImplicant)
			{
				if(imp != secondImp)
				{
					secondImp.cover.remove(minterm);
				}
			}
		}
		myPrimeImplicant.remove(imp);
	}
}
