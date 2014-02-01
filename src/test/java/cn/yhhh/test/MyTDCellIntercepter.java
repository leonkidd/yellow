package cn.yhhh.test;

import java.util.Map;

import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.impl.FileInfo;
import cn.heroes.yellow.intercepter.TDCellIntercepter;

public class MyTDCellIntercepter extends TDCellIntercepter {

	public MyTDCellIntercepter(String[] cellPoses) {
		super(cellPoses);
	}

	@Override
	public void callback(Map<String, Object> cellDatas) {

	}
	
	// TODO

	@Override
	public void info(FileInfo info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Info info) {
		// TODO Auto-generated method stub
		
	}

}
