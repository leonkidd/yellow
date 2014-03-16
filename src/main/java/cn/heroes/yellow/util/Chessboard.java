package cn.heroes.yellow.util;

import cn.heroes.yellow.exception.CellNameWrongException;

/**
 * 棋盘工具类, 用于处理Excel的单元格位置.
 * 
 * @author cq
 * @deprecated use {@link CellUtils} instead, because of this not support that
 *             more than two letters.
 */
public class Chessboard {

	/**
	 * 根据单元格位置名得出二维表格位置(支持大、小写). 如：A2 --> 2, 1
	 * <p>
	 * NOTE: 默认字母不会超过一位.
	 * </p>
	 * 
	 * @param cellName
	 *            单元格位置名, e.g. A2, A代表列数,2代表行数
	 * @return 该单元格的二维表位置, 1-based, e.g. [2, 1] 第一位为行号， 第二位为列号.
	 */
	public static int[] name2pos(String cellName) {
		// 转为大写
		cellName = cellName.toUpperCase();

		// 名字长度
		int length = cellName.length();
		// 不足两位则报错
		if (length < 2) {
			throw new CellNameWrongException("The Cellname [" + cellName
					+ "] length cannot less than 2.");
		}

		// 获取第一位字母(即列号)
		char c = cellName.charAt(0);
		int col = c - 64;
		if (col > 26 || col < 1) {
			throw new CellNameWrongException("The letter [" + c
					+ "] in Cellname is not between A to Z.");
		}

		// 获取第一位数字(即行号)
		String number = cellName.substring(1);
		try {
			int row = Integer.parseInt(number);
			return new int[] { row, col };
		} catch (NumberFormatException e) {
			throw new CellNameWrongException("The number [" + number
					+ "] in Cellname is a wrong number.", e);
		}

	}

	/**
	 * 根据二维表格位置得出单元格位置名(1-based). 如：2, 1 --> A2 (其中2为行号, A和为列号)
	 * 
	 * @param row
	 *            行号
	 * @param col
	 *            列号
	 * @return
	 */
	public static String pos2name(int row, int col) {
		if (col > 26 || col < 1 || row < 1) {
			throw new RuntimeException("Row number cannot large than 26, row ["
					+ row + "] and col [" + row + "] cannot less than 1.");
		}

		char letter = (char) (col + 64);
		return letter + String.valueOf(row);
	}
}
