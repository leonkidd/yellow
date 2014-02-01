package cn.yhhh.test;

import java.util.Map;

import cn.heroes.yellow.intercepter.TDCellIntercepter;

public class MyTDCellIntercepter extends TDCellIntercepter {

	public MyTDCellIntercepter(String[] cellPoses) {
		super(cellPoses);
	}

	@Override
	public void callback(Map<String, Object> cellDatas) {

	}

}
