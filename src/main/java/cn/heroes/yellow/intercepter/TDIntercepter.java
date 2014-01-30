package cn.heroes.yellow.intercepter;

import java.io.File;

import cn.heroes.yellow.entity.TDRow;

/**
 * Two-Dimensional Table Intercepter interface<br/>
 * 二维表格拦截器, 逐行处理.
 * <p>
 * Priority Level between <code>begin</code>, <code>ignore</code> and
 * <code>end</code> is begin > end > ignore, and begin once.
 * 
 * <pre>
 * while (begin) {
 * 	while (!end) {
 * 		if (!ignore) {
 * 			// Business code
 * 		}
 * 	}
 * 	break;
 * }
 * </pre>
 * 
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @since 1.0
 */
public interface TDIntercepter extends Interceptor {

	/**
	 * 
	 * Should we begin here? And contain current row.<br/>
	 * It means the specified row will be <code>row()</code>.
	 * 
	 * @param row
	 * @return if true, we will start to <code>row()</code>.
	 */
	boolean begin(TDRow row);

	/**
	 * 
	 * Should we end here? And not contain current row.<br/>
	 * It means the specified row will not be <code>row()</code>.
	 * 
	 * @param row
	 * @return if true, we will end until here and this row will be ignored;
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
	 * Intercept every row between begin and end. And contain begin, but not
	 * end.
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

}
