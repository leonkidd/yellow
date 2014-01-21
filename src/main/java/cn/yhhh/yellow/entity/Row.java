package cn.yhhh.fairy.yellow.entity;

/**
 * Row entity interface
 * 
 * @author Leon Kidd
 * @version 1.00, 2011-9-4
 * @since 1.0
 */
public interface Row {

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            the first column is 0, the second is 1, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	public Object getObject(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            the first column is 0, the second is 1, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	public String getString(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            the first column is 0, the second is 1, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	public double getDouble(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            the first column is 0, the second is 1, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	public float getFloat(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            the first column is 0, the second is 1, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	public long getLong(int i);

	/**
	 * Retrieves the value of the designated column in the current row.
	 * 
	 * @param i
	 *            the first column is 0, the second is 1, ...
	 * @return the column value; if the value is not exist, the value returned
	 *         is <code>null</code>
	 */
	public int getInt(int i);

	/**
	 * Retrieves the length of the current row.
	 * 
	 * @return the number of column.
	 */
	public int length();

	/**
	 * Retrieves the number of the current row.
	 * 
	 * @return the first row is 1, the second row is 2, ...
	 */
	public long getRowNum();

	/**
	 * 
	 * Retrieves all column's value array of the current row.
	 * 
	 * @return
	 */
	public Object[] getValues();

}
