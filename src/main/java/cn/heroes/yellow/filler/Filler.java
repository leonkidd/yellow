package cn.heroes.yellow.filler;

import java.io.OutputStream;

import cn.heroes.yellow.NObject;

/**
 * The <code>Filler</code> interface should be implemented by all Fillers. Fill
 * data into something.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-11
 */
public interface Filler<T> extends NObject {

	/**
	 * Fill data into a file or something else.
	 * 
	 * @param t
	 *            Data content
	 * @param os
	 *            The <code>OutputStream</code> that data save into.
	 */
	void fill(T data, OutputStream os);
}