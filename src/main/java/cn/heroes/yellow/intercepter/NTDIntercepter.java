package cn.heroes.yellow.intercepter;

public abstract class NTDIntercepter<F> implements TDIntercepter<F> {

	/**
	 * Current handling sheet, invoke once before starting to parse every sheet.
	 * 
	 * @param index
	 *            1-based
	 * @param name
	 */
	public abstract void sheet(int index, String name);

}
