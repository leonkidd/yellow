package cn.yhhh.test;

import java.io.File;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;
import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.intercepter.TDIntercepter;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.Sheet0Parser;

public class Lab {

	public static void main(String[] args) {
		File source = new File("test/lab.xlsx");
		File output = new File("test/importer.xls");
		Filler filler = null;
		TDParser parser = new Sheet0Parser();
		TDIntercepter intercepter = new LabIntercepter();
		Yellow yellow = new TDYellow(parser, intercepter, null);
		yellow.yellow(source);
		
	}
}
