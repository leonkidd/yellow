package cn.yhhh.test;

import java.util.Map;
import java.util.Set;

import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.intercepter.TDCellIntercepter;

public class MyTDCellIntercepter extends TDCellIntercepter {

	public MyTDCellIntercepter(Set<String> cellPoses) {
		super(cellPoses);
	}

	private Map<String, Object> cellDatas;

	public Map<String, Object> getCellDatas() {
		return cellDatas;
	}

	@Override
	public void callback(Map<String, Object> cellDatas) {
		this.cellDatas = cellDatas;
	}

	@Override
	public void inputInfo(Info info) {
		super.inputInfo(info);
	}

}
