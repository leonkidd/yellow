package cn.yhhh.test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import cn.heroes.jkit.utils.Callback;
import cn.heroes.jkit.utils.FileUtils;
import cn.heroes.yellow.Yellow;
import cn.heroes.yellow.intercepter.TDCellIntercepter;
import cn.heroes.yellow.parser.impl.ExcelParser;

public class MyTDCellIntercepter extends TDCellIntercepter {

	@Override
	public void callback(Map<String, Object> cellDatas) {

		// Excel 解析器
		ExcelParser parser = new ExcelParser();

		// 实现的单元格拦截器
		MyTDCellIntercepter ic = new MyTDCellIntercepter();

		// 创建Yellow对象
		Yellow yellow = Yellow.build(parser, ic);

		// 处理文件
		File template = new File("test/cell/template.xls");
		yellow.yellow(template);

	}

}
