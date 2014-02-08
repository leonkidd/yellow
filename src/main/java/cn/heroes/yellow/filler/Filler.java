package cn.heroes.yellow.filler;

import cn.heroes.yellow.NObject;
import cn.heroes.yellow.entity.Info;

public interface Filler<T> extends NObject {

	/**
	 * Fill data into a file or something else.
	 * 
	 * @param t
	 *            Data content
	 * @param info
	 *            Information about saving.
	 */
	void fill(T data, Info info);
}