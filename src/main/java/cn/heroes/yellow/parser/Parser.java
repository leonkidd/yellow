package cn.heroes.yellow.parser;

import java.io.InputStream;

import cn.heroes.yellow.NObject;
import cn.heroes.yellow.exception.ParsingException;

/**
 * The <code>Parser</code> interface should be implemented by all Parsers.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 */
public interface Parser<T> extends NObject {

	/**
	 * Parse the specified input stream.
	 * 
	 * @param is
	 *            the specified input stream.
	 * @return
	 */
	T parse(InputStream is) throws ParsingException;
}
