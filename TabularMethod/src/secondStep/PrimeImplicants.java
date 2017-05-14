package secondStep;

import java.util.LinkedList;

import firstStep.Implicant;
import firstStep.Inputs;

public class PrimeImplicants {
	public LinkedList<Implicant> myPrimeImplicant = new LinkedList<Implicant>();
	public LinkedList<Integer> minterms;
	public LinkedList<Implicant> essentialPrimeImps = new LinkedList<Implicant>();
	public LinkedList<Combination> rightCompinations = new LinkedList<Combination>();
	public Combination comb = new Combination();
	public char[] variables = new char[Inputs.variblesNum];
	public LinkedList<String> solutions = new LinkedList<String>();

	public PrimeImplicants(LinkedList<Integer> minterms, LinkedList<Implicant> primeImplicants) {
		this.minterms = minterms;
		this.myPrimeImplicant = primeImplicants;
		genrateEssential();
		generateRightCombinations(0);
		deleteRedundantCombinations();
		initializeArrOfVariables();
	}

	/**
	 * @return the essential Prime Implicants
	 */
	public LinkedList<Implicant> getEssentials() {
		return essentialPrimeImps;
	}

	/**
	 * @param start
	 *            recursive method to generate right combinations of non
	 *            essential prime implicants
	 */
	private void generateRightCombinations(int start) {
		if (start == myPrimeImplicant.size()) {
			if (comb.coverAllMinterms(minterms))
				rightCompinations.add(comb.copy());
			return;
		}
		generateRightCombinations(start + 1);
		comb.add(myPrimeImplicant.get(start));
		generateRightCombinations(start + 1);
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
	private void genrateEssential() {
		boolean hasChanged = true;
		while (hasChanged) {
			hasChanged = false;
			hasChanged = deleteRedundantImps();
			for (int i = 0; i < minterms.size(); i++) {
				Integer minterm = minterms.get(i);
				int coveredBy = 0;
				for (Implicant imp : myPrimeImplicant) {
					if (imp.coversminTerm(minterm)) {
						coveredBy++;
					}
				}
				if (coveredBy == 1)// Essential Prime Implicant
				{
					hasChanged = true;
					deleteEssentialImp(minterm);
				}
			}
		}
	}

	/**
	 * delete Redundant Implicants and
	 * 
	 * @return true if( has deleted one or more) and false if hasn't deleted
	 *         anything
	 */
	private boolean deleteRedundantImps() {
		boolean hasdeleted = false;
		for (int i = 0; i < myPrimeImplicant.size(); i++) {
			Implicant firstImp = myPrimeImplicant.get(i);
			for (int j = 0; j < myPrimeImplicant.size(); j++) {
				Implicant secondImp = myPrimeImplicant.get(j);
				if (firstImp != secondImp && firstImp.coversAllOf(secondImp)) {
					myPrimeImplicant.remove(secondImp);
					hasdeleted = true;
				}
			}
		}
		return hasdeleted;
	}

	/**
	 * @param minterm
	 *            delete Essential Prime Implicant "minterm"
	 */
	private void deleteEssentialImp(Integer minterm) {
		for (int j = 0; j < myPrimeImplicant.size(); j++) {
			Implicant imp = myPrimeImplicant.get(j);
			if (imp.coversminTerm(minterm)) {
				essentialPrimeImps.add(new Implicant(imp));
				deleteImplicant(imp);
				break;
			}
		}
	}

	/**
	 * @param deletedImp
	 *            delete "deletedImp" and all of it's minterms from all the
	 *            prime implicant and the minterms itself
	 */
	private void deleteImplicant(Implicant deletedImp) {
		for (Integer minterm : deletedImp.coveredMinterms) {
			minterms.remove(minterm);
			for (Implicant imp : myPrimeImplicant) {
				if (deletedImp != imp) {
					imp.removeCoveredMinterm(minterm);
				}
			}
		}
		myPrimeImplicant.remove(deletedImp);
	}

	/**
	 * 
	 */
	private void deleteRedundantCombinations() {
		for (int i = 0; i < rightCompinations.size(); i++) {
			Combination firstComb = rightCompinations.get(i);
			for (int j = 0; j < rightCompinations.size(); j++) {
				Combination secondComb = rightCompinations.get(j);
				if (firstComb != secondComb && secondComb.coversAllImpsOf(firstComb)) {
					rightCompinations.remove(j);
					j = 0;
				}
			}
		}
	}

	/**
	 * get minmum solutions from right implicants
	 * 
	 * @return linked list of combination which are minmum solutions
	 */
	public LinkedList<Combination> getMinimumSolutions() {
		LinkedList<Combination> minmum = new LinkedList<Combination>();
		int min = rightCompinations.get(0).myCombination.size();
		if (min == 0) {
			Combination tmp = new Combination();
			tmp.myCombination.addAll(essentialPrimeImps);
			minmum.add(tmp);
			return minmum;
		}
		for (int i = 0; i < rightCompinations.size(); i++) {
			if (rightCompinations.get(i).myCombination.size() < min) {
				min = rightCompinations.get(i).myCombination.size();
			}
		}

		for (int i = 0; i < rightCompinations.size(); i++) {
			if (rightCompinations.get(i).myCombination.size() == min) {
				rightCompinations.get(i).myCombination.addAll(essentialPrimeImps);
				minmum.add(rightCompinations.get(i));
			}
		}
		return minmum;
	}

	/**
	 * initialize array of chars to be {A, B, C, D, ..}
	 */
	private void initializeArrOfVariables() {
		for (int i = 0; i < variables.length; i++) {
			variables[i] = (char) ('A' + i);
		}
	}

	/**
	 * print all soltions put all minmum solutions in array of strings =>
	 * solutions
	 * 
	 * @param sol
	 *            sol is linked list of combinations which are the minmum
	 *            solutions
	 */
	public void printMinmumSolution(LinkedList<Combination> sol) {
		for (int i = 0; i < sol.size(); i++) {
			StringBuilder res = new StringBuilder();
			for (int j = 0; j < sol.get(i).myCombination.size(); j++) {
				res.append(sol.get(i).myCombination.get(j).printImplicant(variables));
				if (j != sol.get(i).myCombination.size() - 1) {
					res.append(" + ");
				}
			}
			solutions.add(res.toString());
		}
	}
	
}
