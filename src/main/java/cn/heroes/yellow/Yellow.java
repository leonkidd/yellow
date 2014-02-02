package cn.heroes.yellow;

import java.io.File;
import java.io.InputStream;

import cn.heroes.yellow.intercepter.Interceptor;
import cn.heroes.yellow.parser.Parser;
import cn.heroes.yellow.parser.TDParser;

/**
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class Yellow {
	public static void main(String[] args) throws Exception {
	}

	private TDParser parser;

	private Yellow() {

	}

	/**
	 * 多例
	 * 
	 * @param parser
	 * @param interceptor
	 * @return
	 */
	public static <T> Yellow build(Parser<T> parser, Interceptor interceptor) {
		// TODO
		Yellow yellow = new Yellow();
		parser.init();
		interceptor.init();
		return null;
	}

	public void yellow(File file) {
		// TODO 更多数据源DataSource, InputStream

	}

	public void yellow(InputStream is) {

	}

}