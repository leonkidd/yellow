package cn.heroes.yellow.intercepter;

import java.io.File;

import cn.heroes.yellow.entity.TDRow;

public interface TDIntercepter extends Interceptor {

	/**
	 * 
	 * Should we begin from here, but not contain current row? Just invoked
	 * before begin.
	 * 
	 * @param row
	 * @return if true, we will start from here.
	 */
	boolean begin(TDRow row);

	/**
	 * 
	 * Should this row be ended, but not contain current row? Just invoked after
	 * begin.
	 * 
	 * @param row
	 * @return if true, we will end util here and this row will be ignored;
	 */
	boolean end(TDRow row);

	/**
	 * Should this row be ignored? Between begin and end.
	 * 
	 * @param row
	 * @return true if should ignore this row.
	 */
	boolean ignore(TDRow row);

	/**
	 * Intercept every row between begin and end, not contain row both.
	 * 
	 * @param row
	 *            row.
	 */
	void row(TDRow row);

	/**
	 * Intercept every file
	 * 
	 * @param file
	 *            file.
	 */
	void file(File file);

	/**
	 * Intercept every directory
	 * 
	 * @param directory
	 *            directory.
	 */
	void directory(File directory);

}
