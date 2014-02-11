package cn.yhhh.test;

import java.io.File;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;
import cn.heroes.yellow.filler.impl.Sheet0Filler;
import cn.heroes.yellow.intercepter.TDIntercepter;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.Sheet0Parser;

public class Lab {

	public static void main(String[] args) {
		File source = new File("test/lab.xlsx");
		File template = new File("template.xls");
		
		Sheet0Filler filler = new Sheet0Filler(template);
		TDParser parser = new Sheet0Parser();
		TDIntercepter intercepter = new LabIntercepter();
		
		Yellow yellow = new TDYellow(parser, intercepter, filler);
		yellow.yellow(source);
	}
}
