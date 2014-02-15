package cn.heroes.yellow.entity;

/**
 * TODO
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-15
 * @since 1.0
 */
public interface TDPage {

	/**
	 * Get the name of page.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Move pointer to the next row, and get that Two-Dimensional Table Row.
	 * Didn't hold any row at the beginning.
	 * 
	 * @return TDRow object, return <code>null</code> if row is not exist.
	 */
	TDRow next();

}
