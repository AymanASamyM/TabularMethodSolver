package firstStep;

import java.util.LinkedList;

public class Implicant {
	/**
	 * 
	 */
	public int num;
	public LinkedList<Integer> difs = new LinkedList<Integer>();
	public boolean check = false;
	public Implicant() {
		
	}
	public Implicant(Implicant imp) {
		num = imp.num;
		check = imp.check;
		difs = new LinkedList<Integer>(imp.difs);
	}	
}
