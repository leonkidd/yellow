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
		for(String cellPos : cellPoses) {
			Object value = cellDatas.get(cellPos);
			System.out.println(cellPos + ", " + value);
		}
	}

	@Override
	public void info(Info info) {
		super.info(info);
	}

}
