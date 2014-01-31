package cn.heroes.yellow.core.impl;

import java.io.File;
import java.io.InputStream;

import cn.heroes.yellow.intercepter.Interceptor;
import cn.heroes.yellow.parser.Parser;
import cn.heroes.yellow.parser.TDParser;

public class YellowImpl {
	
	private Parser<?> parser;
	private Interceptor interceptor;
	
	private YellowImpl() {
		
	}

	public static <T> YellowImpl build(Parser<T> parser, Interceptor interceptor) {
		YellowImpl yellow = new YellowImpl();
		yellow.parser = parser;
		yellow.interceptor = interceptor;
		
		// 初始化
		parser.init();
		interceptor.init();
		return yellow;
	}

	public static <T> YellowImpl buildTD(TDParser parser, Interceptor interceptor) {
		YellowImpl yellow = new YellowImpl();
		yellow.parser = parser;
		yellow.interceptor = interceptor;
		
		// 初始化
		parser.init();
		interceptor.init();
		return yellow;
	}

	public void yellow(File file) {
		// TODO 更多数据源DataSource, InputStream
		
	}

	public void yellow(InputStream is) {
		Object result = parser.parse(is);
		
	}

}