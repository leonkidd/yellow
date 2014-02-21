package cn.heroes.yellow.intercepter;

import java.util.List;

import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;

/**
 * 
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-21
 * @since 1.0
 */
public abstract class CopyIntercepter<F> extends NTDIntercepter<F> {

	/**
	 * Should we begin copy here? And contain current row.
	 * 
	 * @return if true, we will start to copy.
	 */
	@Override
	public abstract boolean begin(TDRow row);

	/**
	 * Should we end copy here? And not contain current row.
	 * 
	 * @return if true, we will end until here and this row will be ignored;
	 */
	@Override
	public abstract boolean end(TDRow row);

	/**
	 * 
	 * Does this sheet have something to copy ?
	 */
	@Override
	public abstract boolean sheet(int index, String name);

	@Override
	public void row(TDRow row) {
		// TODO copy
	}

	@Override
	public void info(Info<F> info) {

	}

	@Override
	public FillObject<List<Object[]>> over() {
		// TODO fill
		return null;
	}
}
