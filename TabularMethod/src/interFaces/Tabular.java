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
	public void setMinterms(String minterms);

	/**
	 * set dont cares
	 * 
	 * @param dontCares
	 */
	public void setDontCares(String dontCares);
	
}
