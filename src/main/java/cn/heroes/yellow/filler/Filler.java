package cn.heroes.yellow.filler;

import cn.heroes.yellow.NObject;
import cn.heroes.yellow.entity.Info;

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
	 * @param info
	 *            Information about saving media.
	 */
	void fill(T data, Info info);
}