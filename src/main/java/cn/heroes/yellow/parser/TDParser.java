package cn.heroes.yellow.parser;

import java.util.Iterator;

import cn.heroes.yellow.entity.TDRow;

/**
 * Two-Dimensional Table parser interface<br/>
 * 二维表格解析器,从数据源中获取"遂条"(遂行)的数据, 以行列的接口(TDRow)形式呈现数据, 如del, excel等
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @since 1.0
 */
public interface TDParser extends Parser<Iterator<TDRow>> {

	/**
	 * Move pointer to the next row, and get that Two-Dimensional Table Row.
	 * Didn't hold any row at the beginning.
	 * 
	 * @return TDRow object, return <code>null</code> if row is not exist.
	 * @throws UnParsedException
	 *             if <code>parse</code> method didn't invoked before.
	 */
	// TDRow next() throws UnParsedException;

}
