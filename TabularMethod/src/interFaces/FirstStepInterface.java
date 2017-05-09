package interFaces;

import java.util.LinkedList;

import firstStep.Implicant;

public interface FirstStepInterface {
	
	/**
	 * set Tabular structure number of variables, set minterms and set dont cares
	 * 
	 * @param minterms
	 */
	public void generateTabular(String minterms, String dontcares);
	/**
	 * 
	 * @return prime implicants
	 */
	public LinkedList<Implicant> getImplicants() ;
	/**
	 * 
	 * @return min terms
	 */
	public LinkedList<Integer> getMinterms();
}
