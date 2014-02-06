package cn.yhhh.test;

public class Main {
	
	/** Alphabet 26位字母表 */
	private static final String ABC = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		for(int i = 65; i < 91; i++) {
			System.out.print((char)i);
		}
	}
	
	public static void way2() {
		String s = "B2";
		char c = s.charAt(0);
		System.out.println(c - 64);
	}
	
	public static void way1() {
		String s = "A2";
		
		// 默认单元格不会超过Z
		String letter = s.substring(0, 1);
		String number = s.substring(1);
		
		int i = ABC.indexOf(letter) + 1; // 0-based

		System.out.println(i);
		System.out.println(number);

	}

}
