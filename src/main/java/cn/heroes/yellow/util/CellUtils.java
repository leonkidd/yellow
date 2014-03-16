package cn.heroes.yellow.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.util.CellReference;

import cn.heroes.yellow.exception.CellNameWrongException;

/**
 * Excel单元格工具
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-3-16
 * @since 1.0
 */
public class CellUtils {

	/**
	 * Takes in a column reference portion of a CellRef and converts it from
	 * ALPHA-26 number format to 1-based base 10. 'A' -> 1, 'Z' -> 26, 'AA' ->
	 * 27, 'IV' -> 256.
	 * 
	 * @param ref
	 * @return 1-based column index
	 */
	public static int colIndex(String ref) {
		return CellReference.convertColStringToIndex(ref) + 1;
	}

	// 单元格位置名匹配的正则表达式, e.g. A2
	private static final Pattern pCellName = Pattern
			.compile("([a-zA-Z]+)(\\d+)");

	/**
	 * 根据单元格位置名得出二维表格位置(1-based), 列名字母支持大、小写. 如：A2 --> 2, 1
	 * 
	 * @param cellName
	 *            单元格位置名, e.g. A2, A代表列数,2代表行数
	 * @return 该单元格的二维表位置, 1-based, e.g. [2, 1] 第一位为行号， 第二位为列号.
	 */
	public static int[] name2pos(String cellName) {
		// 匹配表达式
		Matcher m = pCellName.matcher(cellName);
		// 是否整名匹配
		if (m.matches()) {
			// 列字母.
			String colLetter = m.group(1);
			// 行号, 1-based.
			String rowStr = m.group(2);

			try {
				// 列号, 1-based.
				int col = CellReference.convertColStringToIndex(colLetter) + 1;
				// 行号, 1-based.
				int row = Integer.parseInt(rowStr);
				return new int[] { row, col };
			} catch (NumberFormatException e) {
				throw new CellNameWrongException("The number [" + rowStr
						+ "] in Cellname is a wrong number.", e);
			}
		} else {
			throw new CellNameWrongException("The Cellname [" + cellName
					+ "] is ilegal, the legal cell name looks like 'A2'.");
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
		return CellReference.convertNumToColString(col - 1) + row;
	}
}
