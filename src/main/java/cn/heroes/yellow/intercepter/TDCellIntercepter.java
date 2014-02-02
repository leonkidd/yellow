package cn.heroes.yellow.intercepter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.ExcelInfo;

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

	/** Alphabet 26位字母表 */
	private static final String ABC = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
	/** 存储单元格位置信息的List */
	protected List<String> cellPoses;

	/**
	 * 
	 * @param cellPoses
	 *            存储单元格位置信息的数组(顺序无关)
	 */
	public TDCellIntercepter(String[] cellPoses) {
		this.cellPoses = Arrays.asList(cellPoses);
	}

	/**
	 * 
	 * @param cellPoses
	 *            存储单元格位置信息的List(顺序无关)
	 */
	public TDCellIntercepter(List<String> cellPoses) {
		this.cellPoses = cellPoses;
	}

	public abstract void callback(Map<String, Object> cellDatas);

	@Override
	public void init() {
		// 单元格位置名排序, 先按行小的,再按列小的. e.g. A1,A2,B1,B2
		// TODO Sheet? 先分类
		Collections.sort(cellPoses);
	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean begin(TDRow row) {
		return true;
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
	
	// TODO 标识Info: File, Directory, Sheet

}
