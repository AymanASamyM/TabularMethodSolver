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
	public Implicant(int num) {
		this.num = num;
	}
	public Implicant(Implicant imp) {
		num = imp.num;
		check = imp.check;
		difs = new LinkedList<Integer>(imp.difs);
	}
	
	@Override
	public boolean equals(Object arg0) {
		Implicant imp = (Implicant) arg0;
		if(this.num != imp.num)
			return false;
		return this.difs.equals(imp.difs);
	}
}
