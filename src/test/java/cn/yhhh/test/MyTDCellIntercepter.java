package cn.yhhh.test;

import java.util.Map;

import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.intercepter.TDCellIntercepter;

public class MyTDCellIntercepter extends TDCellIntercepter {

	public MyTDCellIntercepter(String[] cellPoses) {
		super(cellPoses);
	}

	@Override
	public void callback(Map<String, Object> cellDatas) {
		for(String cellPos : cellPoses) {
			Object value = cellDatas.get(cellPos);
			System.out.println(cellPos + ", " + value);
		}
	}

	@Override
	public void info(Info info) {
		//FileInfo ei = (FileInfo)info;
		//System.out.println("开始解析: " + ei.file.getName());
	}

}
