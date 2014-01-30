package cn.yhhh.fairy.yellow.interceptor;

import java.io.File;
import java.io.Serializable;

import cn.yhhh.fairy.yellow.core.Configuration;
import cn.yhhh.fairy.yellow.entity.Row;

public interface Interceptor extends Serializable {

	public void init(Configuration config);

	public void destroy();

	/**
	 * 
	 * Should we begin from here, but not contain current row? Just invoked
	 * before begin.
	 * 
	 * @param row
	 * @return if true, we will start from here.
	 */
	public boolean begin(Row row);

	/**
	 * 
	 * Should this row be ended, but not contain current row? Just invoked after
	 * begin.
	 * 
	 * @param row
	 * @return if true, we will end util here and this row will be ignored;
	 */
	public boolean end(Row row);

	/**
	 * Should this row be ignored? Between begin and end.
	 * 
	 * @param row
	 * @return true if should ignore this row.
	 */
	public boolean ignore(Row row);

	/**
	 * Intercept every row between begin and end, not contain row both.
	 * 
	 * @param row
	 *            row.
	 */
	public void row(Row row);

	/**
	 * Intercept every file
	 * 
	 * @param file
	 *            file.
	 */
	public void file(File file);

	/**
	 * Intercept every directory
	 * 
	 * @param directory
	 *            directory.
	 */
	public void directory(File directory);

}
