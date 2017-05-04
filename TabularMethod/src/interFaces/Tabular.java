package interFaces;

public interface Tabular {
	/**
	 * set number of variables
	 * 
	 * @param num
	 */
	public void setNumOfVariables(int num);

	/**
	 * set minterms
	 * 
	 * @param minterms
	 */
	public void setMinterms(String minterms, String dontcares);
}
