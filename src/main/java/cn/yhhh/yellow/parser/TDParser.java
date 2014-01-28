package cn.yhhh.yellow.parser;

import java.io.File;

import cn.yhhh.yellow.entity.TDRow;
import cn.yhhh.yellow.exception.UnParserException;

/**
 * Two-Dimensional Table parser interface<br/>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @since 1.0
 */
public interface TDParser extends Parser {

	/**
	 * Parse the specified file
	 * 
	 * @param file
	 *            the specified file
	 * @return
	 */
	void parser(File file);

	/**
	 * Move pointer to the next row, and get that Two-Dimensional Table Row.
	 * Didn't hold any row at the beginning.
	 * 
	 * @return TDRow object, return <code>null</code> if row is not exist.
	 * @throws UnParserException
	 */
	TDRow next() throws UnParserException;
}
