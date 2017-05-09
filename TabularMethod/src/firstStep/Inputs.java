package firstStep;

import java.util.Collections;
import java.util.LinkedList;

public class Inputs {
	public static int num;
	public static LinkedList<Integer> minTerms = new LinkedList<Integer>();
	public static LinkedList<Integer> implicants = new LinkedList<Integer>();

	/**
	 * set number of variables, minterms and dont cares
	 * @param two Strings one for minTerms and another for dont cares
	 */
	public static void setInputs(String minterms, String dontcares) {
		// TODO Auto-generated method stub
		int flag = minterms.length();
		minterms += "," + dontcares;
		for (int i = 0; i < minterms.length(); i++) {
			char z = minterms.charAt(i);
			StringBuilder c = new StringBuilder();
			while (z != ' ' && Character.isDigit(z) && z != ',') {
				c.append(z);
				i++;
				if (i >= minterms.length()) {
					break;
				}
				z = minterms.charAt(i);
			}
			if (c != null && c.length() != 0) {
				implicants.add(Integer.parseInt(c.toString()));
				if (i <= flag) {
					minTerms.add(Integer.parseInt(c.toString()));
				}
			}
		}
		/* remove dublicates from minTerms List and implicants List which have the minterms and dont cars */
		StaticMethods.removeDuplicate(implicants);
		StaticMethods.removeDuplicate(minTerms);
		Collections.sort(implicants);
		Collections.sort(minTerms);
		
		Inputs.num = (int) Math.ceil(Math.log(minTerms.get(minTerms.size() - 1) + 1) / Math.log(2));
	}
	
}