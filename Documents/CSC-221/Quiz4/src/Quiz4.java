import java.util.HashMap;
import java.util.Map;


public class Quiz4 {
	/*A lookup table that you can fill with values as you calculate greatest odd divisors*/
	private static Map<Integer, Integer> LOOK_UP_TABLE = new HashMap<Integer, Integer>();
	
	/** 
	 * Sort an array of names and format the names into all the same format
	 * "first last"
	 * 
	 * 
	 * @param names - a String[] of names in "first last" or "last, first" format
	 * @return the names in "first last" format sorted alph by last name, then by first
	 */
//	public String[] copy(String[] names, int a, int b) {
//		String[] create = new String[b-a];
//		for(int i = a; i <= b; i++) {
//			create[i-a] = names[i];
//		}
//		return create;
//	}
//	public String[] sort(String[] names) {
//		int n = names.length;
//		if(names[n] > names[n-1]) {
//			return names;
//		}else {
//			String temp = names[n];
//			names[n]=names[n-1];
//			names[n-1]=temp;
//			return sort(copy(names, 0, n-1))
//		}
//	}
//	public String[] dataCleanup(String[] names) {
//		for (int i = 0; i < names.length; i++) {
//			if (names[i].contains(",")) {
//				String[] name = names[i].split(", ");
//				names[i] = name[1] + " " + name[0];
//			}
//		}
//		return sort(names);
//	}
	
	/**
	 * Given a positive integer n. Return f(1)+f(2)+...+f(n) where
	 * f(x) is the greatest odd divisor of x, and x is a positive integer.
	 * 
	 * @param n - the positive integer to compute the sum of the greatest odd divisors
	 * @return - the sum of the greatest odd divisors of f(1)+f(2)+...+f(n)
	 */
	public static int sumOfGreatestOddDivisors(int n){
		if(n==1) {
			return 1;
		}
		if(LOOK_UP_TABLE.containsKey(n)) {
			return LOOK_UP_TABLE.get(n);
		}
		int c = 0;
		for (int i = 1; i <= n; i=+2) {
			if (n%i==0) {
				c = i;
			}
		}
		return c + sumOfGreatestOddDivisors(n-1);
	}
		
	
	public static void main(String[] args){
		Quiz4 q = new Quiz4();

		System.out.println("**********Testing dataCleanup**************");
		String[] names = {"Joe Smith", "Brown, Sam", "Miller, Judi"};
		//add more test cases here
		//String[] answer = q.dataCleanup(names);

		//for(String n : answer){
//			System.out.println(n);
//		}
		System.out.println("*******************************************\n");
		System.out.println("********Testing sumDivisors************");
		
		int[] testCases = {3, 7, 1}; // Answers: 5, 21, 1, 201537
		
		for(int i : testCases){
			System.out.println("sumOfGreatestOddDivisors(" + i + ") = " + sumOfGreatestOddDivisors(i));
		}
		
		System.out.println("*******************************************\n");
	}
}
