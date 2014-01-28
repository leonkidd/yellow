package cn.heroes.yellow.entity;

/**
 * Two-Dimensional Table's row<br/>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @since 1.0
 */
public interface TDRow {

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            1-based, the first column is 1, the second is 2, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	Object getObject(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            1-based, the first column is 1, the second is 2, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	String getString(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            1-based, the first column is 1, the second is 2, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	double getDouble(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            1-based, the first column is 1, the second is 2, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	float getFloat(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            1-based, the first column is 1, the second is 2, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	long getLong(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            1-based, the first column is 1, the second is 2, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	int getInt(int i);

	/**
	 * Retrieves the length of the current row.
	 * 
	 * @return the number of column.
	 */
	int length();

	/**
	 * Retrieves the number of the current row.
	 * 
	 * @return the first row is 1, the second row is 2, ...
	 */
	long getRowNum();

	/**
	 * 
	 * Retrieves all column's value array of the current row.
	 * 
	 * @return
	 */
	Object[] getValues();
}
