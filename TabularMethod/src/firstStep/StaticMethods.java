package firstStep;

import java.util.LinkedList;

public class StaticMethods {
	
	/**
	 * @param num
	 * @return	 true if the number (num) is power of two (1,2,4,8,..,etc)
	 * 			 false if the number is not
	 */
	public static boolean isPowerOf2(int num) {
		int numOfOnes = 0;
		while (num != 0) {
			numOfOnes += num % 2;
			num /= 2;
		}
		return numOfOnes == 1;
	}
	
	/**
	 * @param ll
	 * Remove the Duplicate from a linked list
	 */
	public static void removeDuplicate(LinkedList<?> ll) {
		for (int i = 0; i < ll.size(); i++) {
			for (int j = 0; j < ll.size(); j++) {
				if (ll.get(i).equals(ll.get(j)) && i != j) {
					ll.remove(j);
				}
			}
		}
	}
}
