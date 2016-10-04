package cn.heroes.yellow.parser;

import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.TDSheet;
import cn.heroes.yellow.exception.UnParsedException;

/**
 * N Two-Dimensional Table parser interface
 * <p>
 * The <tt>T</tt> is source document type that parsed, or <i>NULL</i> if
 * SourceFiller will not be used.
 * </p>
 * 
 *
 * @author Leon Kidd
 * @version 1.00, 2016-10-4
 * @see cn.heroes.yellow.filler.SourceFiller
 */
public interface NTDParser<T> extends Parser<T> {

	/**
	 * Move pointer to the next sheet, and get that N Two-Dimensional Table
	 * sheet. Didn't hold any row at the beginning.
	 * 
	 * @return TDSheet object, return <code>null</code> until the end.
	 * @throws UnParsedException
	 *             if <code>parse</code> method didn't invoked before.
	 */
	TDSheet sheet() throws UnParsedException;

	/**
	 * Move pointer to the next row of current sheet, and get that
	 * Two-Dimensional Table Row. Didn't hold any row at the beginning.
	 * 
	 * @return TDRow object, return <code>null</code> until the end.
	 * @throws UnParsedException
	 *             if <code>parse</code> method didn't invoked before.
	 */
	TDRow next() throws UnParsedException;

}
