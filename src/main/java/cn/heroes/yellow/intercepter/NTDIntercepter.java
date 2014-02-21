package cn.heroes.yellow.intercepter;

/**
 * TODO
 * <p>
 * NOTE: This intercepter is singleton, so the attributes will be used in every
 * sheet.
 * <p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-16
 * @since 1.0
 */

public abstract class NTDIntercepter<F> implements TDIntercepter<F> {

	/**
	 * Current handling sheet, invoke once before starting to parse every sheet.
	 * <p>
	 * NOTE: Some local attribute's data should be reset, because intercepter is
	 * singleton.
	 * </p>
	 * 
	 * @param index
	 *            current sheet index, 1-based.
	 * @param name
	 *            current sheet name
	 * @return true if this sheet should to handle, false if this sheet should
	 *         be ignore.
	 */
	public abstract boolean sheet(int index, String name);
}
