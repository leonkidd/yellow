package demo;


public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		int[] is = new int[10];
		int i = 0;
		try {
			is[i++] = a();
		} catch(RuntimeException e) {
			System.out.println(i);
		}
		
	}
	
	public static int a() {
		throw new RuntimeException();
	}

}
