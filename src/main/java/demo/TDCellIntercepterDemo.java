package demo;

import java.io.File;
import java.util.Map;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;
import cn.heroes.yellow.intercepter.TDCellIntercepter;
import cn.heroes.yellow.parser.impl.SheetNParser;

public class TDCellIntercepterDemo extends TDCellIntercepter {

	public TDCellIntercepterDemo(String[] cellPoses) {
		super(cellPoses);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TDCellIntercepter
		Yellow yellow = new TDYellow(new SheetNParser(0),
				new TDCellIntercepterDemo(new String[] { "A2", "H20", "L11" }), null);
		yellow.yellow(new File("A.xlsx"));
		yellow.destroy();
	}

	@Override
	public void info(Object info) {

	}

	@Override
	public void callback(Map<String, Object> cellDatas) {
		System.out.println();
	}

}
