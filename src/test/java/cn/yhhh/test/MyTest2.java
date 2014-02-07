package cn.yhhh.test;

import java.io.File;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.Sheet0Parser;

public class MyTest2 {

	public static void main(String[] args) {
		File source = new File("source.xls");
		// TODO Filler

		TDParser parser = new Sheet0Parser();
		new Yellow(parser, interceptor, null);
		
	}
}
