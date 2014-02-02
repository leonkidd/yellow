package cn.yhhh.test;

import java.io.FileInputStream;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 实现Interceptor
		// 构建Yellow, 装入Parser, Interceptor, Filler

		FileInputStream fis = new FileInputStream("");
		// 只分析流
		// Yellow多例
		Yellow yellow = new TDYellow(null, null, null);
		yellow.yellow(fis);

	}

}
