package demo;

import java.io.File;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.DelParser;

public class InnerDetail {
	public static void main(String[] args) {
		// Parser
		TDParser parser = new DelParser();
		// Intercepter
		InnerDetailIntercepter intercepter = new InnerDetailIntercepter();
		// Filler
		//Sheet0Filler filler = new Sheet0Filler(new File("template.xls"));
		// Yellow
		Yellow yellow = new TDYellow(parser, intercepter, null);
		// do yellow
		File[] files = new File("test/inner").listFiles();
		for(File file : files) {
			yellow.yellow(file);
		}
		yellow.destroy();
	}
}
