package cn.heroes.yellow.intercepter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.FileInfo;
import cn.heroes.yellow.util.Chessboard;

/**
 * 二维表单元格(横标尺为ABC..., 纵标尺为123...)取值, 获取指定的单元格的数值.
 * <p>
 * 单元格标识为：<code>CellName</code>, 代表单元格位置(e.g. H3).
 * </p>
 * Usage: 实现<code>callback</code>方法, 实例一个实现类对象,并传入要解析的单元格位置名称数组.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-30
 */
public abstract class TDCellIntercepter implements TDIntercepter {
	
	private static final Logger logger = LoggerFactory.getLogger(TDCellIntercepter.class);

	/** 存储单元格位置信息的List, e.g. {"H1", "B2"} */
	protected List<String> cellPoses;
	/** 存储单元格位置信息的Map, key: RowNum (1-based), value: List[ColNum] */
	private Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
	/** 解析出来的单元格内容 */
	private Map<String, Object> cellDatas = new HashMap<String, Object>();

	//private int[][] i = new int[10][];

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

	/**
	 * 回调方法,用于将解析出来的单元格内容返回给方法实现者。
	 * 
	 * @param cellDatas
	 *            key: 单元格位置名称(e.g. H2); value: 分析出来的内容.
	 */
	public abstract void callback(Map<String, Object> cellDatas);

	@Override
	public void init() {
		// 遍历单元格位置名称, 构建单元格位置信息map
		for (String cellPos : cellPoses) {
			// 单元格位置名称转为单元格位置二维数组
			int[] p = Chessboard.name2pos(cellPos);
			// TODO exception
			int row = p[0];
			int col = p[1];

			List<Integer> list = map.get(row);
			if (list == null) {
				list = new ArrayList<Integer>();
				map.put(row, list);
			}
			list.add(col);
		}

		// Collections.sort(cellPoses);
	}

	@Override
	public void over() {
		callback(cellDatas);
	}

	@Override
	public void row(TDRow row) {
		// 当前行号
		int rowNum = row.getRowNum();
		// 是否存在该行的单元格信息
		List<Integer> list = map.get(rowNum);
		if (list == null) {
			return;
		}

		// 迭代该行里需要分析的列号
		for (int i : list) {
			// 该单元格的内容
			Object value = row.getObject(i);
			if (value == null) {
				value = "";
			}
			// 该单元格的位置名称
			String key = Chessboard.pos2name(rowNum, i);

			cellDatas.put(key, value);
		}
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
	public void info(Info info) {
		FileInfo fi = (FileInfo)info;
		logger.info("开始处理[{}]目录下的文件[{}]", fi.file.getParent(), fi.file.getName());
	}

	@Override
	public void destroy() {

	}

}
