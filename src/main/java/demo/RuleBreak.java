package demo;

import java.io.File;

import cn.heroes.yellow.core.impl.NTDYellow;
import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.filler.impl.Sheet0Filler;
import cn.heroes.yellow.parser.NTDParser;
import cn.heroes.yellow.parser.impl.ExcelParser;

/**
 * Demo 3:<br/>
 * TODO TD-SheetN-Parser, TD-CellIntercepter, NONE Filler Demo
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-15
 * @since 1.0
 */
public class RuleBreak {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Parser
		NTDParser parser = new ExcelParser();
		// Intercepter
		RuleBreakIntercepter rbi = new RuleBreakIntercepter();
		// Filler
		Sheet0Filler filler = new Sheet0Filler(new File("template.xls"));
		// Yellow
		NTDYellow yellow = new NTDYellow(parser, rbi, filler);
		// do yellow
		yellow.yellow(new File("*.xls"));
		yellow.destroy();
	}

}
