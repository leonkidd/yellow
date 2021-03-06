package cn.heroes.yellow.parser;

import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.exception.UnParsedException;

/**
 * Two-Dimensional Table parser interface<br/>
 * 二维表格解析器,从数据源中获取"遂条"(遂行)的数据, 以行列的接口(TDRow)形式呈现数据, 如del, excel等.
 * <p>
 * The <tt>T</tt> is source document type that parsed, or <i>NULL</i> if
 * SourceFiller will not be used.
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @since 1.0
 * @see cn.heroes.yellow.filler.SourceFiller
 */
public interface TDParser<T> extends Parser<T> {

	/**
	 * Move pointer to the next row, and get that Two-Dimensional Table Row.
	 * Didn't hold any row at the beginning.
	 * 
	 * @return TDRow object, return <code>null</code> until the end.
	 * @throws UnParsedException
	 *             if <code>parse</code> method didn't invoked before.
	 */
	TDRow next() throws UnParsedException;

}
