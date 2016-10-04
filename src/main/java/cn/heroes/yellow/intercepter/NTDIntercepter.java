package cn.heroes.yellow.intercepter;

/**
 * N Two-Dimensional Table Intercepter interface, iterate every sheet and row.
 * <p>
 * NOTE: Some local attribute's data should be reset, because intercepter is
 * singleton.
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2016-10-4
 * @see TDIntercepter
 */

public interface NTDIntercepter extends TDIntercepter {

	/**
	 * Ask for parsing current sheet or not, invoke once before starting to
	 * parse every sheet.
	 * <p>
	 * NOTE: Some local attribute's data should be reset, because intercepter is
	 * singleton.
	 * </p>
	 * 
	 * @param index
	 *            current sheet index, 1-based.
	 * @param name
	 *            current sheet name
	 * @return true if this sheet should to parse, false if this sheet should be
	 *         ignore.
	 */
	public boolean sheet(int index, String name);
}
