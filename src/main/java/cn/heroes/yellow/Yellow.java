package cn.heroes.yellow;

import java.io.File;
import java.io.FileInputStream;

import cn.heroes.yellow.intercepter.Interceptor;
import cn.heroes.yellow.intercepter.TDIntercepter;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.ExcelParser;

public class Yellow {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("test.xls");
		TDParser parser = new ExcelParser();
		TDIntercepter intercepter = null;
		
		
		parser.parse(fis);

	}
	
	private TDParser parser;
	
	private Yellow() {
		
	}

	public static Yellow buildTDYellow(TDParser parser, Interceptor interceptor) {
		return null;
	}

	public void yellow(File file) {
		// TODO 更多数据源DataSource, InputStream
		
	}

}
