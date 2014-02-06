package cn.heroes.yellow.util;

import cn.heroes.yellow.exception.CellNameWrongException;

/**
 * 棋盘工具类, 用于处理Excel的单元格位置.
 * 
 * @author cq
 * 
 */
public class Chessboard {

	/**
	 * 根据单元格位置名得出二维表格位置. 如：A2 --> 2, 1
	 * <p>
	 * NOTE: 默认字母不会超过一位.
	 * </p>
	 * 
	 * @param cellName
	 *            单元格位置名, e.g. A2.
	 * @return 该单元格的二维表位置, 1-based, e.g. [2, 1] 第一位为行号， 第二位为列号.
	 */
	public int[] position(String cellName) {
		int length = cellName.length();
		
		char c = cellName.charAt(0);
		int row = c - 64;
		if(row > 26 || row < 1) {
			throw new CellNameWrongException("");
		}
		
		String number = cellName.substring(1);
		int parseInt = Integer.parseInt(number);
		return new int[] { c - 64 };
	}
}
