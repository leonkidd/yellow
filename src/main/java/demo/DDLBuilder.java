package demo;

import java.io.File;

import cn.heroes.jkit.callback.ACallback;
import cn.heroes.jkit.utils.FileUtils;
import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.NTDYellow;
import cn.heroes.yellow.filler.impl.SheetNFiller;
import cn.heroes.yellow.parser.impl.ExcelParser;

public class DDLBuilder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File template = new File("test/DDL.xls");

		ExcelParser parser = new ExcelParser();
		DDLBuilderIntercepter intercepter = new DDLBuilderIntercepter();
		SheetNFiller filler = new SheetNFiller(template, "标准化字段");
		final Yellow yellow = new NTDYellow(parser, intercepter, filler);
		FileUtils.recursion(new File("test/copy"), null, new ACallback() {
			@Override
			public void invoke(Object... t) {
				File file = (File) t[0];
				yellow.yellow(file);
			}
		});
		yellow.destroy();
	}

}
