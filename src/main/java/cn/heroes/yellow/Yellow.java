package cn.heroes.yellow;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

import cn.heroes.yellow.intercepter.Interceptor;
import cn.heroes.yellow.intercepter.TDIntercepter;
import cn.heroes.yellow.parser.Parser;
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

	public static <T> Yellow build(Parser<T> parser, Interceptor interceptor) {
		
		return null;
	}

	public void yellow(File file) {
		// TODO 更多数据源DataSource, InputStream
		
	}

	public void yellow(InputStream is) {
		
	}

}