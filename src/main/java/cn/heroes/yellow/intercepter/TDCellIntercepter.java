package cn.heroes.yellow.intercepter;

import java.io.File;
import java.util.Map;

import cn.heroes.yellow.entity.TDRow;

/**
 * 二维表单元格取值<br/>
 * 获取指定的单元格的数值.
 * 
 * <p>
 * 单元格标识为：<code>[SheetName!]CellName</code>, 其中<code>SheetName</code>
 * 代表Excel的SheetName(当非Excel或默认第一个Sheet时可不用写), <code>CellName</code>代表单元格位置(e.g.
 * H3).
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-30
 */
public abstract class TDCellIntercepter implements TDIntercepter {

	/** Alphabet字母表 */
	private static final String ABC = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";

	public TDCellIntercepter(String[] cellPoses) {

	}
	
	public abstract void callback(Map<String, Object> cellDatas);

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean begin(TDRow row) {
		return false;
	}

	@Override
	public boolean end(TDRow row) {
		return false;
	}

	@Override
	public boolean ignore(TDRow row) {
		return false;
	}

	@Override
	public void row(TDRow row) {

	}

	@Override
	public void file(File file) {

	}

//	@Override
//	public void directory(File directory) {
//
//	}

}
